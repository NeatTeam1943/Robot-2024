package frc.robot.general;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotOdometry;

/** A class responsible for updating the odometry of the robot. */
public class Odometry {
  private DifferentialDriveOdometry m_robotOdomtry;
  private DriveTrain m_drive;
  private RobotOdometry m_robotData;

  private Field2d m_field2d;

  public Odometry(DriveTrain drive, RobotOdometry data) {
    m_robotData = data;
    m_drive = drive;

    m_field2d = new Field2d();

    m_robotOdomtry =
        new DifferentialDriveOdometry(
            m_robotData.getHeadingRotaion2d(),
            m_drive.getLeftFrontMotorTraveledDistance(),
            m_drive.getRightFrontMotorTraveledDistance());

    SmartDashboard.putData(m_field2d);
  }

  /** Updates the odometry. */
  public void update() {
    m_robotOdomtry.update(
        m_robotData.getHeadingRotaion2d(),
        m_drive.getLeftFrontMotorTraveledDistance(),
        m_drive.getRightFrontMotorTraveledDistance());
  }

  /**
   * Resets the odometry for path planner.
   *
   * @param pos2d - The position to reset the odometry to
   */
  public void resetPP(Pose2d pos2d) {
    m_robotOdomtry.resetPosition(
        m_robotData.getHeadingRotaion2d(),
        m_drive.getLeftFrontMotorTraveledDistance(),
        m_drive.getRightFrontMotorTraveledDistance(),
        pos2d);
  }

  /** Resets the odometry to (0, 0). */
  public void reset() {
    m_robotData.setHeading(0);
    m_drive.resetEncoders();

    m_robotOdomtry.update(
        m_robotData.getHeadingRotaion2d(),
        m_drive.getLeftFrontMotorTraveledDistance(),
        m_drive.getRightFrontMotorTraveledDistance());
  }

  /**
   * @return The current position of the robot in meters.
   */
  public Pose2d getCurrentPosMeters() {
    return m_robotOdomtry.getPoseMeters();
  }

  /** Sets the field to an updated position. */
  public void updateRobotPoseOnField() {
    m_field2d.setRobotPose(getCurrentPosMeters());
  }

  /**
   * @return Gets the current wheel speeds of the robot.
   */
  public WheelSpeeds getCurrentWheelSpeeds() {
    return new WheelSpeeds(m_drive.getLeftMasterVelocity(), m_drive.getRightMasterVelocity());
  }

  /**
   * @return Gets the current ChassisSpeeds object.
   */
  public ChassisSpeeds getCurrentChassisSpeeds() {
    return ChassisSpeeds.fromFieldRelativeSpeeds(
        m_drive.getVelocityX(),
        0.0,
        m_robotData.getAngularVelocityRads(),
        m_robotData.getHeadingRotaion2d());
  }

  /**
   * @return True if the robot is on the red alliance, false if the robot is on the blue alliance.
   */
  public boolean isRedAlliance() {
    var alliance = DriverStation.getAlliance();

    if (alliance.isPresent()) {
      return alliance.get() == DriverStation.Alliance.Red;
    }

    return false;
  }
}
