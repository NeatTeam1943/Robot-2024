// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrain extends SubsystemBase {
  private WPI_TalonFX m_leftFront;
  private WPI_TalonFX m_leftRear;
  private WPI_TalonFX m_rightFront;
  private WPI_TalonFX m_rightRear;

  private DifferentialDrive m_drive;

  public DriveTrain() {
    m_leftFront = new WPI_TalonFX(DriveTrainConstants.kLeftFront);
    m_leftRear = new WPI_TalonFX(DriveTrainConstants.kLeftRear);
    m_rightFront = new WPI_TalonFX(DriveTrainConstants.kRightFront);
    m_rightRear = new WPI_TalonFX(DriveTrainConstants.kRightRear);

    m_rightRear.follow(m_rightFront);
    m_leftRear.follow(m_leftFront);

    m_drive = new DifferentialDrive(m_leftFront, m_rightFront);
  }

  public void driveArcade(double movement, double rotation) {
    m_drive.arcadeDrive(movement, rotation);
  }

  public void driveArcade(CommandXboxController joystick) {
    m_drive.arcadeDrive(joystick.getRightTriggerAxis() - joystick.getLeftTriggerAxis(), joystick.getRightX());
  }
  public void driveTank(double left, double right) {
    m_drive.tankDrive(left, right);
  }
  public void driveTank(CommandXboxController joystick) {
    m_drive.tankDrive(joystick.getLeftY(), joystick.getRightY());
  }
  public double getLeftFrontMotorTraveledDistance() {
    return m_leftFront.getSelectedSensorPosition() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }
  public double getRightFrontMotorTraveledDistance() {
    return m_rightFront.getSelectedSensorPosition() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }
  public double getLeftRearMotorTraveledDistance() {
    return m_leftRear.getSelectedSensorPosition() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }
  public double getRightRearMotorTraveledDistance() {
    return m_rightRear.getSelectedSensorPosition() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }
  public void setLeftFrontMotorTraveledDistance(double position) {
    m_leftFront.setSelectedSensorPosition(position);
  }
  public void setRightFrontMotorTraveledDistance(double position) {
    m_rightFront.setSelectedSensorPosition(position);
  }
  public void setLeftRearMotorTraveledDistance(double position) {
    m_leftRear.setSelectedSensorPosition(position);
  }
  public void setRightRearMotorTraveledDistance(double position) {
    m_rightRear.setSelectedSensorPosition(position);
  }
  public void resetEncoders() {
    m_leftFront.setSelectedSensorPosition(0);
    m_rightFront.setSelectedSensorPosition(0);
    m_leftRear.setSelectedSensorPosition(0);
    m_rightRear.setSelectedSensorPosition(0);
  }
}
