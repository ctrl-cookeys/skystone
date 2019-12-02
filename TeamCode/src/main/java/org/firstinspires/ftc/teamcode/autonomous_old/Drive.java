package org.firstinspires.ftc.teamcode.autonomous_old;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants;

@Deprecated
public class Drive {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;

    private  DcMotor leftDrive;
    private  DcMotor rightDrive;

    private BNO055IMU imu;
    private double globalAngle;

    private Orientation lastAngles = new Orientation();

    private ElapsedTime driveRuntime = new ElapsedTime();

    /*
     * Constructor
     */
    public Drive(LinearOpMode linearOpMode) {

        this.linearOpMode = linearOpMode;
        this.telemetry = this.linearOpMode.telemetry;

        this.leftDrive = this.linearOpMode.hardwareMap.dcMotor.get("left_drive");
        this.rightDrive = this.linearOpMode.hardwareMap.dcMotor.get("right_drive");

        initIMU();
        resetDrive();

    }

    /**
     * Code to driveTrain around using direction enums
     * Note: Using forward, backward, turnLeft, turnRight may be more straightforward
     *
     * @param direction      FORWARD, BACKWARD, LEFT_TURN, RIGHT_TURN
     * @param power          Motor Power
     * @param distanceInches Distance to driveTrain in inches. Ideally same value for both
     *                       left and right driveTrain
     * @param timeoutSeconds If unable to finish driving task within timeoutSeconds seconds
     *                       abort and move on to the next task. Set this value very carefully!
     **/
    @Deprecated
    public void drive(Direction direction, double distanceInches, double power, double timeoutSeconds) {

        switch (direction) {
            case FORWARD:
                driveByEncoder(power, distanceInches, distanceInches, timeoutSeconds);
                break;
            case BACKWARD:
                driveByEncoder(power, -distanceInches, -distanceInches, timeoutSeconds);
                break;
            case LEFT_TURN:
                driveByEncoder(power, -distanceInches, distanceInches, timeoutSeconds);
                break;
            case RIGHT_TURN:
                driveByEncoder(power, distanceInches, -distanceInches, timeoutSeconds);
                break;
        }
    }

    /**
     * Drive forward
     **/
    public void forward(double distanceInches, double power,  double timeoutSeconds) {

        driveByEncoder(power, distanceInches, distanceInches, timeoutSeconds);

    }

    /**
     * Drive backward
     **/
    public void backward(double distanceInches, double power, double timeoutSeconds) {

        driveByEncoder(power, -distanceInches, -distanceInches, timeoutSeconds);

    }

    /**
     * Turn Left using Motors
     * @deprecated
     * use rotateLeft instead
     **/
    @Deprecated
    public void turnLeft(double distanceInches, double power, double timeoutSeconds) {

        driveByEncoder(power, -distanceInches, distanceInches, timeoutSeconds);

    }

    /**
     * Turn Right using Motors
     * @deprecated
     * use rotateRight instead
     **/
    @Deprecated
    public void turnRight(double distanceInches, double power, double timeoutSeconds) {

        driveByEncoder(power, distanceInches, -distanceInches, timeoutSeconds);

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
     * Drive using Encoders
     **/
    private void driveByEncoder(double power,
                                double leftInches, double rightInches,
                                double timeoutSeconds) {

        int newLeftTarget;
        int newRightTarget;

        // Range Clip (Don't think this is actually needed)
        //power = Range.clip(power, 0, 1);

        // Ensure that the OpMode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = this.leftDrive.getCurrentPosition() + (int) (leftInches * Constants.Drive.COUNTS_PER_INCH);
            newRightTarget = this.rightDrive.getCurrentPosition() + (int) (rightInches * Constants.Drive.COUNTS_PER_INCH);

            // Use gyro to drive in a straight line.
            // This is from : https://stemrobotics.cs.pdx.edu/node/7266
            double correction = checkDirection();
            telemetry.addData("IMU Correction:", "%.2f", correction);

            // Set Target Position with correction reported by IMU
            this.leftDrive.setTargetPosition(newLeftTarget - (int) correction);
            this.rightDrive.setTargetPosition(newRightTarget + (int) correction);

            // Turn On RUN_TO_POSITION
            this.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Reset the timeout time and start motion.
            driveRuntime.reset();
            this.leftDrive.setPower(Math.abs(power));
            this.rightDrive.setPower(Math.abs(power));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (linearOpMode.opModeIsActive() &&
                    (driveRuntime.seconds() < timeoutSeconds) &&
                    (this.leftDrive.isBusy() && this.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        this.leftDrive.getCurrentPosition(),
                        this.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Turn off RUN_TO_POSITION
            this.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            // Stop all motion;
            this.leftDrive.setPower(0);
            this.rightDrive.setPower(0);


            // optional pause after each move
            this.linearOpMode.sleep(50);
        }
    }

    private void initIMU() {

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = this.linearOpMode.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        // make sure the imu gyro is calibrated before continuing.
        while (!this.linearOpMode.isStopRequested() && !imu.isGyroCalibrated()) {
            this.linearOpMode.sleep(50);
            this.linearOpMode.idle();
        }

    }

    /**
     * Resets the cumulative angle tracking to zero.
     */
    private void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
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

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

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
    @SuppressWarnings("StatementWithEmptyBody")
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
        this.leftDrive.setPower(leftPower);
        this.rightDrive.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0) {
            // On right turn we have to get off zero first.
            while (this.linearOpMode.opModeIsActive() && getAngle() == 0) {
            }

            while (this.linearOpMode.opModeIsActive() && getAngle() > degrees) {
            }
        } else    // left turn.
            while (this.linearOpMode.opModeIsActive() && getAngle() < degrees) {
            }

        // turn the motors off.
        this.rightDrive.setPower(0);
        this.leftDrive.setPower(0);

        // wait for rotation to stop.
        this.linearOpMode.sleep(100);

        // reset angle tracking on new heading.
        resetAngle();
    }


    public void stop() {

        this.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.leftDrive.setPower(0);
        this.rightDrive.setPower(0);
    }

    private void resetDrive() {

        this.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.leftDrive.setDirection(DcMotor.Direction.FORWARD);
        this.rightDrive.setDirection(DcMotor.Direction.REVERSE);

    }


    public enum Direction {

        FORWARD,
        BACKWARD,
        LEFT_TURN,
        RIGHT_TURN
    }
}