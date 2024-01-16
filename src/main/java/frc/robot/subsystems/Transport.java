// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
 * The Transfer subsystem controls the mechanism which moves the Note from the Intake to Shooter
 */
public class Transport extends SubsystemBase {
  /*
   * Constructs the Transport subsystem
   */
  public Transport() {}

  /*
   * Sets speed to the motors of the mechanism
   * 
   * @param speed - sets the speed of the motors on scale of -1 to 1
   */
  public void moveBelts(double speed){
  }

  /*
   * returns if the Note inside and ready to be shot
   */
  public boolean isNoteReady(){
    return false;
  }

  /*
   * returns if the Intake acquired the Note
   */
  public boolean isTheNoteAcquired(){
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
