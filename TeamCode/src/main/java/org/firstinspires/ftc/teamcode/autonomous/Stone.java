package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.ColorReader;

@Deprecated
public class Stone {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;

    public Stone(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    /**
     * From robot's point of view, stones are laid out as follows:
     * __________________________________
     * |__1__|__2__|__3__|__4__|__5__|__6_|
     * <p>
     * Pattern A:
     * 1 and 4 are skystones, then skystoneArray would be
     * |__1__|__0__|__0__|__1__|__0__|__0_|
     * <p>
     * Pattern B:
     * 2 and 5 are skystones, then skystoneArray would be
     * |__0__|__1__|__0__|__0__|__1__|__0_|
     * <p>
     * Pattern C:
     * 3 and 6 are skystones, then skystoneArray would be
     * |__0__|__0__|__1__|__0__|__0__|__1_|
     * <p>
     * Logic:
     * 1. We have 2 color sensors 1 (LS) on  left channel (LC),
     * and the other sensor (RS) on right channel (RC).
     * LC  |o|        |o|  RC
     * | |        | |
     * ==============
     * |            |
     * |            |
     * |            |
     * ==============
     * <p>
     * 2. Initial Robot Placement:
     * LC and RC are lined up with the stones 4 and 6
     * <p>
     * 3. Robot moves forward closer to the stones
     * <p>
     * 4. Pattern A:
     * If ( LS == BLACK (i.e. !YELLOW) && RS == YELLOW ) then
     * 4th is a skystone, so 1st stone is also a skystone
     * <p>
     * 5. Pattern B:
     * If ( LS == YELLOW && RS == YELLOW ) then
     * 5th is a skystone, so 2nd stone is also a skystone
     * <p>
     * 6. Pattern C:
     * If ( LS == YELLOW && RS == BLACK (i.e. !YELLOW) ) then
     * 6th is a skystone, so 3rd stone is also a skystone
     *
     * @return
     */

    public int[] getSkystoneArray() {


        int[] skystonesArray = {0, 0, 0, 0, 0, 0};

        if (isPatternA()) {
            skystonesArray[0] = 1;
            skystonesArray[1] = 0;
            skystonesArray[2] = 0;
            skystonesArray[3] = 1;
            skystonesArray[4] = 0;
            skystonesArray[5] = 0;

        } else if (isPatternB()) {

            skystonesArray[0] = 0;
            skystonesArray[1] = 1;
            skystonesArray[2] = 0;
            skystonesArray[3] = 0;
            skystonesArray[4] = 1;
            skystonesArray[5] = 0;

        } else if (isPatternC()) {

            skystonesArray[0] = 0;
            skystonesArray[1] = 0;
            skystonesArray[2] = 1;
            skystonesArray[3] = 0;
            skystonesArray[4] = 0;
            skystonesArray[5] = 1;

        } else {

            skystonesArray[0] = 0;
            skystonesArray[1] = 0;
            skystonesArray[2] = 0;
            skystonesArray[3] = 0;
            skystonesArray[4] = 0;
            skystonesArray[5] = 0;

        }

        return skystonesArray;

    }

    private boolean isPatternA() {

        /*  4. Pattern A:
                If ( LS == BLACK (i.e. !YELLOW) && RS == YELLOW ) then
                   4th is a skystone, so 1st stone is also a skystone
         */

        int lR = cBot.leftColorSensor.red();
        int lG = cBot.leftColorSensor.green();
        int lB = cBot.leftColorSensor.blue();

        int rR = cBot.leftColorSensor.red();
        int rG = cBot.leftColorSensor.green();
        int rB = cBot.leftColorSensor.blue();

        if (ColorReader.isSkystone(lR, lG, lB) && !ColorReader.isSkystone(rR, rG, rB)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isPatternB() {

        /*
           5. Pattern B:
               If ( LS == YELLOW && RS == YELLOW ) then
                  5th is a skystone, so 2nd stone is also a skystone
         */

        int lR = cBot.leftColorSensor.red();
        int lG = cBot.leftColorSensor.green();
        int lB = cBot.leftColorSensor.blue();

        int rR = cBot.leftColorSensor.red();
        int rG = cBot.leftColorSensor.green();
        int rB = cBot.leftColorSensor.blue();

        if (!ColorReader.isSkystone(lR, lG, lB) && !ColorReader.isSkystone(rR, rG, rB)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isPatternC() {

        /*
           6. Pattern C:
               If ( LS == YELLOW && RS == BLACK (i.e. !YELLOW) ) th
                  6th is a skystone, so 3rd stone is also a skystone
         */

        int lR = cBot.leftColorSensor.red();
        int lG = cBot.leftColorSensor.green();
        int lB = cBot.leftColorSensor.blue();

        int rR = cBot.leftColorSensor.red();
        int rG = cBot.leftColorSensor.green();
        int rB = cBot.leftColorSensor.blue();

        if (!ColorReader.isSkystone(lR, lG, lB) && ColorReader.isSkystone(rR, rG, rB)) {
            return true;
        } else {
            return false;
        }

    }
}
