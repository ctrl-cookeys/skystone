package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.Drive;
import org.firstinspires.ftc.teamcode.autonomous.Robot;


@Disabled
@Autonomous(name = "AutoTest", group = "Test")
public class AutoTest extends LinearOpMode {

    Robot cBot = new Robot();

    @Override
    public void runOpMode() {

        cBot.init(hardwareMap);

        Drive drive = new Drive(cBot, telemetry, this);




        waitForStart();

        drive.backward(18, 0.5, 4);
        drive.rotateLeft(90, 0.2);
    }
}
