package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleop.Robot;

public class Grabber {


    final double MIN_FOUNDATION_POWER = 0.5;
    final double MAX_FOUNDATION_POWER = 1;

    private Telemetry telemetry;
    private Robot cBot;
    private OpMode opMode;

    private double servoPower = MIN_FOUNDATION_POWER; //Initialize the Foundation hook to the starting position

    /*
     * Constructor
     */
    public Grabber(Robot cBot, Telemetry telemetry, OpMode opMode) {

        this.cBot = cBot;
        this.opMode = opMode;
        this.telemetry = telemetry;

        release();
    }

    public void engage() {

        cBot.grabber.setPosition(MAX_FOUNDATION_POWER);
    }

    public void release() {
        cBot.grabber.setPosition(MIN_FOUNDATION_POWER);

    }


}
