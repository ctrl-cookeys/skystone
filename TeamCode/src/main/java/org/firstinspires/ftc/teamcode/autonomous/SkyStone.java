package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.ColorReader;

public class SkyStone {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;

    private boolean isPatternA;
    private boolean isPatternB;
    private boolean isPatternC;

    public SkyStone(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    /**
     * From robot's point of view, stones are laid out as follows:
     * __________________________________
     * |__1__|__2__|__3__|__4__|__5__|__6_|
     * <p>
     * Pattern A: 1, 4
     * 1 and 4 are skystones, then skystoneArray would be
     * |__1__|__0__|__0__|__1__|__0__|__0_|
     * <p>
     * Pattern B: 2, 5
     * 2 and 5 are skystones, then skystoneArray would be
     * |__0__|__1__|__0__|__0__|__1__|__0_|
     * <p>
     * Pattern C: 3, 6
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

    public boolean isPatternC() {

        return this.isPatternC;
    }

    private void setPatternA() {

        /*  4. Pattern A: 1, 4
                If ( LS == BLACK (i.e. !YELLOW) && RS == YELLOW ) then
                   4th is a skystone, so 1st stone is also a skystone
         */

        this.isPatternA =
                (
                        ColorReader.isSkystone
                                (
                                        cBot.leftColorSensor.red(),
                                        cBot.leftColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                                && !ColorReader.isSkystone
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
                        !ColorReader.isSkystone
                                (
                                        cBot.leftColorSensor.red(),
                                        cBot.leftColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                                && !ColorReader.isSkystone
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
                        !ColorReader.isSkystone
                                (
                                        cBot.leftColorSensor.red(),
                                        cBot.leftColorSensor.green(),
                                        cBot.leftColorSensor.blue()
                                )
                                && ColorReader.isSkystone
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
