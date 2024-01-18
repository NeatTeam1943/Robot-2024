package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

/*
 * The Shooter subsystem controls the robot's shooting mechanism.
 */
public class Shooter extends SubsystemBase {
  TalonFX m_leftShooter;
  TalonFX m_rightShooter;

  TalonFX m_angler;

  AnalogPotentiometer m_pot;

  DigitalInput m_topLimitSwitch;
  DigitalInput m_bottomLimitSwitch;

  boolean m_inverted;

  /*
   * Constructs the Shooter subsystem with motor controllers.
   */
  public Shooter() {
    m_leftShooter = new TalonFX(ShooterConstants.kLeftShooter);
    m_rightShooter = new TalonFX(ShooterConstants.kRightShooter);

    m_angler = new TalonFX(ShooterConstants.kAngler);

    m_pot = new AnalogPotentiometer(0, 180, ShooterConstants.kMinAngle);
    
    m_topLimitSwitch = new DigitalInput(ShooterConstants.kTopLimitSwitchPort);
    m_bottomLimitSwitch = new DigitalInput(ShooterConstants.kBottomLimitSwitchPort);

    m_inverted = false;
  }

  /*
   * Sets the speed of the motors for initiating the shooting mechanism.
   */
  public void setShooterMotorSpeed(double speed) {
    m_leftShooter.set(speed);
    m_rightShooter.set(speed);
  }

  /*
   * Toggles between a state and its inversion; used for determining the Angler orientation.
   */
  public void toggleInverted() {
    m_inverted = !m_inverted;
  }

  /*
   * Returns the speed of the Angler motor and inverts its orientation if needed.
   */
  public double getAnglerSpeed() {
    double speed = m_angler.get();
    if (m_inverted) {
      speed *= -1;
    }
    return speed;
  } 

  /*
   * Sets the orientation of the Angler motor.
   */
  public void setAnglerOrientation(boolean orientation) {
    double speed = getAnglerSpeed();
    m_angler.set(speed);
  }

  /*
   * Returns the pitch angle of the shooting mechanism.
   */
  public double getAngle() {
    return m_pot.get();
  }

  /*
   * Returns if the shooter angle got the limit of motion.
   */
  public boolean isAtLimit() {
    return m_topLimitSwitch.get() || m_bottomLimitSwitch.get(); // to be changed to shooter's angle
  }

  /*
   * Returns the RPM of the right motor.
   */
  public double getLeftRPM() {
    return m_leftShooter.getVelocity().getValueAsDouble() * 60;
  }

  /*
   * Returns the RPM of the right motor.
   */
  public double getRightRPM() {
    return m_rightShooter.getVelocity().getValueAsDouble() * 60;
  }
}
