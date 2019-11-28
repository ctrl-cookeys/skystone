package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.Constants.Arm.ARM_RAMP_DOWN_ENCODER_TARGET_THRESHOLD;

public class Arm {

    private final OpMode opMode;
    private final Telemetry telemetry;

    private final DcMotor armRotator;

    private final Gamepad gamepad2;

    /*
     * Constructor
     */
    public Arm(OpMode opMode) {

        this.opMode = opMode;
        this.telemetry = this.opMode.telemetry;

        this.armRotator = this.opMode.hardwareMap.dcMotor.get("arm_rotator");

        this.armRotator.setDirection(DcMotor.Direction.REVERSE);

        this.gamepad2 = opMode.gamepad2;

        resetArm();

    }

    public void bringForward(double power) {
        rotateArmAndHold(this.armRotator.getCurrentPosition() + 500, power);
    }

    public void bringBackward(double power) {
        rotateArmAndHold(this.armRotator.getCurrentPosition() - 500, power);
    }

    //Sets the desired encoder target position to which the motor should advance or retreat and then actively hold thereat.
    private void rotateArmAndHold(int encoderTargetPosition, double power) {

        double armRotatorPower;

        //encoderTargetPosition = Range.clip(encoderTargetPosition,  1000, 5400) ;

        // Set Encoder Target Position
        this.armRotator.setTargetPosition(encoderTargetPosition);

        /*
         * Set Motor Mode to DcMotor.RunMode.RUN_TO_POSITION
         * Note: setTargetPosition must be done prior to setting DcMotor.RunMode.RUN_TO_POSITION
         */
        this.armRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Ramp down the arm power as it approaches the target positionß
        if (Math.abs(encoderTargetPosition - armRotator.getTargetPosition()) > ARM_RAMP_DOWN_ENCODER_TARGET_THRESHOLD)
            armRotatorPower = Math.abs(power);
        else
            armRotatorPower = 0.1;

        this.armRotator.setPower(armRotatorPower);

    }

    private void resetArm() {

        this.armRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.armRotator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.armRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void stop() {

        this.armRotator.setPower(0);
    }

}