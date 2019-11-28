package org.firstinspires.ftc.teamcode;


public final class Constants {

    private Constants() {}

    public static class Drive {

        private Drive() {}

        public static final double COUNTS_PER_MOTOR_REV = 1497;     // MR Motor (1497.325)
        public static final double GEAR_RATIO = 0.75;     //  Gear Ratio: 24 (Driven) to 32 (Driver)
        public static final double WHEEL_DIAMETER_INCHES = 4.0;      // For figuring circumference
        public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * GEAR_RATIO) / (WHEEL_DIAMETER_INCHES * Math.PI);
    }

    public class Arm {

        private Arm() {}

        public static final int ARM_RAMP_DOWN_ENCODER_TARGET_THRESHOLD = 100;

    }

    public class Robot {
        private Robot() {}

        public static final String LEFT_DRIVE_DEVICE_NAME = "left_drive";
        public static final String RIGHT_DRIVE_DEVICE_NAME = "right_drive";

    }

    public class GameMode {

        private GameMode() {}

    }

}
