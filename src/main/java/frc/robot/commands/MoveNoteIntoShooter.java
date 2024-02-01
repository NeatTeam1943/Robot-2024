// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.TransportConstants;
import frc.robot.subsystems.Transport;

public class MoveNoteIntoShooter extends Command {
  private Transport m_transport;
  private boolean m_isReleased, m_pressedAgain;
  
  public MoveNoteIntoShooter(Transport transport) {
    m_transport = transport;
    m_isReleased = false;
    m_pressedAgain = false;
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
    if (!m_isReleased){
      m_isReleased = !m_transport.isNoteReady();
    } else {
      m_pressedAgain = m_transport.isNoteReady();
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
    return m_pressedAgain;
  }
}
