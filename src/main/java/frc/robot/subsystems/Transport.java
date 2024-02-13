package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TransportConstants;

/**
 * The Transfer subsystem controls the mechanism which moves the Note from the Intake to Shooter
 */
public class Transport extends SubsystemBase {
  private CANSparkMax m_leftMotor;
  private CANSparkMax m_rightMotor;

  private DigitalInput m_shooterSwitch;

  /**
   * Constructs the Transport subsystem
   */
  public Transport() {
    m_leftMotor = new CANSparkMax(TransportConstants.kLeftMotor, MotorType.kBrushless);
    m_rightMotor = new CANSparkMax(TransportConstants.kRightMotor, MotorType.kBrushless);
    m_rightMotor.setInverted(true);

    m_shooterSwitch = new DigitalInput(TransportConstants.kShooterSwitch);
  }
  
  /**
   * Sets speed to the motors of the mechanism
   * 
   * @param speed - Sets the speed of the motors on scale of -1 to 1
   */
  public void setBeltsSpeed(double speed) {
    m_leftMotor.set(speed);
    m_rightMotor.set(speed);
  }

  /**
   * @return If the Note ready to be shot
   */
  public boolean isNoteVisible() {
    return m_shooterSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
