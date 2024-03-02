package frc.robot.commands.routines.dis2;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.general.Dis2FunctionalCommands;

public class Shoot extends SequentialCommandGroup {
  private Dis2FunctionalCommands m_commands;

  public Shoot(Dis2FunctionalCommands commands, boolean isAmp) {
    m_commands = commands;

    addCommands(
        m_commands.shoot(isAmp ? 0.17 : 1),
        new WaitCommand(1.5),
        m_commands.transportIntake(0.05, -0.7),
        new WaitCommand(0.5),
        m_commands.shoot(0),
        m_commands.transportIntake(0, 0));
  }
}
