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
    public static final int kMotor = 5;
    public static final int kIntakeSwitch = 0;
    
    public static final double kIntakeMotorSpeed = 0.5;
  }

  public static class TransportConstants {
    public static final int kLeftMotor = 6;
    public static final int kRightMotor = 7;
    
    public static final int kShooterSwitch = 1;

    public static final double kBeltsSpeed = 0.5;
  }

  public static class ShooterConstants {
    public static final int kLeftShooterMotor = 8;
    public static final int kRightShooterMotor = 9;
    
    public static final double kP = 0.5; // TODO Add real values
    public static final double kI = 0;
    public static final double kD = 0;
  }

  public static class PitcherConstants {
    public static final int kLeftAngleMotor = 10;
    public static final int kRightAngleMotor = 11;
    
    public static final double kMinAngle = 69; // TODO: Add angle
    public static final double kMaxAngle = 420; // TODO: Add angle
    public static final double kPotentiometerRange = 270;
    public static final double kPotentiometerOffset = 4269;

    public static final int kPotentiometerPort = 0;
  }
}
