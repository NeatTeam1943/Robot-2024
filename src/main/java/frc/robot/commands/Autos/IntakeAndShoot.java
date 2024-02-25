package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class IntakeAndShoot extends SequentialCommandGroup {

  public static enum autos{
    AMP(),
    AMPTWO(),


  }

  public IntakeAndShoot(DriveIntake driveIntake, DriveShoot driveShoot) {

    addCommands();
  }
}
