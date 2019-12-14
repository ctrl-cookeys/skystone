package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Flipper {


    private Telemetry telemetry;
    private Robot cBot;
    private LinearOpMode linearOpMode;

    final double MIN_FLIPPER_POWER = 0.1;
    final double MAX_FLIPPER_POWER = 0.5;


    private double servoPower = MIN_FLIPPER_POWER; //Initialize the FLIPPER to the starting position

    /*
     * Constructor
     */
    public Flipper(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }
public void raise(){
    cBot.flipper.setPosition(MAX_FLIPPER_POWER);
}
public void lower(){
    cBot.flipper.setPosition(MIN_FLIPPER_POWER);

}


}
