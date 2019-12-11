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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="LiftTest", group="Lift")
//@Disabled

public class LiftTest extends OpMode {

    // Declare OpMode members.
    private Servo leftLift;
    private Servo rightLift;
    private Servo testServo;
    private Servo testServo1;


    private double servoTargetPosition = 0.0;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        //leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        //rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        //leftDrive.setDirection(DcMotor.Direction.FORWARD);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);



        leftLift = hardwareMap.get(Servo.class,"left_lift");
        rightLift = hardwareMap.get(Servo.class,"right_lift");

        testServo = hardwareMap.get(Servo.class,"test_servo");
        testServo1 = hardwareMap.get(Servo.class,"test_servo1");


        leftLift.scaleRange(0.3,0.7);
        rightLift.scaleRange(0.3,0.7);
        testServo.scaleRange(0.3,0.7);
        testServo1.scaleRange(0.3,0.7);

        leftLift.setDirection(Servo.Direction.FORWARD);
        rightLift.setDirection(Servo.Direction.REVERSE);
        testServo.setDirection(Servo.Direction.FORWARD);
        testServo1.setDirection(Servo.Direction.REVERSE);


        // leftLift.setPosition(0);
        //  rightLift.setPosition(0);

        //  testServo.setPosition(0);
        //  testServo1.setPosition(0);



        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() { }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        if (gamepad1.right_trigger > 0)
        {
            servoTargetPosition = Range.clip (servoTargetPosition + 0.001, 0.0, 1.0);
            testServo.setPosition(servoTargetPosition);
            testServo1.setPosition(servoTargetPosition);

        }

        if (gamepad1.left_trigger > 0) {

            servoTargetPosition = Range.clip (servoTargetPosition - 0.001, 0.0, 1.0);
            testServo.setPosition(servoTargetPosition);
            testServo1.setPosition(servoTargetPosition);
        }



        if (gamepad2.right_trigger > 0)
        {
            servoTargetPosition = Range.clip (servoTargetPosition + 0.001, 0.0, 1.0);
            leftLift.setPosition(servoTargetPosition);
            rightLift.setPosition(servoTargetPosition);

        }

        if (gamepad2.left_trigger > 0) {

            servoTargetPosition = Range.clip (servoTargetPosition - 0.001, 0.0, 1.0);
            leftLift.setPosition(servoTargetPosition);
            rightLift.setPosition(servoTargetPosition);

        }

        telemetry.addData("Servo Position", "%.2f", servoTargetPosition);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
