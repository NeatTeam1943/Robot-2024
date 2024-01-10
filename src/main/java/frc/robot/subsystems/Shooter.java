// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public Shooter() {}

  public void setMotorSpeed(double speed){

  }

  public void setAngle(double angle){

  }

  /*
   * returns if the shooter angle got the limit of motion
   */
  public boolean isAtLimit(){
    return false;
  }

  /*
   * Returns the RPM of the motor.
   */
  public double getRPM() {

    return 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
