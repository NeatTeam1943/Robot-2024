package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
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
  /**
   * Masters are rear motors and Followers are front motors.
   */
  private TalonFX m_leftFollower;
  private TalonFX m_leftMaster;
  private TalonFX m_rightFollower;
  private TalonFX m_rightMaster;

  private DifferentialDrive m_drive;

  /**
   * Constructs the DriveTrain subsystem with motor controllers and sets up
   * follower behavior.
   */
  public DriveTrain() {
    m_leftMaster = new TalonFX(DriveTrainConstants.kLeftRear);
    m_rightMaster = new TalonFX(DriveTrainConstants.kRightRear);
    m_leftFollower = new TalonFX(DriveTrainConstants.kLeftFront);
    m_rightFollower = new TalonFX(DriveTrainConstants.kRightFront);

    m_rightMaster.setInverted(true);
    m_leftMaster.setInverted(false);

    m_leftMaster.setControl(new Follower(m_leftFollower.getDeviceID(), false));
    m_rightMaster.setControl(new Follower(m_rightFollower.getDeviceID(), false));

    m_drive = new DifferentialDrive(m_leftMaster, m_rightMaster);
  }

  /**
   * Drives the robot using arcade drive control.
   *
   * @param movement - The forward/backward movement speed.
   * @param rotation - The rotational speed.
   */
  public void driveArcade(double movement, double rotation) {
    m_drive.arcadeDrive(movement, rotation);
  }

  /**
   * Drives the robot using arcade drive control with an Xbox controller.
   *
   * @param joystick - The Xbox controller used for driving.
   */
  public void driveArcade(CommandXboxController joystick) {
    m_drive.arcadeDrive(joystick.getRightTriggerAxis() - joystick.getLeftTriggerAxis(), -joystick.getLeftX());
  }

  /**
   * Drives the robot using tank drive control.
   *
   * @param left - The speed for the left side of the robot.
   * @param right - The speed for the right side of the robot.
   */
  public void driveTank(double left, double right) {
    m_drive.tankDrive(left, right);
  }

  /**
   * Drives the robot using tank drive control with an Xbox controller.
   *
   * @param joystick - The Xbox controller used for driving.
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
    StatusSignal<Double> leftFrontRotorSignal = m_leftFollower.getRotorPosition();
    leftFrontRotorSignal.refresh();

    return leftFrontRotorSignal.getValue() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }

  /**
   * Gets the distance traveled by the right front motor in meters.
   *
   * @return The distance traveled by the right front motor in meters.
   */
  public double getRightFrontMotorTraveledDistance() {
    StatusSignal<Double> rightFrontRotorSignal = m_rightFollower.getRotorPosition();
    rightFrontRotorSignal.refresh();

    return rightFrontRotorSignal.getValue() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }

  /**
   * Gets the distance traveled by the left rear motor in meters.
   *
   * @return The distance traveled by the left rear motor in meters.
   */
  public double getLeftRearMotorTraveledDistance() {
    StatusSignal<Double> leftRearRotorSignal = m_leftMaster.getRotorPosition();
    leftRearRotorSignal.refresh();

    return leftRearRotorSignal.getValue() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }

  /**
   * Gets the distance traveled by the right rear motor in meters.
   *
   * @return The distance traveled by the right rear motor in meters.
   */
  public double getRightRearMotorTraveledDistance() {
    StatusSignal<Double> rightRearRotorSignal = m_rightMaster.getRotorPosition();
    rightRearRotorSignal.refresh();

    return rightRearRotorSignal.getValue() * DriveTrainConstants.kEncoderSensorRotationsToMeters;
  }

  /**
   * Sets the position of the left front motor in sensor units.
   *
   * @param position - The position to set.
   */
  public void setLeftFrontMotorTraveledDistance(double position) {
    m_leftFollower.setPosition(position);
  }

  /**
   * Sets the position of the right front motor in sensor units.
   *
   * @param position - The position to set.
   */
  public void setRightFrontMotorTraveledDistance(double position) {
    m_rightFollower.setPosition(position);
  }

  /**
   * Sets the position of the left rear motor in sensor units.
   *
   * @param position - The position to set.
   */
  public void setLeftRearMotorTraveledDistance(double position) {
    m_leftMaster.setPosition(position);
  }

  /**
   * Sets the position of the right rear motor in sensor units.
   *
   * @param position - The position to set.
   */
  public void setRightRearMotorTraveledDistance(double position) {
    m_rightMaster.setPosition(position);
  }

  /**
   * Resets the encoders of all motors to zero.
   */
  public void resetEncoders() {
    m_leftMaster.setPosition(0);
    m_rightMaster.setPosition(0);
    m_leftFollower.setPosition(0);
    m_rightFollower.setPosition(0);
  }
}
