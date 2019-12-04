package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.Arm;
import org.firstinspires.ftc.teamcode.teleop.Robot;
import org.firstinspires.ftc.teamcode.teleop.Drive;


@TeleOp(name = "T1_TeleDrive", group = "CBot")
//@Disabled
public class TeleOpDrive extends OpMode {


    private Drive drive;
    private Arm arm;

    private Robot cBot = new Robot();

    double leftPower;
    double rightPower;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        cBot.init(hardwareMap);

        drive = new org.firstinspires.ftc.teamcode.teleop.Drive(cBot, telemetry, this);
        arm = new org.firstinspires.ftc.teamcode.teleop.Arm(cBot, telemetry, this);

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
        operateArm();
    }

    /**
     * Code to run ONCE when the driver hits STOP
     */
    @Override
    public void stop() {

        drive.stop();

    }

    public void driveAround() {

        drive.tankDrive();

    }

    private void operateArm() {

        if (gamepad2.a) {
            arm.bringForward(0.9);
        } else if (gamepad2.y) {
            arm.bringBackward(0.9);
        }
    }
}
