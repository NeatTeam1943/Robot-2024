package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    m_leftShooterMotor.setInverted(true);
    m_rightShooterMotor.setInverted(false);
  }

  /**
   * Sets the speed of the motors for initiating the mechanism.
   * 
   * @param speed - The speed at which the shooter motors will rotate.
   */
  public void setShooterMotorsSpeed(double speed) {
    m_leftShooterMotor.set(speed);
    m_rightShooterMotor.set(speed);
  }

  /**
   * @param speed - The speed at which the right shooter motor will rotate
   */
  public void setRightShooterMotorSpeed(double speed) {
    m_rightShooterMotor.set(speed);
  }

  /**
   * @param speed The speed at which the left shooter motor will rotate
   */
  public void setLeftShooterMotorSpeed(double speed) {
    m_leftShooterMotor.set(speed);
  }

  public void setLeftMotorVoltage(double voltage) {
    m_leftShooterMotor.setVoltage(voltage);
  }

  public void setRightMotorVoltage(double voltage) {
    m_rightShooterMotor.setVoltage(voltage);
  }

  /**
   * @return The RPM of the left shooter motor.
   */
  public double getLeftRPM() {
    return getLeftRate() * ShooterConstants.kAngularVelocityToRPM;
  }

  /**
   * @return The RPM of the right shooter motor.
   */
  public double getRightRPM() {
    return getRightRate() * ShooterConstants.kAngularVelocityToRPM;
  }

  public double getLeftRate() {
    return m_leftShooterMotor.getVelocity().getValueAsDouble();
  }

  public double getRightRate() {
    return m_rightShooterMotor.getVelocity().getValueAsDouble();
  }

  /**
   * @return The average RPM of the two shooter motors.
   */
  public double getAverageRPM() {
    return (getLeftRPM() + getRightRPM()) / 2;
  }
}