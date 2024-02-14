package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

/**
 * Intakes the note into the robot.
 */
public class IntakeNote extends Command {
  private Intake m_intake;

  private Transport m_transport;

  /**
   * Creates a new IntakeNote Command.
   * 
   * @param intake - An Intake subsystem instant.
   * @param transport - A transport subsystem instant.
   */
  public IntakeNote(Intake intake, Transport transport) {
    m_intake = intake;
    m_transport = transport;

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    m_intake.setMotorSpeed(IntakeConstants.kIntakeMotorSpeed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    m_intake.setMotorSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return m_transport.isNoteVisible();
  }
}
