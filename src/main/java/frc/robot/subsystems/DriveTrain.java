// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrain extends SubsystemBase {
  private WPI_TalonSRX m_leftFront;
  private WPI_TalonSRX m_leftRear;
  private WPI_TalonSRX m_rightFront;
  private WPI_TalonSRX m_rightRear;

  private MotorControllerGroup m_leftMotors;
  private MotorControllerGroup m_rightMotors;

  private DifferentialDrive m_drive;

  private boolean m_headingShooter = true; // ~~ becomes false in constractor ~~

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    m_leftFront = new WPI_TalonSRX(DriveTrainConstants.kLeftFront);
    m_leftRear = new WPI_TalonSRX(DriveTrainConstants.kLeftRear);
    m_rightFront = new WPI_TalonSRX(DriveTrainConstants.kRightFront);
    m_rightRear = new WPI_TalonSRX(DriveTrainConstants.kRightRear);

    setMotorInversions();

    m_leftMotors = new MotorControllerGroup(m_leftMotors, m_leftRear);
    m_rightMotors = new MotorControllerGroup(m_rightFront, m_rightRear);

    m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  }

  public void driveArcade(CommandXboxController controller) {
    m_drive.arcadeDrive(controller.getRightTriggerAxis() - controller.getLeftTriggerAxis(), controller.getLeftX());
  }

  public void driveArcade(double speed, double rotation){
    m_drive.arcadeDrive(speed, rotation);
  }

  public void driveTank(double speed, double rotation){
    m_drive.tankDrive(speed, rotation);
  }

  public void setMotorInversions() {
    m_headingShooter = !m_headingShooter;
    m_leftFront.setInverted(!m_headingShooter);
    m_leftRear.setInverted(!m_headingShooter);
    m_rightFront.setInverted(m_headingShooter);
    m_rightRear.setInverted(m_headingShooter);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
