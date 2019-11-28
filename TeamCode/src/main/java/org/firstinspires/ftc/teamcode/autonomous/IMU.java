package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Deprecated
public class IMU {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;

    // change to private final.. here and all others arm, driveTrain etc..
    private BNO055IMU imu;

    private Orientation lastAngles = new Orientation();

    private double globalAngle;
    private double power = .1;
    private double correction;

    private DcMotor leftDrive;
    private DcMotor rightDrive;

    private DriveTrain driveTrain;

    /*
     * Constructor
     */
    public IMU(LinearOpMode linearOpMode) {

        this.linearOpMode = linearOpMode;
        this.telemetry = this.linearOpMode.telemetry;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        imu = this.linearOpMode.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        // make sure the imu gyro is calibrated before continuing.
        while (!this.linearOpMode.isStopRequested() && !imu.isGyroCalibrated())
        {
            this.linearOpMode.sleep(50);
            this.linearOpMode.idle();
        }

        driveTrain = new DriveTrain(this.linearOpMode);

    }

    public void rotateLeft(int degrees, double power) {

        rotate(degrees, power);

    }

    public void rotateRight(int degrees, double rotateLeftPower, double rotateRightPower) {

        rotate(-degrees, power);

    }
/*
    public void drive(DriveTrain.Direction direction, double power, double distanceInches, double timeoutSeconds) {

        // Use imu (gyro) to drive in a straight line.
        // This is from : https://stemrobotics.cs.pdx.edu/node/7266
        double imuCorrection = checkDirection();
        this.telemetry.addData("IMU Correction:","%.2f", imuCorrection);

        driveTrain.drive(direction, (power - imuCorrection), distanceInches, timeoutSeconds);

    }*/


    /**
     * Resets the cumulative angle tracking to zero.
     */
    private void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     * @return Angle in degrees. + = left, - = right.
     */
    private double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    /**
     * See if we are moving in a straight line and if not return a power correction value.
     * @return Power adjustment, + is adjust left - is adjust right.
     */
    public double checkDirection()
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .10;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
     * @param degrees Degrees to turn, + is left - is right
     */
    private void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = power;
            rightPower = -power;
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = -power;
            rightPower = power;
        }
        else return;

        // set power to rotate.
        this.leftDrive.setPower(leftPower);
        this.rightDrive.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (this.linearOpMode.opModeIsActive() && getAngle() == 0) {}

            while (this.linearOpMode.opModeIsActive() && getAngle() > degrees) {}
        }
        else    // left turn.
            while (this.linearOpMode.opModeIsActive() && getAngle() < degrees) {}

        // turn the motors off.
        this.rightDrive.setPower(0);
        this.leftDrive.setPower(0);

        // wait for rotation to stop.
        this.linearOpMode.sleep(100);

        // reset angle tracking on new heading.
        resetAngle();
    }

}
