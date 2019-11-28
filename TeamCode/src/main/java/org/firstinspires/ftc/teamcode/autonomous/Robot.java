package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class Robot {

    // Singleton Instance
    public static Robot INSTANCE = null;

    public Drive drive;

    public Arm arm;

    private Robot(LinearOpMode linearOpMode) {

        //driveTrain = new DriveTrain(linearOpMode);
        drive = new org.firstinspires.ftc.teamcode.autonomous.Drive(linearOpMode);
        arm = new org.firstinspires.ftc.teamcode.autonomous.Arm(linearOpMode);
        //imu = new IMU(linearOpMode);
    }

    public static Robot getInstance(LinearOpMode linearOpMode) {

        if (INSTANCE == null) {
            INSTANCE = new Robot(linearOpMode);
        }

        return INSTANCE;
    }

}
