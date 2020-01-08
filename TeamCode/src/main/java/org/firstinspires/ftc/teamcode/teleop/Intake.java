package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//Define objects in the class
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

    // Set start speed for left and right intake wheels
    public void start() {
    //0.48 was the original speed
        motorsRuntime.reset();
        cBot.leftIntake.setPower(0.5);
        cBot.rightIntake.setPower(0.5);

    }

    // Set stop speed for left and right intake wheels
    public void stop() {

        motorsRuntime.reset();
        cBot.leftIntake.setPower(0);
        cBot.rightIntake.setPower(0);

    }

    // Set reverse speed for left and right intake wheels
    public void reverse(){

        cBot.leftIntake.setPower(-0.15);
        cBot.rightIntake.setPower(-0.15);
    }

    // If the stone gets stuck in the arms of the intake, then we need to "spit the stone out"
    //The following method will eject the stone out of the intake's grasp
    public void eject() {

        cBot.leftIntake.setPower(0.15);
        cBot.rightIntake.setPower(0.15);
    }

    //Provide robot with the run time
    public double getRuntime() {

        return motorsRuntime.seconds();

    }

    //Provide robot with the time to stop at
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