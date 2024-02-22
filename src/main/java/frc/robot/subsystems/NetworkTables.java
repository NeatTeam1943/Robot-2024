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

  private DoubleTopic m_optimalShooterVelocity;
  private DoubleTopic m_optimalShooterAngle;
  private DoubleTopic m_deltaX;

  // Publishers
  private DoublePublisher m_imuPitchPublisher;
  private DoublePublisher m_imuRollPublisher;
  private DoublePublisher m_imuYawPublisher;

  private DoublePublisher m_optimalShooterVelocitPublisher;
  private DoublePublisher m_optimalShooterAnglePublisher;
  private DoublePublisher m_deltaXPublisher;
  
  // Subscribers
  private DoubleSubscriber m_screenXSubscriber;
  private DoubleSubscriber m_screenYSubscriber;

  private DoubleSubscriber m_targetPitchSubscriber;
  private DoubleSubscriber m_targetRollSubscriber;
  private DoubleSubscriber m_targetYawSubscriber;

  private DoubleSubscriber m_imuPitchSubscriber;
  private DoubleSubscriber m_imuRollSubscriber;
  private DoubleSubscriber m_imuYawSubscriber;

  private DoubleSubscriber m_optimalShooterVelocitySubscriber;
  private DoubleSubscriber m_optimalShooterAngleSubscriber;
  private DoubleSubscriber m_deltaXPSubscruber;

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

    m_optimalShooterVelocity = m_table.getDoubleTopic("Optimal Shooter Velocity");
    m_optimalShooterAngle = m_table.getDoubleTopic("Optimal Shooter Angle");
    m_deltaX = m_table.getDoubleTopic("Delta X");

    // Publishers
    m_imuPitchPublisher = m_imuPitch.publish();
    m_imuRollPublisher = m_imuRoll.publish();
    m_imuYawPublisher = m_imuYaw.publish();

    m_optimalShooterVelocitPublisher = m_optimalShooterVelocity.publish();
    m_optimalShooterAnglePublisher = m_optimalShooterAngle.publish();
    m_deltaXPublisher = m_deltaX.publish();

    // Subscribers
    m_screenXSubscriber = m_screenX.subscribe(0);
    m_screenYSubscriber = m_screenY.subscribe(0);

    m_targetPitchSubscriber = m_targetPitch.subscribe(0);
    m_targetRollSubscriber = m_targetRoll.subscribe(0);
    m_targetYawSubscriber = m_targetYaw.subscribe(0);

    m_imuPitchSubscriber = m_imuPitch.subscribe(0);
    m_imuRollSubscriber = m_imuRoll.subscribe(0);
    m_imuYawSubscriber = m_imuYaw.subscribe(0);

    m_optimalShooterVelocitySubscriber = m_optimalShooterVelocity.subscribe(0);
    m_optimalShooterAngleSubscriber = m_optimalShooterAngle.subscribe(0);
    m_deltaXPSubscruber = m_deltaX.subscribe(0);
  }

  /*
   * @return A NetworkTable table for subscribing , or publishing.
   * This method just returns the NetworkTable table
   */
  public NetworkTable getTable() {
    return m_table;
  }

  /*
   * @param pitch - The pitch value the IMU we use provides
   * Sets the NetworkTable value that represents the IMU's pitch value as the pitch we provide
   */
  public void setImuPitch(double pitch) {
    m_imuPitchPublisher.set(pitch);
  }

  /*
   * @param roll - The roll value the IMU we use provides
   * Sets the NetworkTable value that represents the IMU's roll value as the pitch we provide
   */
  public void setImuRoll(double roll) {
    m_imuRollPublisher.set(roll);
  }

  /*
   * @param yaw - The yaw value the IMU we use provides
   * Sets the NetworkTable value that represents the IMU's yaw value as the pitch we provide
   */
  public void setImuYaw(double yaw) {
    m_imuYawPublisher.set(yaw);
  }

  /*
   * @param velocity - The optimal velocity of the shooter
   * Sets the NetworkTable value that represents the optimal velocity of the shooter
   */
  public void setOptimalShooterVelocity(double velocity) {
    m_optimalShooterVelocitPublisher.set(velocity);
  }

  /*
   * @param angle - The optimal angle of the shooter
   * Sets the NetworkTable value that represents the optimal angle of the shooter
   */
  public void setOptimalShooterAngle(double angle) {
    m_optimalShooterAnglePublisher.set(angle);
  }

  /*
   * @param deltaX - The delta X value of the target
   * Sets the NetworkTable value that represents the delta Y value of the target
   */
  public void setDeltaX(double deltaX) {
    m_deltaXPublisher.set(deltaX);
  }

  /*
   * @return A position along the X axis of a screen(the unit is pixels)
   * Provides the latest position of the target in the camera's screen(along the X axis)
   */
  public double getScreenX() {
    return m_screenXSubscriber.get();
  }

  /*
   * @return A position along the Y axis of a screen(the unit is pixels)
   * Provides the latest position of the target in the camera's screen(along the Y axis)
   */
  public double getScreenY() {
    return m_screenYSubscriber.get();
  }

  /*
   * @return The target's pitch from the camera
   * Provides the latest pitch value from the target from the camera's view
   */
  public double getTargetPitch() {
    return m_targetPitchSubscriber.get();
  }

  /*
   * @return The target's roll from the camera
   * Provides the latest roll value from the target from the camera's view
   */
  public double getTargetRoll() {
    return m_targetRollSubscriber.get();
  }

  /*
   * @return The target's yaw from the camera
   * Provides the latest yaw value from the target from the camera's view
   */
  public double getTargetYaw() {
    return m_targetYawSubscriber.get();
  }

  /*
   * @return The IMU pitch value that's registered in the NetworkTables
   * Provides the latest IMU pitch value that's in the NetworkTable
   */
  public double getImuPitch() {
    return m_targetPitchSubscriber.get();
  }

  /*
   * @return The IMU roll value that's registered in the NetworkTables
   * Provides the latest IMU roll value that's in the NetworkTable
   */
  public double getImuRoll() {
    return m_imuRollSubscriber.get();
  }

  /*
   * @return The IMU yaw value that's registered in the NetworkTables
   * Provides the latest IMU yaw value that's in the NetworkTable
   */
  public double getImuYaw() {
    return m_imuYawSubscriber.get();
  }

  /*
   * @return The optimal velocity of the shooter that's registered in the NetworkTables
   * Provides the latest optimal velocity of the shooter that's in the NetworkTable
   */
  public double getOptimalShooterVelocity() {
    return m_optimalShooterVelocitySubscriber.get();
  }

  /*
   * @return The optimal angle of the shooter that's registered in the NetworkTables
   * Provides the latest optimal angle of the shooter that's in the NetworkTable
   */
  public double getOptimalShooterAngle() {
    return m_optimalShooterAngleSubscriber.get();
  }

  /*
   * @return The delta X value of the target that's registered in the NetworkTables
   * Provides the latest delta Y value of the target that's in the NetworkTable
   */
  public double getDeltaX() {
    return m_deltaXPSubscruber.get();
  }

  @Override
  public void periodic() {}
}
