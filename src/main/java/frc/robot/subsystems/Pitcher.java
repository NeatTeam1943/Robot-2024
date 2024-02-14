package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
  public double getAngle() {
    return 0.0; 
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
