package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.Robot;
import org.firstinspires.ftc.teamcode.autonomous.Arm;

@Autonomous(name = "ResetArm (Check values before running)", group = "Auto")
//@Disabled
public class ResetArm extends LinearOpMode {

    private final double DEFAULT_ROTATE_POWER = 0.3;

    Arm arm;

    Robot cBot = new Robot();

    @Override
    public void runOpMode() {

        // Initialize Hardware Map - Do this before calling any other method
        cBot.init(hardwareMap);

        arm = new Arm(cBot, telemetry, this);

        //telemetry.setAutoClear(false);

        telemetry.addLine("Make sure you have set the target value");
        telemetry.addLine("in the code correctly before hitting Play");

        telemetry.addLine("If you are not sure, then STOP, and ");
        telemetry.addLine("check/fix the values first");
        telemetry.update();

        waitForStart();

        arm.rotate(500, DEFAULT_ROTATE_POWER);


    }
}