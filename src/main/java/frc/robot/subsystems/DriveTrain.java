// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrain extends SubsystemBase {
  private TalonFX m_frontRight;
  private TalonFX m_frontLeft;
  private TalonFX m_rearRight;
  private TalonFX m_rearLeft;
  private DifferentialDrive m_differentialDrive;

  public DriveTrain() {
    m_frontLeft = new TalonFX(DriveTrainConstants.frontLeftPort);
    m_frontRight = new TalonFX(DriveTrainConstants.frontRightPort);
    m_rearLeft = new TalonFX(DriveTrainConstants.rearLeftPort);
    m_rearRight = new TalonFX(DriveTrainConstants.rearRightPort);


    m_differentialDrive = new DifferentialDrive(m_frontLeft, m_frontRight);
  }

  public void move(double moveSpeed, double rotationSpeed){

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
