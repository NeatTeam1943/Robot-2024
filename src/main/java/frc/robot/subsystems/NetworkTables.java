// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.FloatArrayPublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NetworkTables extends SubsystemBase {
  NetworkTableInstance m_ntinstance;
  NetworkTable m_nttable;

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
    m_ntinstance = NetworkTableInstance.getDefault();
    m_nttable = m_ntinstance.getTable("RealSense Data");

    cordX = m_nttable.getDoubleTopic("cordX");
    cordY = m_nttable.getDoubleTopic("cordY");

    targetPitch = m_nttable.getDoubleTopic("Target Pitch");
    targetRoll = m_nttable.getDoubleTopic("Target Roll");
    targetYaw = m_nttable.getDoubleTopic("Target Yaw");

    imuPitch = m_nttable.getDoubleTopic("IMU Pitch");
    imuRoll = m_nttable.getDoubleTopic("IMU Roll");
    imuYaw = m_nttable.getDoubleTopic("IMU Yaw");

  }

  /*
   * Retrieves a NetworkTables table for data storage and communication.
   */
  public NetworkTable getTable() {
    return m_nttable;
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

  @Override
  public void periodic() {
    /*
     * This method will be called once per scheduler run.
     */
  }
}
