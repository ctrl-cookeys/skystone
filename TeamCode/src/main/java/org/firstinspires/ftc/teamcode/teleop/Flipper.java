package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleop.Robot;

/*
 * The Flipper class is used to push stones off the robot and onto the foundation
 * A back and forth action is used to push the stones
 * The MIN and MAX position are needed for the FLIPPER to function
 * The FLIPPER moves by alternating between its MIN and MAX position
 */ 
public class Flipper {


    private Telemetry telemetry;
    private Robot cBot;
    private OpMode opMode;

    final double MIN_FLIPPER_POWER = 0; //the FLIPPER'S minimum position (0)
    final double MAX_FLIPPER_POWER = 1; //the FLIPPER'S maximum position (1)

    /*
     * Initialize the FLIPPER to the starting position
     */
    private double servoPower = MIN_FLIPPER_POWER;

    /*
     * Constructor
     */
    public Flipper(Robot cBot, Telemetry telemetry, OpMode opMode) {

        this.cBot = cBot;
        this.opMode = opMode;
        this.telemetry = telemetry;

        raise();

    }

    /*
     * Raises the FLIPPER and sets it to its maximum position (1)
     */
public void raise(){
        cBot.flipper.setPosition(MAX_FLIPPER_POWER);
}

/*
 * Lowers the FLIPPER and sets it to its minimum position (0)
 */
public void lower(){

    cBot.flipper.setPosition(MIN_FLIPPER_POWER);

}


}
