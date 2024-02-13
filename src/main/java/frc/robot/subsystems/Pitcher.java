// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PitcherConstants;

/**
 * The Pitcher subsystem controls the Robot's pitch-angle tuning-mechanism.
 */
public class Pitcher extends SubsystemBase {
  private VictorSP m_leftAngleMotor;
  private VictorSP m_rightAngleMotor;

  private AnalogPotentiometer m_potentiometer;


  /** Creates a new Pitcher. */
  public Pitcher() {
    m_leftAngleMotor = new VictorSP(PitcherConstants.kLeftAngleMotor);
    m_rightAngleMotor = new VictorSP(PitcherConstants.kRightAngleMotor);

    m_potentiometer = new AnalogPotentiometer(PitcherConstants.kPotentiometerPort, PitcherConstants.kPotentiometerRange, PitcherConstants.kPotentiometerOffset);
  }

  /**
   * Sets the speed at which the pitch motors will move.
   * 
   * @param speed - The speed at which the pitch motors move.
   */
  public void setAngleMotorsSpeed(double speed) {
    m_leftAngleMotor.set(speed);
    m_rightAngleMotor.set(speed);
  }

  /**
   * @return The pitch angle of the mechanism.
   */
  public double getAngle() {
    return m_potentiometer.get();
  }

  @Override
  public void periodic() {}
}
