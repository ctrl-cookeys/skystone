package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Stone {

    private LinearOpMode linearOpMode;
    private OpMode opMode;

    private Telemetry telemetry;
    private Robot cBot;

    private static final double SCALE_FACTOR = 255;

    private boolean isPatternA;
    private boolean isPatternB;
    private boolean isPatternC;

    public Stone(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    public Stone(Robot cBot, Telemetry telemetry, OpMode opMode) {

        this.cBot = cBot;
        this.opMode = opMode;
        this.telemetry = telemetry;

    }

    private int getHue(int red, int green, int blue) {

        float hsvValues [] = {0F, 0F, 0F};

        android.graphics.Color.RGBToHSV(
                (int) (red   * SCALE_FACTOR),
                (int) (blue  * SCALE_FACTOR),
                (int) (green * SCALE_FACTOR), hsvValues);


        return (int) hsvValues[0];

    }

    private boolean isSkystone(int red, int green, int blue) {


        // determine the hue red by the sensor by
        // passing in the RGB values to getHue()
        int hue = getHue(red, green, blue);

        // if the stone is Yellow, then it is NOT a skystone
        // so, return the opposite boolean value
        return !( hue > 30 && hue < 80 );

    }

    public boolean isLeftSensorYellow() {

        return !isSkystone(cBot.leftColorSensor.red(), cBot.leftColorSensor.green(), cBot.leftColorSensor.blue());

    }

    public boolean isRightSensorYellow() {

        return !isSkystone(cBot.rightColorSensor.red(), cBot.rightColorSensor.green(), cBot.rightColorSensor.blue());

    }

    /**
     * From robot's point of view, stones are laid out as follows:
     * __________________________________
     * |__1__|__2__|__3__|__4__|__5__|__6_|
     *
     * Pattern A: 1, 4
     * 1 and 4 are skystones
     * |__1__|__0__|__0__|__1__|__0__|__0_|
     *
     * Pattern B: 2, 5
     * 2 and 5 are skystones
     * |__0__|__1__|__0__|__0__|__1__|__0_|
     *
     * Pattern C: 3, 6
     * 3 and 6 are skystones
     * |__0__|__0__|__1__|__0__|__0__|__1_|
     *
     * Logic:
     * 1. We have 2 color sensors 1 (LS) on  left channel (LC),
     * and the other sensor (RS) on right channel (RC).
     *      LC  |o|        |o|  RC
     *          | |        | |
     *          ==============
     *          |            |
     *          |            |
     *          |            |
     *          ==============
     *
     * 2. Initial Robot Placement:
     * LC and RC are lined up with the stones 4 and 6
     *
     * 3. Robot moves forward closer to the stones
     *
     * 4. Pattern A:
     * If ( LS == BLACK (i.e. !YELLOW) && RS == YELLOW ) then
     * 4th is a skystone, so 1st stone is also a skystone
     *
     * 5. Pattern B:
     * If ( LS == YELLOW && RS == YELLOW ) then
     * 5th is a skystone, so 2nd stone is also a skystone
     *
     * 6. Pattern C:
     * If ( LS == YELLOW && RS == BLACK (i.e. !YELLOW) ) then
     * 6th is a skystone, so 3rd stone is also a skystone
     *
     */


    public void getSkystonePattern() {

        setPatternA();
        setPatternB();
        setPatternC();

    }


    public boolean isPatternA() {
        return this.isPatternA;
    }

    public boolean isPatternB() {
        return this.isPatternB;
    }

    public boolean isPatternC() { return this.isPatternC; }

    private void setPatternA() {

        /*  4. Pattern A: 1, 4
                If ( LS == BLACK (i.e. !YELLOW) && RS == YELLOW ) then
                   4th is a skystone, so 1st stone is also a skystone
         */

        this.isPatternA =
                (
                        isSkystone
                                (
                                        cBot.leftColorSensor.red(),
                                        cBot.leftColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                                && !isSkystone
                                (
                                        cBot.rightColorSensor.red(),
                                        cBot.rightColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                );

        //set others to be false
        this.isPatternB = false;
        this.isPatternC = false;
    }

    private void setPatternB() {

        /*
           5. Pattern B: 2, 5
               If ( LS == YELLOW && RS == YELLOW ) then
                  5th is a skystone, so 2nd stone is also a skystone
         */

        this.isPatternB =
                (
                        !isSkystone
                                (
                                        cBot.leftColorSensor.red(),
                                        cBot.leftColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                                && !isSkystone
                                (
                                        cBot.rightColorSensor.red(),
                                        cBot.rightColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                );

        //set others to be false
        this.isPatternA = false;
        this.isPatternC = false;
    }

    private void setPatternC() {

        /*
           6. Pattern C: 3, 6
               If ( LS == YELLOW && RS == BLACK (i.e. !YELLOW) ) th
                  6th is a skystone, so 3rd stone is also a skystone
         */

        this.isPatternC =
                (
                        !isSkystone
                                (
                                        cBot.leftColorSensor.red(),
                                        cBot.leftColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                                && isSkystone
                                (
                                        cBot.rightColorSensor.red(),
                                        cBot.rightColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                );
        //set others to be false
        this.isPatternA = false;
        this.isPatternB = false;
    }
}
