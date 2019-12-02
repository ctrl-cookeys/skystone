package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Constants;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;

    private ElapsedTime armRuntime = new ElapsedTime();

    public Arm(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    public void rotate(int target, double power) {

        power = Range.clip(power, 0, 1);
        rotateByEncoderAndHold(target, power);

    }

    public void rotateForward(int target, double power) {

        power = Range.clip(power, 0, 1);
        rotateByEncoderAndHold(-target, power);
    }

    public void rotateBackward(int target, double power) {

        power = Range.clip(power, 0, 1);
        rotateByEncoderAndHold(target, power);
    }

    private void stop() {

        cBot.armRotator.setPower(0);
    }

    private void rotateByEncoderAndHold(int target, double power) {

        double armRotatorPower = power;
        //cBot.armRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        cBot.armRotator.setTargetPosition(target);
        cBot.armRotator.setPower(armRotatorPower);

        cBot.armRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (linearOpMode.opModeIsActive() && cBot.armRotator.isBusy()) {

            if (armRotatorPower != 0.1) {
                // Ramp down the arm power as it approaches the target position
                if (Math.abs(target - cBot.armRotator.getCurrentPosition()) < Constants.Arm.ARM_RAMP_DOWN_ENCODER_TARGET_THRESHOLD) {
                    armRotatorPower = 0.1;
                    cBot.armRotator.setPower(armRotatorPower);
                }
            }

            telemetry.addData("Arm Target Position ", "%d", cBot.armRotator.getTargetPosition());
            telemetry.addData("Arm Current Position", "%d", cBot.armRotator.getCurrentPosition());
            telemetry.addData("armRotatorPower", "%.1f", armRotatorPower);

            telemetry.update();

        }


        // cBot.armRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // cBot.armRotator.setPower(0);


    }


}
