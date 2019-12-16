package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    private OpMode opMode;
    private Telemetry telemetry;
    private Robot cBot;

    private ElapsedTime motorsRuntime = new ElapsedTime();

    public Intake(Robot cBot, Telemetry telemetry, OpMode opMode) {

        this.cBot = cBot;
        this.opMode = opMode;
        this.telemetry = telemetry;

    }

    public void start() {
    //0.48 was the original speed
        motorsRuntime.reset();
        cBot.leftIntake.setPower(0.4);
        cBot.rightIntake.setPower(0.4);

    }

    public void stop() {

        motorsRuntime.reset();
        cBot.leftIntake.setPower(0);
        cBot.rightIntake.setPower(0);

    }

    public void reverse(){

        cBot.leftIntake.setPower(-0.5);
        cBot.rightIntake.setPower(-0.5);
    }

    public void eject() {

        cBot.leftIntake.setPower(0.7);
        cBot.rightIntake.setPower(0.7);
    }

    public double getRuntime() {

        return motorsRuntime.seconds();

    }

    public double getStoppedTime() {

        return motorsRuntime.seconds();
    }

    public double getLeftIntakePower() {
        return cBot.leftIntake.getPower();
    }

    public double getRightIntakePower() {
        return cBot.leftIntake.getPower();
    }

}