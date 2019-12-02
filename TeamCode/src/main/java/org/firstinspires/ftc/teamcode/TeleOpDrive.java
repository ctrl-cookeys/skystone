package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop_old.Robot;

@TeleOp(name = "T1_TeleDrive", group = "CBot")
//@Disabled
public class TeleOpDrive extends OpMode {

    Robot cBot = null;

    double leftPower;
    double rightPower;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        cBot = Robot.getInstance(this);

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

        cBot.teleop.stop();

    }

    public void driveAround() {

        cBot.teleop.tankDrive();

    }

    private void operateArm() {

        if (gamepad2.a) {
            this.cBot.arm.bringForward(0.9);
        } else if (gamepad2.y) {
            this.cBot.arm.bringBackward(0.9);
        }
    }
}
