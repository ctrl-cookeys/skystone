package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.DirectDrive;
import org.firstinspires.ftc.teamcode.autonomous.Stone;
import org.firstinspires.ftc.teamcode.autonomous.Robot;

@Autonomous(name = "A1_BlueDepotAutoDrive", group = "Auto")
@Disabled
public class BlueDepotAutoDrive extends LinearOpMode {

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
        stone = new Stone(cBot, telemetry, this);

        waitForStart();

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
