package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

@Deprecated
public class DriveTrain {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;

    private DcMotor leftDrive;
    private DcMotor rightDrive;

    private ElapsedTime driveRuntime = new ElapsedTime();

    /*
     * Constructor
     */
    public DriveTrain(LinearOpMode linearOpMode) {

        this.linearOpMode = linearOpMode;
        this.telemetry = this.linearOpMode.telemetry;

        this.leftDrive = this.linearOpMode.hardwareMap.dcMotor.get("left_drive");
        this.rightDrive = this.linearOpMode.hardwareMap.dcMotor.get("right_drive");


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
    public void drive(Direction direction, double power, double distanceInches, double timeoutSeconds) {

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
    public void forward(double power, double distanceInches, double timeoutSeconds) {

        driveByEncoder(power, distanceInches, distanceInches, timeoutSeconds);

    }

    /**
     * Drive backward
     **/
    public void backward(double power, double distanceInches, double timeoutSeconds) {

        driveByEncoder(power, -distanceInches, -distanceInches, timeoutSeconds);

    }

    /**
     * Turn Left
     **/
    public void turnLeft(double power, double distanceInches, double timeoutSeconds) {

        driveByEncoder(power, -distanceInches, distanceInches, timeoutSeconds);

    }

    /**
     * Turn Right
     **/
    public void turnRight(double power, double distanceInches, double timeoutSeconds) {

        driveByEncoder(power, distanceInches, -distanceInches, timeoutSeconds);

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
            newLeftTarget = this.leftDrive.getCurrentPosition() + (int) (leftInches * Constants.Drive.COUNTS_PER_INCH);
            newRightTarget = this.rightDrive.getCurrentPosition() + (int) (rightInches * Constants.Drive.COUNTS_PER_INCH);

            // Set Target Position
            this.leftDrive.setTargetPosition(newLeftTarget);
            this.rightDrive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            this.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
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

            // Stop all motion;
            this.leftDrive.setPower(0);
            this.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            this.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            this.linearOpMode.sleep(50);   // optional pause after each move
        }
    }


    private void resetDrive() {

        this.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public enum Direction {

        FORWARD,
        BACKWARD,
        LEFT_TURN,
        RIGHT_TURN
    }
}