package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants;

/*
    After the 14th Dec League, we should have only 1 base class and have chain drive and direct drive as sub-class.
    Although, we doubt we will go back to the chain drive, this is a GREAT example for kids to learn about
    inheritance in JAVA.
 */

public class DirectDrive {

    private final ElapsedTime driveRuntime = new ElapsedTime();
    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;
    private double globalAngle;
    private Orientation lastAngles = new Orientation();

    private ColorDistance colorDistance = new ColorDistance(this.cBot, this.telemetry, this.linearOpMode);

    /*
     * Constructor
     */
    public DirectDrive(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

        calibrateGyro();

    }


    /**
     * Drive forward
     **/
    public void forward(double distanceInches, double power, double timeoutSeconds) {

        driveByEncoder(power, distanceInches, distanceInches, timeoutSeconds);

    }

    /**
     * Drive backward
     **/
    public void backward(double distanceInches, double power, double timeoutSeconds) {

        driveByEncoder(power, -distanceInches, -distanceInches, timeoutSeconds);

    }

    /**
     * Turn Left using IMU
     */
    public void rotateLeft(int degrees, double power) {

        rotate(degrees, power);
    }

    /**
     * Turn Right using IMU
     */
    public void rotateRight(int degrees, double power) {

        rotate(-degrees, power);
    }

    /**
     * Drive by Color/Distance Sensor
     */

    public void driveByProximity(double power, double proximity) {

        cBot.leftDrive.setPower(Math.abs(power));
        cBot.rightDrive.setPower(Math.abs(power));

        while (linearOpMode.opModeIsActive() && (colorDistance.getLeftDistance() > proximity && colorDistance.getRightDistance() > proximity)) {

            this.telemetry.addData("Distance to stones: ", "%.1f", colorDistance.getLeftDistance());
            this.telemetry.addData("Distance to stones: ", "%.1f", colorDistance.getRightDistance());
            this.telemetry.update();
            this.linearOpMode.sleep(50);
        }

        stop();

    }

    /**
     * Drive using Encoders
     **/
    private void driveByEncoder(double power,
                                double leftInches, double rightInches,
                                double timeoutSeconds) {

        int newLeftTarget;
        int newRightTarget;

        // Range Clip (Don't think this is actually needed)
        power = Range.clip(power, 0, 1);

        // Ensure that the OpMode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = cBot.leftDrive.getCurrentPosition() + (int) (leftInches * Constants.DirectDrive.COUNTS_PER_INCH);
            newRightTarget = cBot.rightDrive.getCurrentPosition() + (int) (rightInches * Constants.DirectDrive.COUNTS_PER_INCH);

            // Use gyro to drive in a straight line.
            // This is from : https://stemrobotics.cs.pdx.edu/node/7266
            //double correction = 0;
            double correction = checkDirection();
            telemetry.addData("IMU Correction:", "%.2f", correction);

            // Set Target Position with correction reported by IMU
            cBot.leftDrive.setTargetPosition(newLeftTarget - (int) correction);
            cBot.rightDrive.setTargetPosition(newRightTarget + (int) correction);

            // Turn On RUN_TO_POSITION
            cBot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            cBot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Reset the timeout time and start motion.
            driveRuntime.reset();
            cBot.leftDrive.setPower(Math.abs(power));
            cBot.rightDrive.setPower(Math.abs(power));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (linearOpMode.opModeIsActive() &&
                    (driveRuntime.seconds() < timeoutSeconds) &&
                    (cBot.leftDrive.isBusy() && cBot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        cBot.leftDrive.getCurrentPosition(),
                        cBot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Turn off RUN_TO_POSITION
            cBot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            cBot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            // Stop all motion;
            cBot.leftDrive.setPower(0);
            cBot.rightDrive.setPower(0);


            // optional pause after each move
            linearOpMode.sleep(50);
        }
    }

    /**
     * Resets the cumulative angle tracking to zero.
     */
    private void resetAngle() {
        lastAngles = cBot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     *
     * @return Angle in degrees. + = left, - = right.
     */
    private double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = cBot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    /**
     * See if we are moving in a straight line and if not return a power correction value.
     *
     * @return Power adjustment, + is adjust left - is adjust right.
     */
    private double checkDirection() {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .10;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
     *
     * @param degrees Degrees to turn, + is left - is right
     */
    private void rotate(int degrees, double power) {
        double leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0) {   // turn right.
            leftPower = power;
            rightPower = -power;
        } else if (degrees > 0) {   // turn left.
            leftPower = -power;
            rightPower = power;
        } else return;

        // set power to rotate.
        cBot.leftDrive.setPower(leftPower);
        cBot.rightDrive.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0) {
            // On right turn we have to get off zero first.
            while (linearOpMode.opModeIsActive() && getAngle() == 0) {
            }

            while (linearOpMode.opModeIsActive() && getAngle() > degrees) {
            }
        } else    // left turn.
            while (linearOpMode.opModeIsActive() && getAngle() < degrees) {
            }

        // turn the motors off.
        cBot.rightDrive.setPower(0);
        cBot.leftDrive.setPower(0);

        // wait for rotation to stop.
        this.linearOpMode.sleep(100);

        // reset angle tracking on new heading.
        resetAngle();
    }


    public void stop() {

        cBot.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cBot.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        cBot.leftDrive.setPower(0);
        cBot.rightDrive.setPower(0);
    }

    private void resetDrive() {

        cBot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cBot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        cBot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cBot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        cBot.leftDrive.setDirection(DcMotor.Direction.FORWARD);
        cBot.rightDrive.setDirection(DcMotor.Direction.REVERSE);

    }

    private void calibrateGyro() {

        // make sure the imu gyro is calibrated before continuing.
        while (!linearOpMode.isStopRequested() && !cBot.imu.isGyroCalibrated()) {
            this.linearOpMode.sleep(50);
            linearOpMode.idle();
        }
    }
}