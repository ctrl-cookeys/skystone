package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.ColorDistance;
import org.firstinspires.ftc.teamcode.autonomous.DirectDrive;
import org.firstinspires.ftc.teamcode.autonomous.Intake;
import org.firstinspires.ftc.teamcode.autonomous.Robot;
import org.firstinspires.ftc.teamcode.autonomous.Stone;

@Autonomous(name = "A1_BlueDepotAutoColorDrive", group = "LQ")
@Disabled
public class BlueDepotAutoColorDrive extends LinearOpMode {

    private final double DEFAULT_DRIVE_TIMEOUT_SECS = 20.0;
    private final double DEFAULT_DRIVE_POWER = 0.4;
    private final double DEFAULT_ROTATE_POWER = 0.3;

    DirectDrive drive;
    Stone stone;
    Intake intake;
    ColorDistance colorDistance;


    private Robot cBot = new Robot();

    @Override
    public void runOpMode() {

        // Initialize Hardware Map - Do this before calling any other method :)
        cBot.init(hardwareMap);

        drive = new DirectDrive(cBot, telemetry, this);
        stone = new Stone(cBot, telemetry, this);
        intake = new Intake(cBot, telemetry, this);
        colorDistance = new ColorDistance(cBot, telemetry, this);


        //cBot.leftIntake.setPower(0.4);
        //cBot.rightIntake.setPower(0.4);

        waitForStart();

        //intake.start();

       drive.driveByProximity(0.5, 1.2);


        //intake.stop();


        /*drive.forward(2, 0.3, 100);
        intake.stop();
        drive.rotateRight(10, 0.5);
        drive.forward(20, 0.3,100);
         */

    }

    private void deliverSkystones() {
        drive.forward(2, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);
        drive.forward(30, DEFAULT_DRIVE_POWER, DEFAULT_DRIVE_TIMEOUT_SECS);

        if (colorDistance.isPatternA()) {
            deliverStonePatternA();
        } else if (colorDistance.isPatternB()) {
            deliverStonePatternB();
        } else if (colorDistance.isPatternC()) {
            deliverStonePatternC();
        } else {
            // Well what to do now??
        }

    }

    private void deliverStonePatternA() {

    }

    private void deliverStonePatternB() {

    }

    private void deliverStonePatternC() {

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


}
