package frc.robot.commands.transportationCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.TransportConstants;
import frc.robot.subsystems.Transport;

/**
 * Transports the note to the shooter.
 */
public class TransportNote extends Command {
  private Transport m_transport;
  private boolean m_frontOfTheNotePassedTheSwitch, m_backOfTheNotePassedTheSwitch;

  /**
   * Creates a new TransportNote command.
   * 
   * @param transport - A Transport subsystem instant.
   */
  public TransportNote(Transport transport) {
    m_transport = transport;

    m_frontOfTheNotePassedTheSwitch = false;
    m_backOfTheNotePassedTheSwitch = false;

    addRequirements(transport);
  }

  @Override
  public void initialize() {
      m_transport.setBeltsSpeed(0.4);
  }

  @Override
  public void execute() {
    m_transport.setBeltsSpeed(TransportConstants.kBeltsSpeed);

    if (!m_frontOfTheNotePassedTheSwitch) {
      m_frontOfTheNotePassedTheSwitch = !m_transport.isNoteVisible();
    } else {
      m_backOfTheNotePassedTheSwitch = m_transport.isNoteVisible();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_transport.setBeltsSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return m_backOfTheNotePassedTheSwitch;
  }
}
