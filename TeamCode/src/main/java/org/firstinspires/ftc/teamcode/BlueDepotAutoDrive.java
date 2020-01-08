package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.DirectDrive;
import org.firstinspires.ftc.teamcode.autonomous.Flipper;
import org.firstinspires.ftc.teamcode.autonomous.Stone;
import org.firstinspires.ftc.teamcode.autonomous.Robot;
import org.firstinspires.ftc.teamcode.autonomous.Intake;


@Autonomous(name = "A1_BlueDepotAutoDrive", group = "LQ")
//@Disabled
public class BlueDepotAutoDrive extends LinearOpMode {

    private final double DEFAULT_DRIVE_TIMEOUT_SECS = 20.0;
    private final double DEFAULT_DRIVE_POWER        =  0.8;
    private final double DEFAULT_ROTATE_POWER       =  0.3;

    DirectDrive drive;
    Stone stone;
    Intake intake;
    Flipper flipper;

    int driveCounter = 0;

    private Robot cBot = new Robot();

    @Override
    public void runOpMode() {

        // Initialize Hardware Map - Do this before calling any other method :)
        cBot.init(hardwareMap);

        drive = new DirectDrive(cBot, telemetry, this);
        //stone = new Stone(cBot, telemetry, this);
        intake = new Intake(cBot, telemetry, this);
        flipper = new Flipper(cBot, telemetry, this);

        //cBot.leftIntake.setPower(0.4);
        //cBot.rightIntake.setPower(0.4);

        waitForStart();

        while (opModeIsActive()) {

            intake.start();

            if (driveCounter < 1) {

                drive.forward(53, 0.4, 100);//original was 55
                drive.rotateRight(4, DEFAULT_ROTATE_POWER);//3
                sleep(500);
                drive.backward(25,DEFAULT_DRIVE_POWER,100);
                drive.rotateLeft(85,DEFAULT_ROTATE_POWER);
                drive.backward(72, DEFAULT_DRIVE_POWER,100);
                flipper.raise();
                sleep(1000);
                flipper.lower();
                drive.forward(47, DEFAULT_DRIVE_POWER, 100);
                drive.rotateRight(18,DEFAULT_ROTATE_POWER);
                sleep(500);
                drive.forward(25, 0.4, 100);//for second block
                sleep(500);
                drive.backward(25, DEFAULT_DRIVE_POWER, 100);
                drive.rotateLeft(25,DEFAULT_ROTATE_POWER);
                drive.backward(56, DEFAULT_DRIVE_POWER, 100); //original was 60
                flipper.raise();
                sleep(1000);
                flipper.lower();
               drive.forward(22, DEFAULT_DRIVE_POWER, 100);
               // drive.rotateRight(15,DEFAULT_ROTATE_POWER); //new
                //drive.forward(10, DEFAULT_DRIVE_POWER, 100);



            }

            if (driveCounter < 1) {
                driveCounter++;
            }

        }

        //intake.start();
        //drive.forward(50, 0.8, 100);
        //drive.rotateRight(3, 1);
        //intake.stop();

        /*
        drive.forward(2, 0.3, 100);
        intake.stop();
        drive.rotateRight(10, 0.5);
        drive.forward(20, 0.3,100);
        */

    }

    private void getFirstSkystone() {
        drive.forward(2, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.forward(30, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);

        stone.getSkystonePattern();
        if (stone.isPatternA()) {

        } else if (stone.isPatternB()) {

        } else if (stone.isPatternC()) {

        } else {
            // Well what to do now??
        }

    }

    /** Deliver 1st Stone Count
     *  From Team's POV to deliver 1st stone from the left
     *  0. Place the robot backwards along the wall
     *  1. slow start
     *  2. drive forward to the 6 stones and go past stones 2 and 3 from left
     *     goal is to not disturb the 4th stone from left
     *  From Opposite POV
     *  3. turn left
     *  4. Move forward
     *  5. Turn further left to be almost parallel to the right wall
     *  6. Drive forward and deliver the first stone
     **/

    private void deliverFirstStone(){

        // Capture/Deliver stone 1
        drive.backward(2, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.backward(38, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(18, DEFAULT_ROTATE_POWER);
        drive.forward(30, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(45, DEFAULT_ROTATE_POWER);
        drive.forward(22, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);

    }

    /** Deliver 2nd Stone Count
     *  From Team's POV to deliver 4th stone from the left
     *  7. Drive backward all the way to the wall and auto-align
     *  8. Turn Left
     *  9. Move Forward
     *  10. Turn Right
     *  11. Drive Forward to deliver the 2nd Stone
     */

    private void deliverSecondStone() {

        drive.backward(65, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateLeft(54, DEFAULT_ROTATE_POWER);
        drive.forward(7, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateRight(60, DEFAULT_ROTATE_POWER);
        drive.forward(47, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);

    }

    /** Park under the bridge
     *  12. Drive Backward to park
     *  13. Slight Turn
     */
    private void parkUnderBridge() {

        drive.backward(10, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.rotateRight(3, DEFAULT_ROTATE_POWER);
    }


}
