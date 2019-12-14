package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.autonomous.Stone;
import org.firstinspires.ftc.teamcode.autonomous.Robot;

@TeleOp(name = "TestColor", group = "Test")
public class TestColor extends OpMode {

    private Robot cBot;
    private Stone stone;

    @Override
    public void init() {

        cBot.init(hardwareMap);

        stone = new Stone(cBot, telemetry, this);

    }

    /**
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }


    @Override
    public void loop() {

        telemetry.addData("Left Color Senor: Yellow: ", stone.isLeftSensorYellow());
        telemetry.addData("Right Color Senor: Yellow: ", stone.isRightSensorYellow());


        /*stone.getSkystonePattern();
        if (stone.isPatternA()) {

        } else if (stone.isPatternB()) {

        } else if (stone.isPatternC()) {

        } else {
            // Well what to do now??
        }
        */

    }

    /**
     * Code to run ONCE when the driver hits STOP
     */
    @Override
    public void stop() {

    }
}