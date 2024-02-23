package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis;
import frc.robot.Constants.MeasurementConstants;
import frc.robot.Constants.RobotDataConstants;

import com.ctre.phoenix6.hardware.Pigeon2;

/*
 * RobotOdometry contains data from spatial sensors.
 */
public class RobotOdometry {
  private static RobotOdometry instance; // Private static instance variable
  // private Pigeon2 m_imu;
  private ADIS16470_IMU m_imu;
  
  /*
   * Constructs RobotOdometry subsystem.
   */
  private RobotOdometry() {
    // m_imu = new Pigeon2(RobotDataConstants.kPigeon);
    m_imu = new ADIS16470_IMU();
  }

  /*
   * Sets position of robot in a 2d field.
   */
  public void setPosition2D(Pose2d position) {
    // Implement logic to set position
  }


  public boolean isSpeakerVisible() {
    // Implement logic to check speaker visibility
    return false;
  }

  public boolean isAMPVisible() {
    // Implement logic to check AMP visibility
    return false;
  }

  /*
   * Gets position of robot in a 2d field.
   */
  public Pose2d getPosition2D() {
    // Implement logic to get position
    return null;
  }

  /*
   * Sets position of robot in a 3d field.
   */
  public void setPosition3D(Pose3d position) {
    // Implement logic to set   
  }

  /*
   * Gets position of robot in a 3d field.
   */
  public Pose2d getPosition3D() {
    // Implement logic to get position
    return null;
  }

  /*
   * Sets heading of robot relative to a selected heading.
   */
  public void setHeading(double angle) {
    // m_imu.setYaw(angle);
    m_imu.setGyroAngle(IMUAxis.kYaw, angle);
  }

  /*
   * Gets heading of robot relative to a selected heading.
   */
  public double getHeading() {
    // return m_imu.getYaw().getValueAsDouble();
    return m_imu.getAngle(IMUAxis.kYaw);
  }

  public Rotation2d getHeadingRotaion2d() {
    // return m_imu.getRotation2d();
    return new Rotation2d(m_imu.getAngle(IMUAxis.kYaw));
  }

  /*
   * Gets the distance of the robot from an AprilTag.
   */
  public double getDistanceToAT() {
    // Implement logic to get distance
    return 0;
  }

  /*
   * Gets the difference of the pitch of the robot from that of the AprilTag using
   * LimeLight.
   */
  public double getPitchToAT() {
    // Implement logic to get pitch
    return 0;
  }

  /*
   * Gets the difference of the yaw of the robot from that of the AprilTag using
   * LimeLight.
   */
  public double getYawToAT() {
    // Implement logic to get yaw
    return 0;
  }

  /*
   * Gets the distance of the robot from a Note using RealSense.
   */
  public double getDistanceToNote() {
    // Implement logic to get distance
    return 0;
  }

  /*
   * Gets the angle of the robot from a Note using RealSense.
   */
  public double getAngleToNote() {
    // Implement logic to get angle
    return 0;
  }

  /*
   * Gets the velocity of the robot using IMU.
   */
  public double getVelocity() {
    // Implement logic to get velocity
    return 0;
  }

  /*
   * Gets the acceleration of the robot using IMU.
   */
  public double getAcceleration() {
    // Implement logic to get acceleration
    return 0;
  }

  /*
   * Gets the position of a Note inside the robot using a time-of-flight sensor.
   */
  public double getDistanceTOF() {
    // Implement logic to get distance
    return 0;
  }

  /**
   * Gets the single instance of RobotData.
   * @return the instance of RobotData
   */
  public static RobotOdometry getInstance() {
    if (instance == null) {
      instance = new RobotOdometry();
    }
    return instance;
  }

  public double getAngularVelocityRads(){
    return m_imu.getRate() * MeasurementConstants.kDegreesPerSecToRadsPerSec;
  }
}
