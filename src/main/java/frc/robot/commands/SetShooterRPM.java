// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

/**
 * Sets the RPM of each of the shooter motors using PID control.
 */
public class SetShooterRPM extends Command {
  private Shooter m_shooter;
  
  private PIDController m_leftController;
  private PIDController m_rightController;

  private double m_setpoint;

  /** Creates a new shooting. */
  public SetShooterRPM(Shooter shooter, double setpoint) {
    m_setpoint = setpoint;
    m_shooter = shooter;

    m_leftController  = new PIDController(ShooterConstants.kp, ShooterConstants.ki, ShooterConstants.kd);
    m_rightController = new PIDController(ShooterConstants.kp, ShooterConstants.ki, ShooterConstants.kd);

    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  m_leftController.setSetpoint(m_setpoint);
  m_rightController.setSetpoint(m_setpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setLeftShooterMotorSpeed(m_leftController.calculate(m_shooter.getLeftRPM(), m_setpoint));
    m_shooter.setRightShooterMotorSpeed(m_rightController.calculate(m_shooter.getRightRPM(), m_setpoint));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setShooterMotorsSpeed(0);
  } 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_leftController.atSetpoint() && (m_rightController.atSetpoint());
  }
}