// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.routines.TransportToShoot;
import frc.robot.subsystems.DriveTrain;

public class DriveShoot extends SequentialCommandGroup {

  public DriveShoot(DriveTrain drive, TransportToShoot shoot, String pathName) {
    PathPlannerPath path = PathPlannerPath.fromPathFile(pathName);
    addRequirements(drive);
    addCommands(new SequentialCommandGroup(AutoBuilder.followPath(path),shoot));
  }
}
