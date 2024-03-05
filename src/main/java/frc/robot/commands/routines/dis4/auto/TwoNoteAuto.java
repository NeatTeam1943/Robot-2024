package frc.robot.commands.routines.dis4.auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.driveTrainCommands.DriveDistance;
import frc.robot.commands.pitcherCommands.ReachPitchVision;
import frc.robot.commands.routines.dis2.Shoot;
import frc.robot.commands.transportationCommands.IntakeNote;
import frc.robot.general.Dis2FunctionalCommands;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

public class TwoNoteAuto extends SequentialCommandGroup {
  private DriveTrain m_drive;

  public TwoNoteAuto(DriveTrain drive, Intake intake, Transport transport, Dis2FunctionalCommands commands) {
    addCommands(
        new Shoot(commands, false),
        Commands.race(new DriveDistance(drive, 1.8, 0.5), new IntakeNote(intake, transport)),
        new Shoot(commands, false)
        );
  }
}
