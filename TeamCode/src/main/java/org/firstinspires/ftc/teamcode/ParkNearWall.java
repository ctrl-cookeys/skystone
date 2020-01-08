package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.DirectDrive;
import org.firstinspires.ftc.teamcode.autonomous.Robot;


@Autonomous(name = "ParkNearWall", group = "LQ")
//@Disabled
public class ParkNearWall extends LinearOpMode {

    private final double DEFAULT_DRIVE_TIMEOUT_SECS = 20.0;
    private final double DEFAULT_DRIVE_POWER = 0.8;
    private final double DEFAULT_ROTATE_POWER = 0.3;

    DirectDrive drive;


    private Robot cBot = new Robot();

    @Override
    public void runOpMode() {

        // Initialize Hardware Map - Do this before calling any other method :)
        cBot.init(hardwareMap);

        drive = new DirectDrive(cBot, telemetry, this);


        waitForStart();

        //sleep(5000);

        parkUnderBridge();

    }

    /**
     * Park under the bridge
     * 12. Drive Backward to park
     * 13. Slight Turn
     */
    private void parkUnderBridge() {

        drive.forward(32, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);


    }


}
