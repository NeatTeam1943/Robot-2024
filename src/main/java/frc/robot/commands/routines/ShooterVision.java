package frc.robot.commands.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.pitcherCommands.ReachPitchVision;
import frc.robot.commands.shooterCommands.SetVisionShooterRPM;
import frc.robot.subsystems.NetworkTables;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.Shooter;

public class ShooterVision extends ParallelCommandGroup {
  public ShooterVision(Pitcher pitcher, Shooter shooter, NetworkTables nt) {
    addCommands(
      new ReachPitchVision(pitcher),
      new SetVisionShooterRPM(shooter, nt)
    );
  }
}
