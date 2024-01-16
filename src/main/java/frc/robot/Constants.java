package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class MeasurementConstants {
    public static final double kInchesToMeters = 0.0254;
  }

  public static class DriveTrainConstants {
    public static final int kLeftFront = 1;
    public static final int kLeftRear = 2;
    public static final int kRightFront = 3;
    public static final int kRightRear = 4;

    public static final double kGearRatio = 10.71 / 1;
    public static final double kWheelDiameterMeters = MeasurementConstants.kInchesToMeters * 6;

    public static final double kEncoderSensorRotationsToMeters = Math.PI * kWheelDiameterMeters / kGearRatio;
  }

  public static class IntakeConstants {
    public static final int kMotorId = 5;
  }
}
