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

  public static class ShooterConstants {
    public static final int kLeftShooterMotor = 6;
    public static final int kRightShooterMotor = 7; 
    public static final int kLeftAngleMotor = 8;
    public static final int kRightAngleMotor = 9;
    
    public static final double kMinAngle = 69; // TODO: Add angle
    public static final double kMaxAngle = 180; // TODO: Add angle 
    
    public static final int kTopLimitSwitchPort = 0;
    public static final int kBottomLimitSwitchPort = 1;
  }
}
