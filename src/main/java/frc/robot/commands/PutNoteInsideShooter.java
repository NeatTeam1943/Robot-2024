// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_transport.setBeltsSpeed(TransportConstants.kBeltsSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!m_frontOfTheNotePassedTheSwitch){
      m_frontOfTheNotePassedTheSwitch = !m_transport.isSwitchPressed();
    } else {
      m_backOfTheNotePassedTheSwitch = m_transport.isSwitchPressed();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_transport.setBeltsSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_backOfTheNotePassedTheSwitch;
  }
}
