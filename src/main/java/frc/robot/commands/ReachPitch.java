// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pitcher;

/**
 * The ReachPitch command reaches the desired pitch-angle of the shooting-mechanism.
 * 
 * @param pitcher - the subsystem which will be used.
 * 
 * @param desiredPitch - the desired pitch-angle of the shooting-mechanism.
 */
public class ReachPitch extends Command {
  Pitcher m_pitcher;

  BangBangController m_controller;
  double m_desiredPitch;
  boolean m_reachedPitch;

  /** Creates a new ReachPitch. */
  public ReachPitch(Pitcher pitcher, double desiredPitch) {
    m_pitcher = pitcher;

    m_controller = new BangBangController();
    m_desiredPitch = desiredPitch;
    m_reachedPitch = false;

    addRequirements(m_pitcher);
  }

  @Override
  public void initialize() {}

  /**
   * Sets the speed of the angle-motors using BangBangControl; checks if the desired pitch-angle has been reached.
   */
  @Override
  public void execute() {
    m_pitcher.setAngleMotorsSpeed(m_controller.calculate(m_pitcher.getAngle(), m_desiredPitch));

    if (m_pitcher.getAngle() == m_desiredPitch) {
      m_reachedPitch = true;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  /**
   * @return If the desired pitch-angle has been reached or if it is out of range.
   */
  @Override
  public boolean isFinished() {
    if (!m_pitcher.isInRange(m_desiredPitch)) {
      System.err.println("IT'S NOT MY BEST ANGLE");
    }
    return m_reachedPitch || !m_pitcher.isInRange(m_desiredPitch);
  }
}
