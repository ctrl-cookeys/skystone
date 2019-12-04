package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.Constants.Arm.ARM_RAMP_DOWN_ENCODER_TARGET_THRESHOLD;

public class Arm {

    private Telemetry telemetry;
    private Robot cBot;
    private OpMode opMode;

    private Gamepad gamepad2;

    /*
     * Constructor
     */
    public Arm(Robot cBot, Telemetry telemetry, OpMode opMode) {

        this.cBot = cBot;
        this.opMode = opMode;
        this.telemetry = telemetry;
        this.gamepad2 = opMode.gamepad2;

        resetArm();
    }

    public void bringForward(double power) {
        rotateArmAndHold(cBot.armRotator.getCurrentPosition() + 500, power);
    }

    public void bringBackward(double power) {
        rotateArmAndHold(cBot.armRotator.getCurrentPosition() - 500, power);
    }

    //Sets the desired encoder target position to which the motor should advance or retreat and then actively hold thereat.
    private void rotateArmAndHold(int encoderTargetPosition, double power) {

        double armRotatorPower;

        // Set Encoder Target Position
        cBot.armRotator.setTargetPosition(encoderTargetPosition);

        /*
         * Set Motor Mode to DcMotor.RunMode.RUN_TO_POSITION
         * Note: setTargetPosition must be done prior to setting DcMotor.RunMode.RUN_TO_POSITION
         */
        cBot.armRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Ramp down the arm power as it approaches the target position
        if (Math.abs(encoderTargetPosition - cBot.armRotator.getTargetPosition()) > ARM_RAMP_DOWN_ENCODER_TARGET_THRESHOLD)
            armRotatorPower = Math.abs(power);
        else
            armRotatorPower = 0.1;

        cBot.armRotator.setPower(armRotatorPower);

    }

    private void resetArm() {

        cBot.armRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cBot.armRotator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cBot.armRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void stop() {

        cBot.armRotator.setPower(0);
    }

}