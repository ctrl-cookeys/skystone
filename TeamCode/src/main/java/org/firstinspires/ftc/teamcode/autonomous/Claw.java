package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;

    // TODO: Declare and assign a reasonable value to the following constants. Note by convention Constants need to be uppercase
    // MIN_CLAW_POSITION
    // MAX_CLAW_POSITION
    // INCREMENT


    public Claw(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        // DO NOT CHANGE this, but you will need to use the following variables
        // See Arm.java or Drive.java for reference
        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    public void open(double position) {
        // TODO: Complete code for this method to open the claw for a certain position
        // Note: claw is a servo motor
        // Note: this is for autonomous mode
    }

    public void close(double position) {

        // TODO: Complete code for this method to close the claw for a certain position
        // Note: claw is a servo motor
        // Note: this is for autonomous mode

    }

}
