package org.firstinspires.ftc.teamcode.autonomous_old;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Deprecated
public final class Robot {

    // Singleton Instance
    public static Robot INSTANCE = null;

    public Drive drive;

    public Arm arm;

    private Robot(LinearOpMode linearOpMode) {

        drive = new org.firstinspires.ftc.teamcode.autonomous_old.Drive(linearOpMode);
        arm = new org.firstinspires.ftc.teamcode.autonomous_old.Arm(linearOpMode);
    }

    public static Robot getInstance(LinearOpMode linearOpMode) {

        if (INSTANCE == null) {
            INSTANCE = new Robot(linearOpMode);
        }

        return INSTANCE;
    }

}
