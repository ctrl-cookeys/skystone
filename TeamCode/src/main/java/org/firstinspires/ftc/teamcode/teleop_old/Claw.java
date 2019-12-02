package org.firstinspires.ftc.teamcode.teleop_old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**

 TASK:
 1. Implement the constructor, grabStone and releaseStone methods below
 2. You can use the Arm.java sample (from the teleop folder) to get an idea on what you may need to do
    Is the claw on the robot using a DcMotor or a Servo?
 3. Note: this is for the teleop mode, sso the driver will use gamepad2 to operate
 4. See the old code from League 2 CBotTeleOpDriveHoldArm_Old.java to get an idea and which buttons to use to operate

 5. Add your notes or questions, if any in this section





 */
@Deprecated
public class Claw {

    private final OpMode opMode;
    private final Telemetry telemetry;


    /*
     * Constructor
     */
    public Claw(OpMode opMode) {

        this.opMode = opMode;
        this.telemetry = this.opMode.telemetry;

    }

    public void grabStone() {

        // Write your code here to grab the claw
        //Think about what inputs (parameters), if any, would you want to get from the user of your class
        // Note this is for the teleop mode, so the driver will use gamepad2 to operate
    }

    public void releaseStone() {

        // Write your code here to release the claw
        //Think about what inputs (parameters), if any, would you want to get from the user of your class
        // Note this is for the teleop mode, so the driver will use gamepad2 to operate
    }

}