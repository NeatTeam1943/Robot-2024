// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.AutoCommands;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.routines.TransportToShoot;
import frc.robot.commands.routines.automatic.InitializeIntakeMode;
import frc.robot.subsystems.DriveTrain;

public class DriveShoot extends SequentialCommandGroup {

  public DriveShoot(DriveTrain drive, InitializeIntakeMode initialize, TransportToShoot transport, String pathName) {
    PathPlannerPath path = PathPlannerPath.fromPathFile(pathName);

    addCommands(
      new SequentialCommandGroup(new ParallelCommandGroup(AutoBuilder.followPath(path),initialize),transport)
    );
  }
}
