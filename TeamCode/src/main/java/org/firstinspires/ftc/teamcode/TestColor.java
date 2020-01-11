package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.autonomous.DirectDrive;
import org.firstinspires.ftc.teamcode.autonomous.Stone;
import org.firstinspires.ftc.teamcode.autonomous.Robot;

@Autonomous(name = "TestColor", group = "Test")
@Disabled
@Deprecated
public class TestColor extends LinearOpMode {

    private Stone stone;

    private Robot cBot = new Robot();

    @Override
    public void runOpMode() {
        // Initialize Hardware Map - Do this before calling any other method
        cBot.init(hardwareMap);

        stone = new Stone(cBot, telemetry, this);

        waitForStart();

        while (opModeIsActive()) {

            stone.isLeftSensorYellow();
            stone.isRightSensorYellow();
            //telemetry.addData("Left Color Sensor: Is Yellow?", stone.isLeftSensorYellow());
            //telemetry.addData("Right Color Sensor: Is Yellow?", stone.isRightSensorYellow());
            telemetry.update();
            //sleep(2000);
        }
    }

    private void getFirstSkystone() {

        stone.getSkystonePattern();
        if (stone.isPatternA()) {

        } else if (stone.isPatternB()) {

        } else if (stone.isPatternC()) {

        } else {
            // Well what to do now??
        }

    }
}