package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;



@Autonomous(name = "RevColorSensorTest1", group = "Sensor")
@Disabled
@Deprecated
public class RevColorSensorTest extends LinearOpMode {

    ColorSensor leftColorSensor = null;
    ColorSensor rightColorSensor = null;

    DistanceSensor leftDistanceSensor = null;
    DistanceSensor rightDistanceSensor = null;


    @Override
    public void runOpMode() {

        leftColorSensor = hardwareMap.get(ColorSensor.class, "left_color");
        rightColorSensor = hardwareMap.get(ColorSensor.class, "right_color");

        leftDistanceSensor = hardwareMap.get(DistanceSensor.class, "left_color");
        rightDistanceSensor = hardwareMap.get(DistanceSensor.class, "right_color");

        leftColorSensor.enableLed(false);
        rightColorSensor.enableLed(false);

        waitForStart();

        while (opModeIsActive()) {


            telemetry.addLine("left color: ")
                    .addData("left:R:G:B:A:H:D", "%d : %d :%d : %d : %d, %.2f",
                            leftColorSensor.red(),
                            leftColorSensor.green(),
                            leftColorSensor.blue(),
                            leftColorSensor.alpha(),
                            leftColorSensor.argb()/1000000,
                            leftDistanceSensor.getDistance(DistanceUnit.INCH));

            telemetry.addLine("right color: ")
                    .addData("right:R:G:B:A:H:D", "%d : %d :%d : %d : %d: %.2f",
                            rightColorSensor.red(),
                            rightColorSensor.green(),
                            rightColorSensor.blue(),
                            rightColorSensor.alpha(),
                            rightColorSensor.argb()/1000000,
                            rightDistanceSensor.getDistance(DistanceUnit.INCH));

           // telemetry.addData("left color: ,r, g, b, hue", "%d, %d, %d, %d", leftColorSensor.argb()/1000000);
           // telemetry.addData("right color hue", rightColorSensor.argb()/1000000);

         //   telemetry.addData("left distance (inch)", leftDistanceSensor.getDistance(DistanceUnit.INCH));
         //   telemetry.addData("right distance (inch)", rightDistanceSensor.getDistance(DistanceUnit.INCH));

            telemetry.update();
        }
    }
}
