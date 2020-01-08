package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleop.Robot;

public class Flipper {


    final double MIN_FLIPPER_POWER = 0;
    final double MAX_FLIPPER_POWER = 1;
    private Telemetry telemetry;
    private Robot cBot;
    private OpMode opMode;


    private double servoPower = MIN_FLIPPER_POWER; //Initialize the FLIPPER to the starting position

    /*
     * Constructor
     */
    public Flipper(Robot cBot, Telemetry telemetry, OpMode opMode) {

        this.cBot = cBot;
        this.opMode = opMode;
        this.telemetry = telemetry;

        raise();

    }

    public void raise() {

        cBot.flipper.setPosition(MAX_FLIPPER_POWER);
    }

    public void lower() {

        cBot.flipper.setPosition(MIN_FLIPPER_POWER);

    }


}
