package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.Constants.FieldConstants;
import frc.robot.Constants.MeasurementConstants;
import frc.robot.Constants.PitcherConstants;
import frc.robot.Limelight.Target;
import frc.robot.general.vision.PitchCalculator;

/**
 * The Pitcher subsystem controls the shooter's pitch angle.
 */
public class Pitcher extends SubsystemBase {
  private VictorSPX m_leftAngleMotor;
  private VictorSPX m_rightAngleMotor;

  //private PitchCalculator m_speakerPitchCalculator;

  private Rev2mDistanceSensor m_tof;

  /**
   * Constructs the Pitcher subsystem.
   */
  public Pitcher() {
    m_leftAngleMotor = new VictorSPX(PitcherConstants.kLeftAngleMotor);
    m_rightAngleMotor = new VictorSPX(PitcherConstants.kRightAngleMotor);

    //m_speakerPitchCalculator = new PitchCalculator(FieldConstants.kSpeakerATHeightMeters);

    m_tof = new Rev2mDistanceSensor(Port.kOnboard);

    setupTof();
  }

  /**
   * Setups the TOF sensor.
   */
  private void setupTof() {
    m_tof.setRangeProfile(RangeProfile.kHighAccuracy);
    m_tof.setAutomaticMode(true);
    m_tof.setDistanceUnits(Unit.kMillimeters);
  }

  /**
   * @return The distance from the TOF in CM.
   */
  private double getTofDistanceCM() {
    return m_tof.getRange() * MeasurementConstants.kMilimetersToCentimeters;
  }

  /**
   * @param a - A side in a tringle.
   * @param b - A side in a tringle.
   * @param c - A side in a tringle.
   * @return - The angle between a & b.
   */
  public double calculateLawOfCosinesDegrees(double a, double b, double c) {
    return Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2.0 * a * b)));
  }

  /**
   * Sets the speed at which the pitch motors will move.
   * 
   * @param speed - The speed at which the pitch motors move.
   */
  public void setAngleMotorsSpeed(double speed) {
    m_leftAngleMotor.set(VictorSPXControlMode.PercentOutput, speed);
    m_rightAngleMotor.set(VictorSPXControlMode.PercentOutput, speed < 0 ? speed + 0.04 : speed - 0.04);
  }

  /**
   * @return The pitch angle of the mechanism.
   */
  public double getCurrentAngleDegrees() {
    double tofDistanceCM = getTofDistanceCM();

    final double LINEAR_TO_ENDPOINT = PitcherConstants.kEndpointToTrueller + tofDistanceCM
        + PitcherConstants.kTofToBase;

    double numerator = (LINEAR_TO_ENDPOINT * LINEAR_TO_ENDPOINT)
        - (PitcherConstants.kHingeToEndpoint * PitcherConstants.kHingeToEndpoint)
        - (PitcherConstants.kLinearToHinge * PitcherConstants.kLinearToHinge);

    double denominator = -2 * PitcherConstants.kHingeToEndpoint * PitcherConstants.kLinearToHinge;

    // return Math.toDegrees(Math.acos(numerator / denominator));
    return tofDistanceCM * 100;
  }

  /**
   * @param angle - The angle that we want to check.
   * 
   * @return If angle is in the robot's range.
   */
  public boolean isInRange(double angle) {
    return PitcherConstants.kMinAngle < angle && angle < PitcherConstants.kMaxAngle;
  }

  public double getDesiredAngle() {
    // return m_speakerPitchCalculator.solve(
    //     getCurrentAngleDegrees(),
    //     PitcherConstants.kPitcherCalculatorStepSize,
    //     PitcherConstants.kPitcherCalculatorMaxIterations).orElse(-1.0);

    // double m = -2.25;
    // double x = Limelight.getDistanceFrom(Target.SPEAKER);
    // double b = 10.15;

    double m = -0.0232;
    double x = Limelight.getDistanceFrom(Target.SPEAKER);
    double b = 0.1012;

    return m*x+b;
  }

  @Override
  public void periodic() {
    // m_speakerPitchCalculator.update(Limelight.getDistanceFrom(Target.SPEAKER));

    SmartDashboard.putNumber("ToF Distance", getTofDistanceCM());
    SmartDashboard.putNumber("Current ANGLE", getCurrentAngleDegrees());
    SmartDashboard.putNumber("Desired Angle", getDesiredAngle());
  }
}
