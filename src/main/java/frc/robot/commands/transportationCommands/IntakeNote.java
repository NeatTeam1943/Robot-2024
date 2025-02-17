package frc.robot.commands.transportationCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.TransportConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

/**
 * Intakes the note into the robot.
 */
public class IntakeNote extends Command {
  private Intake m_intake;

  private Transport m_transport;

  private Timer m_timer;

  /**
   * Creates a new IntakeNote Command.
   * 
   * @param intake    - An Intake subsystem instant.
   * @param transport - A transport subsystem instant.
   */
  public IntakeNote(Intake intake, Transport transport) {
    m_intake = intake;
    m_transport = transport;

    addRequirements(intake, transport);
  }

  @Override
  public void initialize() {
    m_timer = new Timer();

    m_timer.start();
  }

  @Override
  public void execute() {
    m_intake.setMotorSpeed(IntakeConstants.kIntakeMotorSpeed);
    m_transport.setBeltsSpeed(TransportConstants.kBeltsSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.setMotorSpeed(0);
    m_transport.setBeltsSpeed(0);
  }

  @Override
  public boolean isFinished() {
    // return m_transport.isNoteVisible();
    return m_timer.hasElapsed(3);
  }
}
