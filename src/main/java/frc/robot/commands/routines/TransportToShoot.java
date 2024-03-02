package frc.robot.commands.routines;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.commands.HeadingCommands.IsInMode;
import frc.robot.commands.transportationCommands.TransportNote;
import frc.robot.general.RobotHeading;
import frc.robot.subsystems.NetworkTables;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

public class TransportToShoot extends ParallelDeadlineGroup {
  public TransportToShoot(Pitcher pitcher, Shooter shooter, Transport transport, NetworkTables nt) {
    super(new IsInMode(RobotHeading.SHOOTER));

    addCommands(new ShooterVision(pitcher, shooter, nt), new TransportNote(transport));
  }
}
