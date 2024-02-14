package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TransportConstants;

/**
 * The Transport subsystem controls the robot's note transport mechanism.
 */
public class Transport extends SubsystemBase {
  private CANSparkMax m_leftMotor;
  private CANSparkMax m_rightMotor;

  private DigitalInput m_shooterSwitch;

  /**
   * Constructs the Transport subsystem.
   */
  public Transport() {
    m_leftMotor = new CANSparkMax(TransportConstants.kLeftMotor, MotorType.kBrushless);
    m_rightMotor = new CANSparkMax(TransportConstants.kRightMotor, MotorType.kBrushless);
    m_rightMotor.setInverted(true);

    m_shooterSwitch = new DigitalInput(TransportConstants.kShooterSwitch);
  }
  
  /**
   * Sets the motor's speed.
   * 
   * @param speed - Sets the speed of the motor.
   */
  public void setBeltsSpeed(double speed) {
    m_leftMotor.set(speed);
    m_rightMotor.set(speed);
  }

  /**
   * @return If the Note ready to be shot.
   */
  public boolean isNoteVisible() {
    return m_shooterSwitch.get();
  }

  @Override
  public void periodic() {}
}
