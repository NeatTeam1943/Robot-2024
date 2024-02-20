package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class MeasurementConstants {
    public static final double kInchesToMeters = 0.0254;
    public static final double kMilimetersToCentimeters = 0.10;
    public static final double kDegreesPerSecToRadsPerSec = 0.017453;
  }

  public static class IntakeModeConstants {
    public static final int kDefaultPitcherAngle = -0;
  }

  public static class ShooterModeConstants {
    public static final int kDefaultPitcherAngle = -0;
  }

  public static class DriveTrainConstants {
    public static final int kLeftFront = 1;
    public static final int kLeftRear = 2;
    public static final int kRightFront = 3;
    public static final int kRightRear = 4;

    public static final boolean kIntakeHeadingLeftInverted = false;
    public static final boolean kIntakeHeadingRightInverted = true;

    public static final boolean kShooterHeadingLeftInverted = true;
    public static final boolean kShooterHeadingRightInverted = false;

    public static final double kGearRatio = 10.71 / 1;
    public static final double kWheelDiameterMeters = MeasurementConstants.kInchesToMeters * 6;

    public static final double kEncoderSensorRotationsToMeters = Math.PI * kWheelDiameterMeters / kGearRatio;

    public static final double kTrackWidthMeters = -0;
  }

  public static class IntakeConstants {
    public static final int kMotor = 5;
    public static final int kIntakeSwitch = 0;

    public static final double kIntakeMotorSpeed = 0.5;
  }

  public static class TransportConstants {
    public static final int kMotor = 6;

    public static final int kPhotoSwitch = 1;

    public static final double kBeltsSpeed = 0.5;
  }

  public static class ShooterConstants {
    public static final int kLeftShooterMotor = 8;
    public static final int kRightShooterMotor = 9;
    public static final int kAngularVelocityToRPM = 60;

    public static final int kDefaultRPM = -0; 
    
    public static final double kP = 0.5; // TODO Add real values
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double kS = -0;
    public static final double kV = -0;

    public static final double kLeftControllerThreshold = 0; // TODO TEMP Values
    public static final double kRightControllerThreshold = 0;
  }

  public static class PitcherConstants {
    public static final int kLeftAngleMotor = 10;
    public static final int kRightAngleMotor = 11;

    public static final double kMinAngle = 69; // TODO: Add angle
    public static final double kMaxAngle = 420; // TODO: Add angle

    /**
     * kTofToBase - The distance between the TOF sensor to the base of the robot
     * (CM).
     */
    public static final double kTofToBase = 28.5;

    /**
     * kLinearToHinge - The distance between the hinge of the shooter to the bottom
     * of the linear
     * motor (CM).
     */
    public static final double kLinearToHinge = 38;

    /**
     * kHingeToEndpoint - The distance between the hinge of the shooter to the tip
     * of the linear motor (CM).
     */
    public static final double kHingeToEndpoint = 51.5;
  }
}
