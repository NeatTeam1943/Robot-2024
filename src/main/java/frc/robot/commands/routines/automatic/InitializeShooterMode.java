package frc.robot.commands.routines.automatic;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.Constants.ShooterModeConstants;
import frc.robot.commands.pitcherCommands.ReachPitch;
import frc.robot.commands.shooterCommands.SetShooterToDefaultRPM;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.Shooter;

/**
 * - Sets the shooter's pitch to a default value.
 * - Sets the shooter velocity to a default value until april tag is visible.
 */
public class InitializeShooterMode extends ParallelDeadlineGroup {
  public InitializeShooterMode(Pitcher pitcher, Shooter shooter, boolean isApm) {
    super(new SetShooterToDefaultRPM(shooter));

    addCommands(new ReachPitch(pitcher, (isApm) ? ShooterModeConstants.kAmpAngle : ShooterModeConstants.kSpeakerAngle));
  }
}
