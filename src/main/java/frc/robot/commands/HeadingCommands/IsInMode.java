package frc.robot.commands.HeadingCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.general.RobotHeadingUtils;
import frc.robot.general.RobotHeading;

public class IsInMode extends Command {
  RobotHeadingUtils m_robotCurrentHeading;
  RobotHeading m_heading;
  
  public IsInMode(RobotHeading heading) {
    m_robotCurrentHeading = RobotHeadingUtils.getInstance();
  }

  @Override
  public boolean isFinished() {
    return m_robotCurrentHeading.sameAs(m_heading);
  }
}
