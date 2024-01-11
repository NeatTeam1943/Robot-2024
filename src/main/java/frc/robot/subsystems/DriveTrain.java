// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrain extends SubsystemBase {
  private WPI_TalonFX m_frontRight;
  private WPI_TalonFX m_frontLeft;
  private WPI_TalonFX m_rearRight;
  private WPI_TalonFX m_rearLeft;
  private DifferentialDrive m_DifferentialDrive;

  public DriveTrain() {
    m_frontLeft = new WPI_TalonFX(DriveTrainConstants.frontLeftPort);
    m_frontRight = new WPI_TalonFX(DriveTrainConstants.frontRightPort);
    m_rearLeft = new WPI_TalonFX(DriveTrainConstants.rearLeftPort);
    m_rearRight = new WPI_TalonFX(DriveTrainConstants.rearRightPort);

    m_rearRight.follow(m_frontRight);
    m_rearLeft.follow(m_frontLeft);

    m_DifferentialDrive = new DifferentialDrive(m_frontLeft, m_frontRight);
  }
  public void move(double moveSpeed, double rotationSpeed){

    m_DifferentialDrive.tankDrive(moveSpeed, rotationSpeed);

  }

  public double getFrontRightEncoder(){
    return m_frontRight.getSelectedSensorPosition();
  }

  public double getFrontLeftEncoder(){
    return m_frontLeft.getSelectedSensorPosition();
  }

  public double getrearRightEncoder(){
    return m_rearRight.getSelectedSensorPosition();
  }

  public double getrearLeftEncoder(){
    return m_rearLeft.getSelectedSensorPosition();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
