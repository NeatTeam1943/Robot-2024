package frc.robot.commands.HeadingCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.general.RobotHeading;
import frc.robot.general.RobotHeadingUtils;

public class ChangeMode {
    private static RobotHeadingUtils m_robotHeading;

    public ChangeMode() {
        m_robotHeading = RobotHeadingUtils.getInstance();
    }

    public static Command IntakeMode() {
        return new RunCommand(() -> m_robotHeading.setRobotHeading(RobotHeading.INTAKE));
    }

    public static Command ShooterMode(){
        return new RunCommand(() -> m_robotHeading.setRobotHeading(RobotHeading.SHOOTER));
    }

    public static Command toggle(){
        return new RunCommand(() -> m_robotHeading.toggleHeading());
    }
}
