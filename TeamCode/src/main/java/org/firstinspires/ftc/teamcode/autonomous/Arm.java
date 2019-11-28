package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private DcMotor armRotator;

    private ElapsedTime armRuntime = new ElapsedTime();

    public Arm(LinearOpMode linearOpMode) {

        this.linearOpMode = linearOpMode;
        this.telemetry = this.linearOpMode.telemetry;

        this.armRotator = this.linearOpMode.hardwareMap.dcMotor.get("arm_rotator");

    }

    public void rotate(double power, int target) {

        power = Range.clip(power, 0, 1);
        rotateByEncoderAndHold(power, target);
    }

    public void stop() {

        this.armRotator.setPower(0);
    }

    private void rotateByEncoderAndHold(double power, int target) {

        if(linearOpMode.opModeIsActive()) {
            this.armRotator.setTargetPosition(target);
            this.armRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (Math.abs(target - this.armRotator.getTargetPosition()) < 100)
                power = 0.1;
            this.armRotator.setPower(power);
        }

        this.armRotator.setPower(0);
    }

}