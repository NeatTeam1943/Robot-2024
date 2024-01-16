// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NetworkTables extends SubsystemBase {
  NetworkTableInstance instance;
  NetworkTable table;

  DoubleTopic cordX;
  DoubleTopic cordY;

  DoubleTopic targetPitch;
  DoubleTopic targetRoll;
  DoubleTopic targetYaw;

  DoubleTopic imuPitch;
  DoubleTopic imuRoll;
  DoubleTopic imuYaw;

  

  /*
   * Creates a new NetworkTables.
   */
  public NetworkTables() {
    instance = NetworkTableInstance.getDefault();
    table = instance.getTable("RealSense Data");

    cordX = table.getDoubleTopic("cordX");
    cordY = table.getDoubleTopic("cordY");

    targetPitch = table.getDoubleTopic("Target Pitch");
    targetRoll = table.getDoubleTopic("Target Roll");
    targetYaw = table.getDoubleTopic("Target Yaw");

    imuPitch = table.getDoubleTopic("IMU Pitch");
    imuRoll = table.getDoubleTopic("IMU Roll");
    imuYaw = table.getDoubleTopic("IMU Yaw");

  }

  /*
   * Retrieves a NetworkTables table for data storage and communication.
   */
  public NetworkTable getTable() {
    return table;
  }

  /*
   * Sets a NetworkTables table x value for data storage and communication.
   */
  public void setX(double x) {
    cordX.publish().set(x);
  }
  /*
   * Sets a NetworkTables table y value for data storage.
   */
  public void setY(double y) {
    cordY.publish().set(y);
  }

  public void setTargetPitch(double pitch) {
    targetPitch.publish().set(pitch);
  }

  public void setTargetRoll(double roll) {
    targetRoll.publish().set(roll);
  }

  public void setTargetYaw(double yaw) {
    targetYaw.publish().set(yaw);
  }

  public void setImuPitch(double pitch) {
    imuPitch.publish().set(pitch);
  }

  public void setImuRoll(double roll) {
    imuRoll.publish().set(roll);
  }

  public void setImuYaw(double yaw) {
    imuYaw.publish().set(yaw);
  }

  public double getCordX() {
    return cordX.subscribe(0).get();
  }

  public double getCordY() {
    return cordY.subscribe(0).get();
  }

  public double getTargetPitch() {
    return targetPitch.subscribe(0).get();
  }

  public double getTargetRoll() {
    return targetRoll.subscribe(0).get();
  }

  public double getTargetYaw() {
    return targetYaw.subscribe(0).get();
  }

  public double getImuPitch() {
    return imuPitch.subscribe(0).get();
  }

  public double getImuRoll() {
    return imuRoll.subscribe(0).get();
  }

  public double getImuYaw() {
    return imuYaw.subscribe(0).get();
  }

  @Override
  public void periodic() {
    /*
     * This method will be called once per scheduler run.
     */
  }
}
