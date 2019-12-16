package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.DirectDrive;
import org.firstinspires.ftc.teamcode.autonomous.Robot;
import org.firstinspires.ftc.teamcode.autonomous.Stone;

@Autonomous(name = "BlueTapeNearNeutral", group = "Auto")
//@Disabled
public class BlueTapeNearNeutral extends LinearOpMode {

    private final double DEFAULT_DRIVE_TIMEOUT_SECS = 20.0;
    private final double DEFAULT_DRIVE_POWER        =  0.4;
    private final double DEFAULT_ROTATE_POWER       =  0.3;

    DirectDrive drive;
    Stone stone;

    private Robot cBot = new Robot();

    @Override
    public void runOpMode() {

        // Initialize Hardware Map - Do this before calling any other method
        cBot.init(hardwareMap);

        drive = new DirectDrive(cBot, telemetry, this);

        waitForStart();
        parkUnderBridge();

        }


    /** Park under the bridge
     *  12. Drive Backward to park
     *  13. Slight Turn
     */
    private void parkUnderBridge() {

        drive.forward(30, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(90,DEFAULT_DRIVE_POWER);
        drive.forward(36, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);

    }


}
