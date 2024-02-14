package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.Intake;

/**
 * Inserts Note into robot using its Intake subsystem.
 */
public class IntakeNote extends Command {
  private Intake m_intake;

  /**
   * Creates a new CollectNote Command.
   * 
   * @param intake - The subsystem that would be used
   */
  public IntakeNote(Intake intake) {
    m_intake = intake;

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
    return m_intake.isNoteAcquired();
  }
}
