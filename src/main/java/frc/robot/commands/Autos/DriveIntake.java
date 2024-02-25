// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.transportationCommands.IntakeNote;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

public class DriveIntake extends ParallelCommandGroup {
  public DriveIntake(DriveTrain drive, IntakeNote intake, String pathName) {
    PathPlannerPath path = PathPlannerPath.fromPathFile(pathName);
    addRequirements(drive);
    addCommands(new ParallelCommandGroup(AutoBuilder.followPath(path),intake));
  }
}
