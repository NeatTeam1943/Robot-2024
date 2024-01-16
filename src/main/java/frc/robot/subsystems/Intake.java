package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

/*
 * The Intake subsystem controls the robot's drive system.
 */
public class Intake extends SubsystemBase {
    private CANSparkMax m_motor;

  /*
   * Constructs the DriveTrain subsystem with motor controllers
   */
  public Intake() {
    m_motor = new CANSparkMax(IntakeConstants.kMotorId, MotorType.kBrushless);
  }

  /*
   * Sets the speed of the motors for initiating the driving mechanism.
   */
  public void setMotorSpeed(double speed) {
    m_motor.set(speed);
  }
}
