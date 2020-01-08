package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;

    private ElapsedTime intakeRuntime = new ElapsedTime();

    public Intake(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    public void start() {

        cBot.leftIntake.setPower(0.2); //changed from 3 to 2
        cBot.rightIntake.setPower(0.2); //changed from 3 to 2
    }

    public void start(int timeoutSeconds) {

        intakeRuntime.reset();

        while (linearOpMode.opModeIsActive() &&
                (intakeRuntime.seconds() < timeoutSeconds))
        {

            cBot.leftIntake.setPower(0.4);
            cBot.rightIntake.setPower(0.4);
        }
    }




    public void stop() {

        intakeRuntime.reset();
        cBot.leftIntake.setPower(0);
        cBot.rightIntake.setPower(0);

    }

    public void reverse(){

        cBot.leftIntake.setPower(-0.4);
        cBot.rightIntake.setPower(-0.4);
    }

    public void eject() {

        cBot.leftIntake.setPower(1);
        cBot.rightIntake.setPower(1);
    }

    public double getRuntime() {

        return intakeRuntime.seconds();

    }

    public double getStoppedTime() {

        return intakeRuntime.seconds();
    }

    public double getLeftIntakePower() {
        return cBot.leftIntake.getPower();
    }

    public double getRightIntakePower() {
        return cBot.leftIntake.getPower();
    }

}