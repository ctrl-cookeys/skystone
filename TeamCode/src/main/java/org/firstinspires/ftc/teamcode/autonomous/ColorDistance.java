package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ColorDistance {

    private final int GB_RATIO_THRESHOLD = 2;

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;

    /*
     * Constructor
     */
    public ColorDistance(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }

    public double getLeftGBColorRatio() {

        return cBot.leftColorSensor.green()/cBot.leftColorSensor.blue();
    }

    public double getRightGBColorRatio() {

        return cBot.rightColorSensor.green()/cBot.rightColorSensor.blue();
    }

    public double getLeftDistance() {
        return cBot.leftDistanceSensor.getDistance(DistanceUnit.INCH);
    }

    public double getRightDistance() {
        return cBot.rightDistanceSensor.getDistance(DistanceUnit.INCH);
    }

    public boolean isPatternA() {

        /*
          4. Pattern A: 1, 4
                If ( LS == BLACK (i.e. !YELLOW) && RS == YELLOW ) then
                   4th is a skystone, so 1st stone is also a skystone
         */

        return ((getLeftGBColorRatio() < GB_RATIO_THRESHOLD && getRightGBColorRatio() > GB_RATIO_THRESHOLD));
    }

    public boolean isPatternB() {
        /*
          5. Pattern B: 2, 5
               If ( LS == YELLOW && RS == YELLOW ) then
                  5th is a skystone, so 2nd stone is also a skystone
         */

        return ((getLeftGBColorRatio() > GB_RATIO_THRESHOLD && getRightGBColorRatio() > GB_RATIO_THRESHOLD));

    }

    public boolean isPatternC() {
         /*
           6. Pattern C: 3, 6
               If ( LS == YELLOW && RS == BLACK (i.e. !YELLOW) ) th
                  6th is a skystone, so 3rd stone is also a skystone
         */

        return ((getLeftGBColorRatio() > GB_RATIO_THRESHOLD && getRightGBColorRatio() < GB_RATIO_THRESHOLD));

    }
}
