package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.TransportConstants;
import frc.robot.subsystems.Transport;

/**
 * Puts Note inside the shooter using robot's Transport subsystem.
 */
public class PutNoteInsideShooter extends Command {
  private Transport m_transport;
  private boolean m_frontOfTheNotePassedTheSwitch, m_backOfTheNotePassedTheSwitch;
  
  /**
   * Creates a new PutNoteInsideShooter.
   * 
   * @param transport - the Transport subsystem which will be used.
   */
  public PutNoteInsideShooter(Transport transport) {
    m_transport = transport;

    m_frontOfTheNotePassedTheSwitch = false;
    m_backOfTheNotePassedTheSwitch = false;

    addRequirements(transport);
  }

  @Override
  public void initialize() {
    m_transport.setBeltsSpeed(TransportConstants.kBeltsSpeed);
  }

  @Override
  public void execute() {
    if (!m_frontOfTheNotePassedTheSwitch){
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
