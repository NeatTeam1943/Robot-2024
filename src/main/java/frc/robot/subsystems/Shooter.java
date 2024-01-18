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

  AnalogPotentiometer m_pot;

  DigitalInput m_topLimitSwitch;
  DigitalInput m_bottomLimitSwitch;

  /*
   * Constructs the Shooter subsystem with motor controllers.
   */
  public Shooter() {
    m_leftShooter = new TalonFX(ShooterConstants.kLeftShooter);
    m_rightShooter = new TalonFX(ShooterConstants.kRightShooter);

    m_pot = new AnalogPotentiometer(0, 180, ShooterConstants.kMinAngle);
    
    m_topLimitSwitch = new DigitalInput(ShooterConstants.kTopLimitSwitchPort);
    m_bottomLimitSwitch = new DigitalInput(ShooterConstants.kBottomLimitSwitchPort);
  }

  /*
   * Sets the speed of the motors for initiating the shooting mechanism.
   */
  public void setMotorSpeed(double speed) {
    m_leftShooter.set(speed);
    m_rightShooter.set(speed);
  }

  /*
   * Sets the pitch angle of the shooting mechanism.
   */
  public void setAngle(double angle) {
    
  }

  public double getAngle() {
    return m_pot.get();
  }

  /*
   * returns if the shooter angle got the limit of motion.
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
