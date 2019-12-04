package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive {

    private OpMode opMode;
    private Telemetry telemetry;
    private Robot cBot;

    private Gamepad gamepad1;

    /*
     * Constructor
     */
    public Drive(Robot cBot, Telemetry telemetry, OpMode opMode) {


        this.cBot = cBot;
        this.opMode = opMode;
        this.telemetry = telemetry;

        this.gamepad1 = opMode.gamepad1;

        resetDrive();

    }

    public void tankDrive() {

        double leftPower;
        double rightPower;

        leftPower = Range.clip(-this.gamepad1.left_stick_y, -1, 1);
        rightPower = Range.clip(-this.gamepad1.right_stick_y, -1, 1);

        cBot.leftDrive.setPower(leftPower);
        cBot.rightDrive.setPower(rightPower);
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

        cBot.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cBot.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

}