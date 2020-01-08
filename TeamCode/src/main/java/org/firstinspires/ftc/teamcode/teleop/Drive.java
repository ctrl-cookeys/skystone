package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;


// Define objects in the class
public class Drive {

    private ElapsedTime driveRuntime = new ElapsedTime();
    private OpMode opMode;
    private Telemetry telemetry;
    private Robot cBot;
    private Gamepad gamepad1;

    /*
     * Constructor
     */

    // the operation "this" redefines objects in the class
    public Drive(Robot cBot, Telemetry telemetry, OpMode opMode) {


        this.cBot = cBot;
        this.opMode = opMode;
        this.telemetry = telemetry;

        this.gamepad1 = opMode.gamepad1;

        resetDrive();

    }

    // Using tank drive,
    public void tankDrive() {

        // The left power and right power should be allowed to have a power value of any integer or decimal.
        // Double allows the power values to be written in decimals as well
        double leftPower;
        double rightPower;

        // leftPower = Range.clip(-this.gamepad1.left_stick_y, -1, 1);
        // rightPower = Range.clip(-this.gamepad1.right_stick_y, -1, 1);

        //Set powers of left and right motor and designate them to controls on the gamepad

        leftPower = Range.clip(-this.gamepad1.left_stick_y, -1, 1);
        rightPower = Range.clip(-this.gamepad1.right_stick_y, -1, 1);

        cBot.leftDrive.setPower(leftPower);
        cBot.rightDrive.setPower(rightPower);
    }

    // Stop the drive from running
    public void stop() {

        cBot.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cBot.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        cBot.leftDrive.setPower(0);
        cBot.rightDrive.setPower(0);
    }

    // Reset the encoders so that you can run the program multiple times
    private void resetDrive() {

        cBot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cBot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        cBot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cBot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        cBot.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cBot.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    /**
     * Drive forward
     **/
    public void forward(double power) {

        driveByEncoder(power);

    }

    /**
     * Drive using Encoders
     **/
    private void driveByEncoder(double power) {


        // Range Clip (Don't think this is actually needed)
        power = Range.clip(power, 0, 1);

        cBot.leftDrive.setPower(Math.abs(power));
        cBot.rightDrive.setPower(Math.abs(power)/2);


    }
}
