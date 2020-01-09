package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.Drive;
import org.firstinspires.ftc.teamcode.teleop.Grabber;
import org.firstinspires.ftc.teamcode.teleop.Robot;


@TeleOp(name = "TestGrabber", group = "LQ")
//@Disabled
public class TestGrabber extends OpMode {

// Define objects in class
    private Grabber grabber;
    private Drive drive;

    private boolean leftDpadPressed = false;

    private Robot cBot = new Robot();

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {


        cBot.init(hardwareMap);

        drive = new Drive(cBot, telemetry, this);
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
        operateGrabber();

/*
        if (gamepad1.left_bumper) {
            leftDpadPressed = false;
        } else if (gamepad1.right_bumper) {
            leftDpadPressed = true;
        }

        if (leftDpadPressed = false) {
            cBot.leftDrive.setPower(0.2);
            cBot.rightDrive.setPower(0.1);
        } else if (leftDpadPressed = true) {
            cBot.leftDrive.setPower(0);
            cBot.rightDrive.setPower(0);
        }*/



        //moveFoundationAtEndGame();

    }

    public void driveAround() {

        drive.tankDrive();

    }

   private void operateGrabber(){
        if(gamepad1.a) {
            grabber.engage();
        } else if (gamepad1.b) {
            grabber.release();
        }
    }

}
