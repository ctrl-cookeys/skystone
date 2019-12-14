package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hopper {


    private Telemetry telemetry;
    private Robot cBot;
    private LinearOpMode linearOpMode;

    final double MIN_HOPPER_POWER = 0.75;
    final double MAX_HOPPER_POWER = 0.75;


    private double servoPower = MIN_HOPPER_POWER; //Initialize the Hopper to the starting position

    /*
     * Constructor
     */
    public Hopper(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

        reset();

    }


    public void reset() {

        cBot.leftHopperArm.setPower(MIN_HOPPER_POWER);
        cBot.rightHopperArm.setPower(MIN_HOPPER_POWER);
    }

    /* Use the below piece of code if you want to control the Hopper movement using the buttons
     * A - Raise the Hopper
     * B - Lower the Hopper
     * X - Lower the Hopper to the minimum position
     * Y - Raise the Hopper to the maximum position
     * */

    public void raise() {

        while (linearOpMode.opModeIsActive()) {

            servoPower += 0.01;

            restrictHopper();

            /* Set the power to the Left and Right Servo. Make sure both are set to the same power
             * ALWAYS
             * */
            cBot.leftHopperArm.setPower(servoPower);
            cBot.rightHopperArm.setPower(servoPower);
        }
    }

    public void lower() {

        while (linearOpMode.opModeIsActive()) {
            servoPower -= 0.01;

            restrictHopper();

            /* Set the power to the Left and Right Servo. Make sure both are set to the same power
             * ALWAYS
             * */
            cBot.leftHopperArm.setPower(servoPower);
            cBot.rightHopperArm.setPower(servoPower);

        }

    }

    public void raiseToMaxPosition() {


            servoPower = MAX_HOPPER_POWER;

            restrictHopper();

            /* Set the power to the Left and Right Servo. Make sure both are set to the same power
             * ALWAYS
             * */
            cBot.leftHopperArm.setPower(servoPower);
            cBot.rightHopperArm.setPower(servoPower);

    }

    public void lowerToMinPosition() {



            servoPower = MIN_HOPPER_POWER;

            restrictHopper();

            /* Set the power to the Left and Right Servo. Make sure both are set to the same power
             * ALWAYS
             * */
            cBot.leftHopperArm.setPower(servoPower);
            cBot.rightHopperArm.setPower(servoPower);

    }

    private void restrictHopper() {

        if(servoPower < MIN_HOPPER_POWER) {
            servoPower = MIN_HOPPER_POWER;
        } else if(servoPower > MAX_HOPPER_POWER){
            servoPower = MAX_HOPPER_POWER;
        }

    }

}
