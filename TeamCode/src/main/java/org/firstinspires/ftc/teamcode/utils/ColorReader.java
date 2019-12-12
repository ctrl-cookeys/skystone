package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.autonomous.Robot;


public class ColorReader {

    private LinearOpMode linearOpMode;
    private Telemetry telemetry;
    private Robot cBot;

    private static final double SCALE_FACTOR = 255;


    public ColorReader(Robot cBot, Telemetry telemetry, LinearOpMode linearOpMode) {

        this.cBot = cBot;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;

    }


    public static int getHue(int red, int green, int blue) {

        float hsvValues [] = {0F, 0F, 0F};

        android.graphics.Color.RGBToHSV(
                (int) (red   * SCALE_FACTOR),
                (int) (blue  * SCALE_FACTOR),
                (int) (green * SCALE_FACTOR), hsvValues);


        return (int) hsvValues[0];

    }

    public static boolean isSkystone(int red, int green, int blue) {


        // determine the hue red by the sensor by
        // passing in the RGB values to getHue()
        int hue = getHue(red, green, blue);

        // if the stone is Yellow, then it is NOT a skystone
        // so, return the opposite boolean value
        return !( hue > 30 && hue < 60 );

    }

}