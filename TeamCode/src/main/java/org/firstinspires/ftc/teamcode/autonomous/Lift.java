package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {

    private final double MIN_CLAW_POSITION = 0.1;
    private final double MAX_CLAW_POSITION = 0.6;
    private final int    SLEEP_CYCLE_MS = 50;
    private final double INCREMENT = 0.005;

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;


    public Lift(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    public void scan(double position, int scanCount) {

        int count = 0;

        while(count != scanCount) {

            open(position);
            close(position);
            count++;

        }
    }

    public void open(double position) {

        while(linearOpMode.opModeIsActive()) {

            position += INCREMENT;

            cBot.leftLift.setPosition(Range.clip(position, MIN_CLAW_POSITION, MAX_CLAW_POSITION));
            cBot.rightLift.setPosition(Range.clip(position, MIN_CLAW_POSITION, MAX_CLAW_POSITION));

            linearOpMode.sleep(SLEEP_CYCLE_MS);
        }

    }

    public void close(double position) {

        while(linearOpMode.opModeIsActive()) {

            position += INCREMENT;

            cBot.leftLift.setPosition(Range.clip(position, MIN_CLAW_POSITION, MAX_CLAW_POSITION));
            cBot.rightLift.setPosition(Range.clip(position, MIN_CLAW_POSITION, MAX_CLAW_POSITION));

            linearOpMode.sleep(SLEEP_CYCLE_MS);
        }
    }

}
