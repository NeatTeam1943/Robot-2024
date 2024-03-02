package frc.robot.commands.routines.automatic;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.IntakeModeConstants;
import frc.robot.commands.pitcherCommands.ReachPitch;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.Shooter;

/** - Sets the shooter's pitch to a default value. - Sets the shooter velocity to 0. */
public class InitializeIntakeMode extends ParallelCommandGroup {
  public InitializeIntakeMode(Pitcher pitcher, Shooter shooter) {
    addCommands(new ReachPitch(pitcher, IntakeModeConstants.kDefaultPitcherAngle));
  }
}
