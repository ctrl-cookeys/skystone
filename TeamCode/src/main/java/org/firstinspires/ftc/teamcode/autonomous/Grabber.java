package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Grabber {


    final double MIN_FOUNDATION_POWER = 0.5;
    final double MAX_FOUNDATION_POWER = 1;

    private Telemetry telemetry;
    private Robot cBot;
    private LinearOpMode linearOpMode;
    private double servoPower = MIN_FOUNDATION_POWER; //Initialize the Foundation hook to the starting position

    /*
     * Constructor
     */
    public Grabber(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
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
