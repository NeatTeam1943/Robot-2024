package frc.robot.commands.driveTrainCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.general.RobotHeadingUtils;
import frc.robot.subsystems.DriveTrain;

public class InvertDrive extends InstantCommand {
  private DriveTrain m_drive;
  private RobotHeadingUtils m_heading;

  public InvertDrive(DriveTrain drive) {
    m_drive = drive;
    m_heading = RobotHeadingUtils.getInstance();

    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_heading.toggleHeading();
    m_drive.setMotorInversions();
  }
}
