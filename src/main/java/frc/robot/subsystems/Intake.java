package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

/** The Intake subsystem controls the robot's Note intake mechanism. */
public class Intake extends SubsystemBase {
  private CANSparkMax m_motor;

  /** Constructs the Intake subsystem. */
  public Intake() {
    m_motor = new CANSparkMax(IntakeConstants.kMotor, MotorType.kBrushless);
  }

  /**
   * Sets the speed of the motors for initiating the driving mechanism.
   *
   * @param speed - Rotation speed of the motor.
   */
  public void setMotorSpeed(double speed) {
    m_motor.set(speed);
  }
}
