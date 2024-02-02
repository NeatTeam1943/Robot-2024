// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NetworkTables extends SubsystemBase {
  private NetworkTableInstance m_instance;
  private NetworkTable m_table;

  private DoubleTopic m_screenX;
  private DoubleTopic m_screenY;

  private DoubleTopic m_targetPitch;
  private DoubleTopic m_targetRoll;
  private DoubleTopic m_targetYaw;

  private DoubleTopic m_imuPitch;
  private DoubleTopic m_imuRoll;
  private DoubleTopic m_imuYaw;

  // Publishers
  private DoublePublisher m_screenXPublisher;
  private DoublePublisher m_screenYPublisher;

  private DoublePublisher m_targetPitchPublisher;
  private DoublePublisher m_targetRollPublisher;
  private DoublePublisher m_targetYawPublisher;

  private DoublePublisher m_imuPitchPublisher;
  private DoublePublisher m_imuRollPublisher;
  private DoublePublisher m_imuYawPublisher;
  
  // Subscribers
  private DoubleSubscriber m_screenXSubscriber;
  private DoubleSubscriber m_screenYSubscriber;

  private DoubleSubscriber m_targetPitchSubscriber;
  private DoubleSubscriber m_targetRollSubscriber;
  private DoubleSubscriber m_targetYawSubscriber;

  private DoubleSubscriber m_imuPitchSubscriber;
  private DoubleSubscriber m_imuRollSubscriber;
  private DoubleSubscriber m_imuYawSubscriber;

  /*
   * Creates a new NetworkTables.
   */
  public NetworkTables() {
    m_instance = NetworkTableInstance.getDefault();
    m_table = m_instance.getTable("RealSense Data");

    m_screenX = m_table.getDoubleTopic("cordX");
    m_screenY = m_table.getDoubleTopic("cordY");

    m_targetPitch = m_table.getDoubleTopic("Target Pitch");
    m_targetRoll = m_table.getDoubleTopic("Target Roll");
    m_targetYaw = m_table.getDoubleTopic("Target Yaw");

    m_imuPitch = m_table.getDoubleTopic("IMU Pitch");
    m_imuRoll = m_table.getDoubleTopic("IMU Roll");
    m_imuYaw = m_table.getDoubleTopic("IMU Yaw");

    // Publishers
    m_screenXPublisher = m_screenX.publish();
    m_screenYPublisher = m_screenY.publish();

    m_targetPitchPublisher = m_targetPitch.publish();
    m_targetRollPublisher = m_targetRoll.publish();
    m_targetYawPublisher = m_targetYaw.publish();

    m_imuPitchPublisher = m_imuPitch.publish();
    m_imuRollPublisher = m_imuRoll.publish();
    m_imuYawPublisher = m_imuYaw.publish();

    // Subscribers
    m_screenXSubscriber = m_screenX.subscribe(0);
    m_screenYSubscriber = m_screenY.subscribe(0);

    m_targetPitchSubscriber = m_targetPitch.subscribe(0);
    m_targetRollSubscriber = m_targetRoll.subscribe(0);
    m_targetYawSubscriber = m_targetYaw.subscribe(0);

    m_imuPitchSubscriber = m_imuPitch.subscribe(0);
    m_imuRollSubscriber = m_imuRoll.subscribe(0);
    m_imuYawSubscriber = m_imuYaw.subscribe(0);
  }

  /*
   * Retrieves a NetworkTables table for data storage and communication.
   */
  public NetworkTable getTable() {
    return m_table;
  }

  /*
   * Sets a NetworkTables table x value for data storage and communication.
   */
  public void setX(double x) {
    m_screenX.publish().set(x);
  }
  
  /*
   * Sets a NetworkTables table y value for data storage.
   */
  public void setY(double y) {
    m_screenYPublisher.set(y);
  }

  /*
   * Sets a NetworkTables table target pitch value for data storage.
   */
  public void setTargetPitch(double pitch) {
    m_targetPitchPublisher.set(pitch);
  }

  /*
   * Sets a NetworkTables table target roll value for data storage.
   */
  public void setTargetRoll(double roll) {
    m_targetRollPublisher.set(roll);
  }

  /*
   * Sets a NetworkTables table target yaw value for data storage.
   */
  public void setTargetYaw(double yaw) {
    m_targetYawPublisher.set(yaw);
  }

  /*
   * Sets a NetworkTables table imu pitch value for data storage.
   */
  public void setImuPitch(double pitch) {
    m_imuPitchPublisher.set(pitch);
  }

  /*
   * Sets a NetworkTables table imu roll value for data storage.
   */
  public void setImuRoll(double roll) {
    m_imuRollPublisher.set(roll);
  }

  /*
   * Sets a NetworkTables table imu yaw value for data storage.
   */
  public void setImuYaw(double yaw) {
    m_imuYawPublisher.set(yaw);
  }

  /*
   * Retrieves a NetworkTables table x value for target on screen(X is a value in pixels on the screen) data storage and communication.
   */
  public double getScreenX() {
    return m_screenXSubscriber.get();
  }

  /*
   * Retrieves a NetworkTables table y value for target on screen(Y is a Y value in the screen, in pixels), for data storage and communication.
   */
  public double getScreenY() {
    return m_screenYSubscriber.get();
  }

  /*
   * Retrieves a NetworkTables table target pitch value for data storage and communication.
   */
  public double getTargetPitch() {
    return m_targetPitchSubscriber.get();
  }

  /*
   * Retrieves a NetworkTables table target roll value for data storage and communication.
   */
  public double getTargetRoll() {
    return m_targetRollSubscriber.get();
  }

  /*
   * Retrieves a NetworkTables table target yaw value for data storage and communication.
   */
  public double getTargetYaw() {
    return m_targetYawSubscriber.get();
  }

  /*
   * Retrieves a NetworkTables table imu pitch value for data storage and communication.
   */
  public double getImuPitch() {
    return m_targetPitchSubscriber.get();
  }

  /*
   * Retrieves a NetworkTables table imu roll value for data storage and communication.
   */
  public double getImuRoll() {
    return m_imuRollSubscriber.get();
  }

  /*
   * Retrieves a NetworkTables table imu yaw value for data storage and communication.ss
   */
  public double getImuYaw() {
    return m_imuYawSubscriber.get();
  }

  @Override
  public void periodic() {}
}
