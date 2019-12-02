package org.firstinspires.ftc.teamcode.teleop_old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Deprecated
public class Robot {

    // Singleton Instance
    public static Robot INSTANCE = null;

    public Drive teleop;
    public Arm arm;


    private Robot(OpMode opMode) {

        teleop = new Drive(opMode);
        arm = new Arm(opMode);

    }

    public static Robot getInstance(OpMode opMode) {

        if (INSTANCE == null) {
            INSTANCE = new Robot(opMode);
        }

        return INSTANCE;
    }

}
