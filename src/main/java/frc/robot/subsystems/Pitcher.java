package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MeasurementConstants;
import frc.robot.Constants.PitcherConstants;

/**
 * The Pitcher subsystem controls the shooter's pitch angle.
 */
public class Pitcher extends SubsystemBase {
  private VictorSPX m_leftAngleMotor;
  private VictorSPX m_rightAngleMotor;

  private Rev2mDistanceSensor m_tof;

  /**
   * Constructs the Pitcher subsystem.
   */
  public Pitcher() {
    m_leftAngleMotor = new VictorSPX(PitcherConstants.kLeftAngleMotor);
    m_rightAngleMotor = new VictorSPX(PitcherConstants.kRightAngleMotor);

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
    m_rightAngleMotor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  /**
   * @return The pitch angle of the mechanism.
   */
  public double getAngleDegrees() {
    double tofDistanceCM = getTofDistanceCM();

    return calculateLawOfCosinesDegrees(
        PitcherConstants.kLinearToHinge,
        PitcherConstants.kHingeToEndpoint,
        PitcherConstants.kTofToBase + tofDistanceCM);
  }

  /**
   * @param angle - The angle that we want to check.
   * 
   * @return If angle is in the robot's range.
   */
  public boolean isInRange(double angle) {
    return PitcherConstants.kMinAngle < angle && angle < PitcherConstants.kMaxAngle;
  }

  @Override
  public void periodic() {}
}
