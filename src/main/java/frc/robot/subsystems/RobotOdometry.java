package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.Pigeon2;

/*
 * RobotOdometry contains data from spatial sensors.
 */
public class RobotOdometry extends SubsystemBase {
  private Pigeon2 m_imu;

  /*
   * Constructs RobotOdometry subsystem.
   */
  public RobotOdometry() {
    m_imu = new Pigeon2(0);
  }

  /*
   * Sets position of robot in a 2d field.
   */
  public void setPosition2D(Pose2d position) {

  }

  /*
   * Gets position of robot in a 2d field.
   */
  public Pose2d getPosition2D() {

    return null;
  }

  /*
   * Sets position of robot in a 3d field.
   */
  public void setPosition3D(Pose3d position) {

  }

  /*
   * Gets position of robot in a 3d field.
   */
  public Pose2d getPosition3D() {

    return null;
  }

  /*
   * Sets heading of robot relative to a selected heading.
   */
  public void setHeading(double angle) {
    m_imu.setYaw(angle);
  }

  /*
   * Gets heading of robot relative to a selected heading.
   */
  public double getHeading() {
    return m_imu.getYaw().getValueAsDouble();
  }

  /*
   * Gets the distance of the robot from an AprilTag.
   */
  public double getDistanceToAT() {

    return 0;
  }

  /*
   * Gets the difference of the pitch of the robot from that of the AprilTag using
   * LimeLight.
   */
  public double getPitchToAT() {

    return 0;
  }

  /*
   * Gets the difference of the yaw of the robot from that of the AprilTag using
   * LimeLight.
   */
  public double getYawToAT() {

    return 0;
  }

  /*
   * Gets the distance of the robot from a Note using RealSense.
   */
  public double getDistanceToNote() {

    return 0;
  }

  /*
   * Gets the angle of the robot from a Note using RealSense.
   */
  public double getAngleToNote() {

    return 0;
  }

  /*
   * Gets the velocity of the robot using IMU.
   */
  public double getVelocity() {

    return 0;
  }

  /*
   * Gets the acceleration of the robot using IMU.
   */
  public double getAcceleration() {

    return 0;
  }

  /*
   * Gets the position of a Note inside the robot using a time-of-flight sensor.
   */
  public double getDistanceTOF() {

    return 0;
  }
}
