// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NetworkTables extends SubsystemBase {
  /*
   * Creates a new NetworkTables.
   */
  public NetworkTables() {}

  /*
   * Retrieves a NetworkTables table for data storage and communication.
   */
  public double GetTable() {
    return 0;
  }

  /*
   * Sets a NetworkTables table x value for data storage and communication.
   */
  public void SetX(double x) {}
  
  /*
   * Sets a NetworkTables table y value for data storage.
   */
  public void SetY(double y) {}


  @Override
  public void periodic() {
    /*
     * This method will be called once per scheduler run.
     */
  }
}
