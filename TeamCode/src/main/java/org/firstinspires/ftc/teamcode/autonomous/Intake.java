package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

        //TODO: Power has changed from 0.2 to 0.3; will this affect the TeleOp program?
        cBot.leftIntake.setPower(0.5); //changed from 2 to 3
        cBot.rightIntake.setPower(0.5); //changed from 2 to 3
    }

    public void start(double leftIntakePower, double rightIntakePower) {
        cBot.leftIntake.setPower(Range.clip(leftIntakePower, 0, 1)); //changed from 2 to 3
        cBot.leftIntake.setPower(Range.clip(rightIntakePower, 0, 1)); //changed from 2 to 3

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