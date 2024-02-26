package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class MeasurementConstants {
    public static final double kInchesToMeters = 0.0254;
    public static final double kMilimetersToCentimeters = 0.10;
    public static final double kCmToInch = 0.393700787;
    public static final double kMeterToInches = 39.37007874;
    public static final double kDegToRad = Math.PI / 180.0;
    public static final double kDegreesPerSecToRadsPerSec = 0.017453;
  }

  public static class VisionConstants {
    public static final double kLimelightPitch = 8.16;
    public static final double kLimelightHeightMeters = 0.76;

    public static final String kLimelightName = "limelight-nt";
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

    public static final double kRotationP = 0.013095;
    public static final double kRotationI = 0.000005;
    public static final double kRotationD = 0.000322;

    public static final double kRotationClampHigh = 0.75;
    public static final double kRotationClampLow = -0.75;
    public static final double kTrackWidthMeters = -0;
  }

  public static class IntakeConstants {
    public static final int kMotor = 5;
    public static final int kIntakeSwitch = 0;

    public static final double kIntakeMotorSpeed = -0.8;
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

    public static final double kLeftP = 0.001676;
    public static final double kLeftI = 0;
    public static final double kLeftD = 0;

    public static final double kLeftS = 0.14783;
    public static final double kLeftV = 0.11522;
    public static final double kLeftA = 0.0064632;

    public static final double kRightP = 0.001676;
    public static final double kRightI = 0;
    public static final double kRightD = 0;

    public static final double kRightS = 0.14783;
    public static final double kRightV = 0.11522;
    public static final double kRightA = 0.0076178;

    public static final double kLeftControllerThreshold = 0; // TODO TEMP Values
    public static final double kRightControllerThreshold = 0;
  }

  public static class PitcherConstants {
    public static final int kLeftAngleMotor = 10;
    public static final int kRightAngleMotor = 11;

    public static final double kMinAngle = 36; // TODO: Add angle
    public static final double kMaxAngle = 70; // TODO: Add angle

    /**
     * kTofToBase - The distance between the TOF sensor to the base of the robot
     * (CM).
     */
    public static final double kTofToBase = 27;

    /**
     * kLinearToHinge - The distance between the hinge of the shooter to the bottom
     * of the linear
     * motor (CM).
     */
    public static final double kLinearToHinge = 40;

    /**
     * kHingeToEndpoint - The distance between the hinge of the shooter to the tip
     * of the linear motor (CM).
     */
    public static final double kHingeToEndpoint = 53;

    /**
     * kEndpointToTrueller - The distance between the palestinian build that marks
     * the
     * endpoint of the TOF sensor range to the endpoint of the robot.
     */
    public static final double kEndpointToTrueller = 1;
  }

  public static class FieldConstants {
    public static final int kAmpATId = 5;
    public static final int kSpeakerATFirstId = 6;
    public static final int kSpeakerATSecondId = 7;
    public static final int kHumanATPlayerId = 7;
    public static final int kTrapATId = 7;

    public static final double kCameraHeightMeters = 0.395;

    public static final double kAmpATHeightMeters = 1.37;
    public static final double kSpeakerATHeightMeters = -0;
    public static final double kTrapATHeightMeters = -0;
    public static final double kHumanPlayerATHeightMeters = -0;
  }

  public static class LimeLightConstants {
    public static final double kLimelightPitch = 1.631;
    public static final String kLimelightName = "limelight-nt";
  }

  public static class RobotDataConstants {
    public static final int kPigeon = 12;
  }

  public static class IntakeModeConstants {
    public static final int kDefaultPitcherAngle = -0;
  }

  public static class ShooterModeConstants {
    public static final int kDefaultPitcherAngle = -0;
  }
}
