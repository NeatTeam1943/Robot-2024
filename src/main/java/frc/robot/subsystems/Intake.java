package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

/**
 * The Intake subsystem controls the robot's Note intake mechanism.
 */
public class Intake extends SubsystemBase {
    private CANSparkMax m_motor;

    private Transport m_transport;

  /**
   * Constructs the Intake subsystem.
   */
  public Intake(Transport transport) {
    m_motor = new CANSparkMax(IntakeConstants.kMotor, MotorType.kBrushless);

    m_transport = transport;
  }

  /**
   * Sets the speed of the motors for initiating the driving mechanism.
   * 
   * @param speed - Rotation speed of the motor on the scale of -1 to 1. 
   */
  public void setMotorSpeed(double speed) {
    m_motor.set(speed);
  }

  /**
   * @return If the intake has acquired the Note.
   */
  public boolean isNoteAcquired() {
    return m_transport.isNoteVisible();
  }
}
