package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.DirectDrive;
import org.firstinspires.ftc.teamcode.autonomous.Grabber;
import org.firstinspires.ftc.teamcode.autonomous.Robot;


@Autonomous(name = "RedTapeAutoFoundationPark", group = "LQ")
//@Disabled
public class RedTapeAutoFoundationPark extends LinearOpMode {

    private final double DEFAULT_DRIVE_TIMEOUT_SECS = 20.0;
    private final double DEFAULT_DRIVE_POWER = 0.8;
    private final double DEFAULT_ROTATE_POWER = 0.3;

    DirectDrive drive;
    Grabber grabber;


    private Robot cBot = new Robot();

    @Override
    public void runOpMode() {

        // Initialize Hardware Map - Do this before calling any other method :)
        cBot.init(hardwareMap);

        drive = new DirectDrive(cBot, telemetry, this);
        grabber = new Grabber(cBot, telemetry, this);


        waitForStart();
        //sleep(5000);
        moveFoundation();
    }




    private void moveFoundation() {





        drive.backward(18, 0.6, DEFAULT_DRIVE_TIMEOUT_SECS);
     //   sleep(500);
        drive.backward(10, 0.2, DEFAULT_DRIVE_TIMEOUT_SECS);
     //   sleep(1000);
        grabber.engage();
        sleep(500);
        drive.forward(30, 0.5, DEFAULT_DRIVE_TIMEOUT_SECS); //org 25
     //   sleep(500);
        curveDrive(0.2, 0.7, 1000);
        grabber.release();
        sleep(500);
        curveDrive(0, 0.5, 1000);
        drive.backward(15, 0.2, DEFAULT_DRIVE_TIMEOUT_SECS);
       // sleep(500);
        drive.rotateRight(40, DEFAULT_ROTATE_POWER);
        drive.backward(35, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(63, DEFAULT_ROTATE_POWER);
        drive.forward(35, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateRight(90,DEFAULT_ROTATE_POWER);
        drive.forward(35, 0.6, DEFAULT_DRIVE_TIMEOUT_SECS);






        //
        //grabber.engage();
       // grabber.release();
        //drive.rotateLeft(54, DEFAULT_ROTATE_POWER);
        //drive.forward(7, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        //drive.rotateRight(60, DEFAULT_ROTATE_POWER);
        //drive.forward(47, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);

    }

    /**
     * Park under the bridge
     * 12. Drive Backward to park
     * 13. Slight Turn
     */
    private void parkUnderBridge() {

        drive.backward(10, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateRight(3, DEFAULT_ROTATE_POWER);
    }

    private void curveDrive(double leftPower, double rightPower, int sleepTimeMilliseconds) {
        cBot.leftDrive.setPower(leftPower);
        cBot.rightDrive.setPower(rightPower);
        sleep(sleepTimeMilliseconds);
        cBot.leftDrive.setPower(0);
        cBot.rightDrive.setPower(0);
    }


}
