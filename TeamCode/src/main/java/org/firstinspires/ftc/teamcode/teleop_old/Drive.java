package org.firstinspires.ftc.teamcode.teleop_old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Deprecated
public class Drive {

    private final OpMode opMode;
    private final Telemetry telemetry;

    private final DcMotor leftDrive;
    private final DcMotor rightDrive;

    private final Gamepad gamepad1;

    /*
     * Constructor
     */
    public Drive(OpMode opMode) {

        this.opMode = opMode;
        this.telemetry = this.opMode.telemetry;

        this.leftDrive = this.opMode.hardwareMap.dcMotor.get("left_drive");
        this.rightDrive = this.opMode.hardwareMap.dcMotor.get("right_drive");

        this.leftDrive.setDirection(DcMotor.Direction.FORWARD);
        this.rightDrive.setDirection(DcMotor.Direction.REVERSE);

        this.gamepad1 = opMode.gamepad1;

        resetDrive();

    }

    public void tankDrive() {

        double leftPower;
        double rightPower;

        leftPower = Range.clip(-this.gamepad1.left_stick_y, -1, 1);
        rightPower = Range.clip(-this.gamepad1.right_stick_y, -1, 1);

        this.leftDrive.setPower(leftPower);
        this.rightDrive.setPower(rightPower);
    }

    public void povDrive() {

    }

    public void stop() {

        this.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.leftDrive.setPower(0);
        this.rightDrive.setPower(0);
    }

    private void resetDrive() {

        this.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

}