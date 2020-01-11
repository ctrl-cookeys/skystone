package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.DirectDrive;
import org.firstinspires.ftc.teamcode.autonomous.Grabber;
import org.firstinspires.ftc.teamcode.autonomous.Robot;


@Autonomous(name = "BlueTapeAutoFoundationPark", group = "LQ")
//@Disabled
public class BlueTapeAutoFoundationPark extends LinearOpMode {

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


        drive.backward(15, 0.7, DEFAULT_DRIVE_TIMEOUT_SECS);
        sleep(200);
        drive.rotateLeft(90, 0.2);
        drive.backward(25, 0.7, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateRight(90, 0.2);
        drive.backward(15, 0.7, DEFAULT_DRIVE_TIMEOUT_SECS);
        grabber.engage();
        sleep(500);
        drive.forward(15, 0.5, DEFAULT_DRIVE_TIMEOUT_SECS); //org 25
        sleep(500);

        curveDrive(0.3,0.5,4000);

        grabber.release();
        sleep(500);

        drive.forward(10,0.7,100);
        drive.rotateRight(10,0.3);
        drive.backward(38,1,1000);

        /* Uncomment curveDrive if you want to curve and part near the bridge*/
       // curveDrive(0.2,0.3,5000);


        /* Parks near the wall*/
        drive.forward(10, 0.8, 100);
        drive.rotateRight(10, 0.3);
        drive.forward(6, 0.8, 100);
        drive.rotateLeft(10, 0.3);
        drive.forward(15, 0.8, 100);




        //curveDrive(0.3,0.2,5000);


        /*drive.rotateLeft(50,0.4);


        drive.forward(25,1,100);
        drive.rotateRight(70,0.4);
        drive.backward(30,1,100);
        drive.rotateLeft(50,0.3);
        drive.backward(30,1,100);
        drive.rotateLeft(90,0.2);
*/

        /*

        curveDrive(0.5, 0, 1000);
        drive.backward(15, 0.2, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(40, DEFAULT_ROTATE_POWER);
        drive.backward(35, 1, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateRight(63, DEFAULT_ROTATE_POWER);
        drive.forward(35, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(90,DEFAULT_ROTATE_POWER);
        drive.forward(25, 0.6, DEFAULT_DRIVE_TIMEOUT_SECS);

        parkUnderBridge();
*/

    }

    /**
     * Park under the bridge
     * 12. Drive Backward to park
     * 13. Slight Turn
     */
    private void parkUnderBridge() {

        drive.backward(5, 1, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(70, 0.8);
        drive.forward(23, 1, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(20, 0.8);
        drive.forward(10, 1, DEFAULT_DRIVE_TIMEOUT_SECS);



    }

    private void curveDrive(double leftPower, double rightPower, int sleepTimeMilliseconds) {
        cBot.leftDrive.setPower(leftPower);
        cBot.rightDrive.setPower(rightPower);
        sleep(sleepTimeMilliseconds);
        cBot.leftDrive.setPower(0);
        cBot.rightDrive.setPower(0);
    }


}
