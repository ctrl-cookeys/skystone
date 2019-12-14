package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;

    private ElapsedTime motorsRuntime = new ElapsedTime();

    public Intake(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    public void start() {
// Original power = 0.6
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

        cBot.leftIntake.setPower(-1);
        cBot.rightIntake.setPower(-1);
    }

    public void eject() {

        cBot.leftIntake.setPower(1);
        cBot.rightIntake.setPower(1);
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