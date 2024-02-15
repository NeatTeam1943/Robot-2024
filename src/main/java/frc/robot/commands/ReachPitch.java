// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pitcher;

/**
 * The ReachPitch command reaches the desired pitch-angle of the shooting-mechanism.
 */
public class ReachPitch extends Command {
  private Pitcher m_pitcher;

  private BangBangController m_controller;
  private double m_desiredPitch;

  /** 
   * Creates a new ReachPitch. 
   * 
   * @param pitcher - the subsystem which will be used.
   * 
   * @param desiredPitch - the desired pitch-angle of the shooting-mechanism.
   */
  public ReachPitch(Pitcher pitcher, double desiredPitch) {
    m_pitcher = pitcher;
    m_desiredPitch = desiredPitch;

    m_controller = new BangBangController();

    addRequirements(m_pitcher);
  }

  @Override
  public void initialize() {}

  /**
   * Sets the speed of the angle-motors using BangBangControl; checks if the desired pitch-angle has been reached.
   */
  @Override
  public void execute() {
    m_pitcher.setAngleMotorsSpeed(m_controller.calculate(m_pitcher.getAngleDegrees(), m_desiredPitch));
  }

  @Override
  public void end(boolean interrupted) {}

  /**
   * @return If the desired pitch-angle has been reached or if it's out of range.
   */
  @Override
  public boolean isFinished() {
    return m_controller.atSetpoint() || !m_pitcher.isInRange(m_desiredPitch);
  }
}
