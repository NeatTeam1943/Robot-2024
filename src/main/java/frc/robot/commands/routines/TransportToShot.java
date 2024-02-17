package frc.robot.commands.routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.transportationCommands.TransportNote;
import frc.robot.subsystems.NetworkTables;
import frc.robot.subsystems.Pitcher;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

public class TransportToShot extends ParallelCommandGroup {
  public TransportToShot(Pitcher pitcher, Shooter shooter, Transport transport, NetworkTables nt) {
    addCommands(
        new ShooterVision(pitcher, shooter, nt),
        new TransportNote(transport));
  }
}
