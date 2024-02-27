package frc.robot.commands.routines.automatic;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.Constants.ShooterModeConstants;
import frc.robot.commands.pitcherCommands.ReachPitch;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.Shooter;

/**
 * - Sets the shooter's pitch to a default value.
 * - Sets the shooter velocity to a default value until april tag is visible.
 */
public class InitializeShooterMode extends ParallelCommandGroup {
  public InitializeShooterMode(Pitcher pitcher, Shooter shooter, boolean isApm) {
    addCommands(new ReachPitch(pitcher, (isApm) ? ShooterModeConstants.kAmpAngle : ShooterModeConstants.kSpeakerAngle));
  }
}
