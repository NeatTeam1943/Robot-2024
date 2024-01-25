// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.TransportConstants;
import frc.robot.subsystems.Transport;

public class MoveNoteToShooter extends Command {
  private Transport m_trandport;
  /** Creates a new MoveNoteToShooter. */
  public MoveNoteToShooter(Transport transport) {
    m_trandport = transport;
    addRequirements(transport);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_trandport.moveBelts(TransportConstants.kBeltsSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_trandport.moveBelts(0);
  }  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_trandport.isNoteReady();
  }
}
