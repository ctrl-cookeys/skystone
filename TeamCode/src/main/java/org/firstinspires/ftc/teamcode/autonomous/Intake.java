package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


    public class Intake {

        private LinearOpMode linearOpMode;
        private Telemetry telemetry;
        private Robot cBot;

        private ElapsedTime armRuntime = new ElapsedTime();

        public Intake(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

            this.cBot = cBot;
            this.linearOpMode = linearOpMode;
            this.telemetry = telemetry;

        }

        }