package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.Flipper;
import org.firstinspires.ftc.teamcode.teleop.Intake;
import org.firstinspires.ftc.teamcode.teleop.Robot;
import org.firstinspires.ftc.teamcode.teleop.Drive;
import org.firstinspires.ftc.teamcode.teleop.Grabber;



@TeleOp(name = "T1_TeleDrive", group = "LQ")
//@Disabled
public class TeleOpDrive extends OpMode {

// Define objects in class
    private Drive drive;
    private Intake intake;
    private Flipper flipper;
    private Grabber grabber;

    private Robot cBot = new Robot();

    double leftPower;
    double rightPower;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {


        cBot.init(hardwareMap);

        drive = new Drive(cBot, telemetry, this);
        intake = new Intake(cBot, telemetry, this);
        flipper = new Flipper(cBot, telemetry, this);
        grabber = new Grabber(cBot, telemetry, this);

    }

    /**
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        driveAround();
        operateIntake();
        operateFlipper();
        operateGrabber();

    }

    /**
     * Code to run ONCE when the driver hits STOP
     */
    @Override
    public void stop() {

        drive.stop();
        flipper.lower();
        intake.stop();

    }

    public void driveAround() {

        drive.tankDrive();

    }


    private void operateIntake() {

        if (gamepad2.a) {
            intake.start();
        } else if (gamepad2.x) {
            intake.stop();
        } else if (gamepad2.b) {
            intake.reverse();
        } else if (gamepad2.y) {
            intake.start();
        }

    }

   private void operateFlipper(){
        if(gamepad2.right_trigger > 0) {
            flipper.lower();
        } else {
            flipper.raise();
        }
    }

    private void operateGrabber(){
        if(gamepad1.a) {
            grabber.engage();
        } else if (gamepad1.b) {
            grabber.release();
        }
    }
}
