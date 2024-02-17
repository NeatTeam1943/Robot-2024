package frc.robot.commands.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.ShooterModeConstants;
import frc.robot.commands.pitcherCommands.ReachPitch;
import frc.robot.commands.shooterCommands.SetShooterToDefaultRPM;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.Shooter;

/**
 * - Sets the shooter's pitch with regard to vision data.
 * - Sets the shooter velocity with regard to vision data.
 */
public class InitializeShooterMode extends ParallelCommandGroup {
  public InitializeShooterMode(Pitcher pitcher, Shooter shooter) {
    addCommands(
        new ReachPitch(pitcher, ShooterModeConstants.kDefaultPitcherAngle),
        new SetShooterToDefaultRPM(shooter, null));
  }
}
