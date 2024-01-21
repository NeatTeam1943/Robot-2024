package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

/*
 * The Shooter subsystem controls the robot's shooting mechanism.
 */
public class Shooter extends SubsystemBase {
  TalonFX m_leftShooterMotor;
  TalonFX m_rightShooterMotor;
  CANSparkMax m_rightAngleMotor;
  CANSparkMax m_leftAngleMotor;

  AnalogPotentiometer m_pot;

  DigitalInput m_topLimitSwitch;
  DigitalInput m_bottomLimitSwitch;

  /*
   * Constructs the Shooter subsystem with motor controllers.
   */
  public Shooter() {
    m_leftShooterMotor = new TalonFX(ShooterConstants.kLeftShooterMotor);
    m_rightShooterMotor = new TalonFX(ShooterConstants.kRightShooterMotor);

    m_leftAngleMotor = new CANSparkMax(ShooterConstants.kLeftAngleMotor, MotorType.kBrushless);
    m_leftAngleMotor = new CANSparkMax(ShooterConstants.kRightAngleMotor, MotorType.kBrushless);

    m_pot = new AnalogPotentiometer(0, ShooterConstants.kMaxAngle, ShooterConstants.kMinAngle);

    m_topLimitSwitch = new DigitalInput(ShooterConstants.kTopLimitSwitchPort);
    m_bottomLimitSwitch = new DigitalInput(ShooterConstants.kBottomLimitSwitchPort);
  }

  /*
   * Sets the speed of the motors for initiating the shooting mechanism.
   */
  public void setShooterMotorSpeed(double speed) {
    m_leftShooterMotor.set(speed);
    m_rightShooterMotor.set(speed);
  }

  /*
   * Sets the speed of the motors for initiating the angle-adjusting mechanism.
   */
  public void setAngleSpeed(double speed) {
    m_leftAngleMotor.set(speed);
    m_rightAngleMotor.set(speed);
  }

  /*
   * Returns the pitch angle of the angle-adjusting mechanism.
   */
  public double getAngle() {
    return m_pot.get();
  }

  /*
   * Returns if the shooter angle had reached the limit of motion.
   */
  public boolean isAtLimit() {
    return m_topLimitSwitch.get() || m_bottomLimitSwitch.get(); // to be changed to shooter's angle
  }

  /*
   * Returns the RPM of the left motor.
   */
  public double getLeftRPM() {
    return m_leftShooterMotor.getVelocity().getValueAsDouble() * 60;
  }

  /*
   * Returns the RPM of the right motor.
   */
  public double getRightRPM() {
    return m_rightShooterMotor.getVelocity().getValueAsDouble() * 60;
  }
}
