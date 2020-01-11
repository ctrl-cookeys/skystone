package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



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



@TeleOp(name="Basic: TestContinuousDigitalServo", group="Iterative Opmode")
@Disabled
@Deprecated

public class TestContinousDigitalServo extends OpMode     {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private CRServo leftServo,rightServo;

    /* Based on testing, we determined
        a power of -1.13 is the lowest position we want the SkyStone Hopper to go to.
        a power of 0.3 is the farthest position we want the SkyStone Hopper to go to. At this
        position it should be good enough to drop the sky stone on the foundation
    */
    final double MIN_HOPPER_POWER = 0.75;
    final double MAX_HOPPER_POWER = 0.75;

    private double servoPower = MIN_HOPPER_POWER; //Initialize the Hopper to the starting position

     /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        /* Skystone hopper has two Continuous rotation servo. This block of code initializes
        * the two servos.
        * Set the Left Servo in FORWARD direction and Right Servo to the REVERSE direction
        * This is done so that both the servos rotate in the same direction
        * Both the Servo's are set to the starting position
        *  */
        leftServo =   hardwareMap.crservo.get("left_Servo");
        rightServo =  hardwareMap.crservo.get("right_Servo");

        leftServo.setDirection(CRServo.Direction.FORWARD);
        rightServo.setDirection(CRServo.Direction.REVERSE);

        leftServo.setPower(MIN_HOPPER_POWER);
        rightServo.setPower(MIN_HOPPER_POWER);

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
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        /* Use the below piece of code if you want to use the Right and Left Triggers to control
        * the movement of the Hopper*/

       /* if (gamepad2.right_trigger > 0) {
            servoPower += 0.01;
        }else if (gamepad2.left_trigger > 0) {
            servoPower -= 0.01;
        }*/


       /* Use the below piece of code if you want to control the Hopper movement using the buttons
       * A - Raise the Hopper
       * B - Lower the Hopper
       * X - Lower the Hopper to the minimum position
       * Y - Raise the Hopper to the maximum position
       * */
        if(gamepad2.a) {
            servoPower += 0.01;
        }else if (gamepad2.b){
            servoPower -= 0.01;
        }
        else if(gamepad2.x){
            servoPower = MIN_HOPPER_POWER;
        }else if (gamepad2.y){
            servoPower = MAX_HOPPER_POWER;
        }

        /* This section of the code is to limit or restrict the movement of the Hopper to its
        * lowest position or the farthest position to avoid physical damage to the Robot Hopper
        * */
        if(servoPower < MIN_HOPPER_POWER) {
            servoPower = MIN_HOPPER_POWER;
        }else if(servoPower > MAX_HOPPER_POWER){
            servoPower = MAX_HOPPER_POWER;
        }

        /* Set the power to the Left and Right Servo. Make sure both are set to the same power
        * ALWAYS
        * */
        leftServo.setPower(servoPower);
        rightServo.setPower(servoPower);
        telemetry.addData("Hopper Servo Power:","%.2f",servoPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        /* Reset the position of the Hopper to the lowest position */
        leftServo.setPower(MAX_HOPPER_POWER);
        rightServo.setPower(MAX_HOPPER_POWER);
    }
}
