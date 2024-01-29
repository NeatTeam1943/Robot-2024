package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

/**
 * The Shooter subsystem controls the robot's shooting-mechanism.
 */
public class Shooter extends SubsystemBase {
  private TalonFX m_leftShooterMotor;
  private TalonFX m_rightShooterMotor;

  /**
   * Constructs the Shooter subsystem with motor controllers and sensors.
   */
  public Shooter() {
    m_leftShooterMotor = new TalonFX(ShooterConstants.kLeftShooterMotor);
    m_rightShooterMotor = new TalonFX(ShooterConstants.kRightShooterMotor);
  }

  /**
   * Sets the speed of the motors for initiating the mechanism.
   * 
   * @param speed - the speed at which the shooter motors will rotate.
   */
  public void setShooterMotorsSpeed(double speed) {
    m_leftShooterMotor.set(speed);
    m_rightShooterMotor.set(speed);
  }

  public void setRightShooterMotorSpeed(double speed) {
    m_rightShooterMotor.set(speed);
  }

  public void setLeftShooterMotorSpeed(double speed) {
    m_leftShooterMotor.set(speed);
  }

  /**
   * @return the RPM of the left shooter motor.
   */
  public double getLeftRPM() {
    return m_leftShooterMotor.getVelocity().getValueAsDouble() * 60;
  }

  /**
   * @return the RPM of the right shooter motor.
   */
  public double getRightRPM() {
    return m_rightShooterMotor.getVelocity().getValueAsDouble() * 60;
  }

    /**
   * @return the average RPM of the two shooter motors.
   */
  public double getAverageRPM() {
    return (getLeftRPM() + getRightRPM()) / 2;
  }
}
