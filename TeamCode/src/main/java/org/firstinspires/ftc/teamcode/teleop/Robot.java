package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * 1. DcMotor          :  Left  drive motor      : "left_drive"
 * 2. DcMotor          :  Right drive motor      : "right_drive"
 * 3. DcMotor          :  Left  intake motor     : "left_intake"
 * 4. DcMotor          :  Right intake motor     : "right_intake"
 * 7. Servo            :  Servo to flip stones   : "flipper"
 * 8. ColorSensor      :  Left Color Sensor      : "left_color"
 * 9. ColorSensor      :  Right Color Sensor     : "right_color"
 */


public class Robot
{
    public DcMotor leftDrive   = null;
    public DcMotor rightDrive  = null;

    protected DcMotor leftIntake   = null;
    protected DcMotor rightIntake = null;



    protected ColorSensor leftColorSensor = null;
    protected ColorSensor rightColorSensor = null;

    protected Servo flipper = null;
    public Servo grabber = null;

    protected BNO055IMU imu;

    /* local OpMode members. */
    HardwareMap hwMap =  null;

    /* Constructor */
    public Robot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        initDriveMotors();
        initIntakeMotors();
        //initColorSensors();
        initGrabber();
        initFlipper();
        initImu();

    }

    /**
     *  Define and Initialize Drive Motors
     */
    private void initDriveMotors() {


        leftDrive    = hwMap.get(DcMotor.class, "left_drive");
        rightDrive   = hwMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Set motors to apply brakes whenever power is set to 0
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);

    }

    /**
     *  Define and Initialize Intake Motors
     */
    private void initIntakeMotors() {

        leftIntake    = hwMap.get(DcMotor.class, "left_intake");
        rightIntake   = hwMap.get(DcMotor.class, "right_intake");

        leftIntake.setDirection(DcMotor.Direction.REVERSE);
        rightIntake.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        leftIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftIntake.setPower(0);
        rightIntake.setPower(0);

    }


    /**
     *  Define and Initialize Color Sensors
     */
    private void initColorSensors(){

        leftColorSensor = hwMap.get(ColorSensor.class, "left_color");
        rightColorSensor = hwMap.get(ColorSensor.class, "right_color");
    }

    private void initFlipper() {

        flipper = hwMap.get(Servo.class, "flipper");

        // To be initialized with other parameters

    }

    private void initGrabber() {

        grabber = hwMap.get(Servo.class, "grabber");

        // To be initialized with other parameters

    }

    /**
     *  Define and Initialize IMU
     */
    private void initImu(){

        // IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

}
