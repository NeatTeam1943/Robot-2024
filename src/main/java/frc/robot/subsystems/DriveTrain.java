package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveTrainConstants;

/**
 * The DriveTrain subsystem controls the robot's drive system.
 */
public class DriveTrain extends SubsystemBase {
  private TalonFX m_leftFront;
  private TalonFX m_leftRear;
  private TalonFX m_rightFront;
  private TalonFX m_rightRear;

  private DifferentialDrive m_drive;

  /**
   * Constructs the DriveTrain subsystem with motor controllers and sets up
   * follower behavior.
   */
  public DriveTrain() {
    m_leftFront = new TalonFX(DriveTrainConstants.kLeftFront);
    m_leftRear = new TalonFX(DriveTrainConstants.kLeftRear);
    m_rightFront = new TalonFX(DriveTrainConstants.kRightFront);
    m_rightRear = new TalonFX(DriveTrainConstants.kRightRear);

    m_rightFront.setInverted(true);
    m_leftFront.setInverted(false);

    m_rightRear.setControl(new Follower(m_rightFront.getDeviceID(), false));
    m_leftRear.setControl(new Follower(m_leftFront.getDeviceID(), false));

    m_drive = new DifferentialDrive(m_leftFront, m_rightFront);
  }

  /**
   * Drives the robot using arcade drive control.
   *
   * @param movement The forward/backward movement speed.
   * @param rotation The rotational speed.
   */
  public void driveArcade(double movement, double rotation) {
    m_drive.arcadeDrive(movement, rotation);
  }

  /**
   * Drives the robot using arcade drive control with an Xbox controller.
   *
   * @param joystick The Xbox controller used for driving.
   */
  public void driveArcade(CommandXboxController joystick) {
    m_drive.arcadeDrive(joystick.getRightTriggerAxis() - joystick.getLeftTriggerAxis(), joystick.getRightX());
  }

  /**
   * Drives the robot using tank drive control.
   *
   * @param left  The speed for the left side of the robot.
   * @param right The speed for the right side of the robot.
   */
  public void driveTank(double left, double right) {
    m_drive.tankDrive(left, right);
  }

  /**
   * Drives the robot using tank drive control with an Xbox controller.
   *
   * @param joystick The Xbox controller used for driving.
   */
  public void driveTank(CommandXboxController joystick) {
    m_drive.tankDrive(joystick.getLeftY(), joystick.getRightY());
  }

  /**
   * Gets the distance traveled by the left front motor in meters.
   *
   * @return The distance traveled by the left front motor in meters.
   */
  public double getLeftFrontMotorTraveledDistance() {
    return m_leftFront.getSelectedSensorPosition() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }

  /**
   * Gets the distance traveled by the right front motor in meters.
   *
   * @return The distance traveled by the right front motor in meters.
   */
  public double getRightFrontMotorTraveledDistance() {
    return m_rightFront.getSelectedSensorPosition() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }

  /**
   * Gets the distance traveled by the left rear motor in meters.
   *
   * @return The distance traveled by the left rear motor in meters.
   */
  public double getLeftRearMotorTraveledDistance() {
    return m_leftRear.getSelectedSensorPosition() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }

  /**
   * Gets the distance traveled by the right rear motor in meters.
   *
   * @return The distance traveled by the right rear motor in meters.
   */
  public double getRightRearMotorTraveledDistance() {
    return m_rightRear.getSelectedSensorPosition() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }

  /**
   * Sets the position of the left front motor in sensor units.
   *
   * @param position The position to set.
   */
  public void setLeftFrontMotorTraveledDistance(double position) {
    m_leftFront.setSelectedSensorPosition(position);
  }

  /**
   * Sets the position of the right front motor in sensor units.
   *
   * @param position The position to set.
   */
  public void setRightFrontMotorTraveledDistance(double position) {
    m_rightFront.setSelectedSensorPosition(position);
  }

  /**
   * Sets the position of the left rear motor in sensor units.
   *
   * @param position The position to set.
   */
  public void setLeftRearMotorTraveledDistance(double position) {
    m_leftRear.setSelectedSensorPosition(position);
  }

  /**
   * Sets the position of the right rear motor in sensor units.
   *
   * @param position The position to set.
   */
  public void setRightRearMotorTraveledDistance(double position) {
    m_rightRear.setSelectedSensorPosition(position);
  }

  /**
   * Resets the encoders of all motors to zero.
   */
  public void resetEncoders() {
    m_leftFront.setSelectedSensorPosition(0);
    m_rightFront.setSelectedSensorPosition(0);
    m_leftRear.setSelectedSensorPosition(0);
    m_rightRear.setSelectedSensorPosition(0);
  }
}
