package frc.robot.commands.driveTrainCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveTrain;

public class DriveRobot extends Command {
  private DriveTrain m_drive;
  private CommandXboxController m_joystick;

  public DriveRobot(DriveTrain drive, CommandXboxController joystick) {
    m_drive = drive;
    m_joystick = joystick;

    addRequirements(m_drive);
  }

  @Override
  public void execute() {
    m_drive.driveArcade(m_joystick);
  }
}
