package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class MeasurementConstants {
    public static final double kInchesToMeters = 0.0254;
    public static final double kCmToInch = 0.393700787;
    public static final double kMeterToInches = 39.37007874;
    public static final double kDegToRad = Math.PI / 180.0;
  }

  }

  public static class DriveTrainConstants {
    public static final int kLeftFront = 1;
    public static final int kLeftRear = 2;
    public static final int kRightFront = 3;
    public static final int kRightRear = 4;

    public static final double kGearRatio = 10.71 / 1;
    public static final double kWheelDiameterMeters = MeasurementConstants.kInchesToMeters * 6;

    public static final double kEncoderSensorRotationsToMeters = Math.PI * kWheelDiameterMeters / kGearRatio;

    public static final double kRotationP = 0.013095;
    public static final double kRotationI = 0.000005;
    public static final double kRotationD = 0.000322;

    public static final double kRotationClampHigh = 0.75;
    public static final double kRotationClampLow = -0.75;
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
    public static final int kLeftAngleMotor = 10;
    public static final int kRightAngleMotor = 11;
    
    public static final double kMinAngle = 69; // TODO: Add angle
    public static final double kMaxAngle = 420; // TODO: Add angle
    public static final double kPotentiometerRange = 270;
    public static final double kPotentiometerOffset = 4269;

    public static final int kPotentiometer = 0;
    
    public static final int kTopLimitSwitch = 0;
    public static final int kBottomLimitSwitch = 1;
  }
}
