package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.Robot;


@Autonomous(name = "A1_BlueDepotAutoDrive", group = "Auto")
//@Disabled
public class BlueDepotAutoDrive extends LinearOpMode {

    private final double DEFAULT_DRIVE_TIMEOUT_SECS = 10.0;
    private final double DEFAULT_DRIVE_POWER        =  0.4;
    private final double DEFAULT_ROTATE_POWER       =  0.3;

    Robot cBot;

    @Override
    public void runOpMode() {

        // Init
        cBot = Robot.getInstance(this);

        // Wait for player to hit START
        waitForStart();

        // Run code during Autonomous mode
        while (opModeIsActive()) {

            deliverFirstStone();
            deliverSecondStone();
            parkUnderBridge();
        }

        // Code to run for STOP
        cBot.drive.stop();
        cBot.arm.stop();

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
    private void deliverFirstStone() {

        // Capture/Deliver stone 1
        cBot.drive.backward(2, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        cBot.drive.backward(38, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        cBot.drive.rotateLeft(20, DEFAULT_ROTATE_POWER);
        cBot.drive.forward(30, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        cBot.drive.rotateLeft(45, DEFAULT_ROTATE_POWER);
        cBot.drive.forward(22, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);

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

        cBot.drive.backward(65, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        cBot.drive.rotateLeft(54, DEFAULT_ROTATE_POWER);
        cBot.drive.forward(7, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        cBot.drive.rotateRight(60, DEFAULT_ROTATE_POWER);
        cBot.drive.forward(47, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);

    }

    /** Park under the bridge
     *  12. Drive Backward to park
     *  13. Slight Turn
     */
    private void parkUnderBridge() {

        cBot.drive.backward(10, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        cBot.drive.rotateRight(3, DEFAULT_ROTATE_POWER);
    }

}
