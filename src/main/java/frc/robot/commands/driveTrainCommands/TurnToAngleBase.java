package frc.robot.commands.driveTrainCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotOdometry;

public abstract class TurnToAngleBase extends Command {
  private double m_initialHeading;

  protected DriveTrain m_drive;
  protected RobotOdometry m_odometry;
  protected PIDController m_controller;

  /**
   * @return The target angle of the robot without the initial heading of the robot.
   */
  protected abstract double getTargetAngle();

  public TurnToAngleBase(DriveTrain drive, RobotOdometry odometry) {
    m_drive = drive;
    m_odometry = odometry;

    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_initialHeading = m_odometry.getHeading();

    double targetAngle = getRobotTargetAngle();

    m_controller = getPID(targetAngle);
    m_controller.setSetpoint(targetAngle);
  }

  @Override
  public void execute() {
    double pidSpeed = MathUtil.clamp(m_controller.calculate(m_odometry.getHeading()),
        DriveTrainConstants.kRotationClampLow, DriveTrainConstants.kRotationClampHigh);

    m_drive.rotateRobot(pidSpeed);

    SmartDashboard.putData(m_controller);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.rotateRobot(0);
  }

  @Override
  public boolean isFinished() {
    return shouldStop();
  }

  private boolean shouldStop() {
    double positionError = m_controller.getPositionError();
    double target = getRobotTargetAngle();

    final double NEGETIVE_TOLERENCE = -2;
    final double POSITIVE_TOLERENCE = 1;

    if (positionError != 0) {
      String errorType = (positionError > 0) ? "PositionError" : "NegativeError";
      SmartDashboard.putString("TurnToAngle",
          String.format("%s: %s, Target: %s", errorType, positionError, target));

      SmartDashboard.putNumber("IMU HEADING", m_odometry.getHeading());
      
      double heading = m_odometry.getHeading();
      double tolerance = (positionError > 0) ? POSITIVE_TOLERENCE : NEGETIVE_TOLERENCE;

      return getRobotTargetAngle() < heading + tolerance;
    }

    return true;
  }

  /**
   * @return The target angle of the robot plus the initial heading of the robot.
   */
  private double getRobotTargetAngle() {
    return getTargetAngle() + m_initialHeading;
  }

  /**
   * @param targetAngle - The PID's desired angle setpoint.
   * @return A PID controller with the correct P, I, and D values. The P, I, and D
   *         values are multiplied by the constant from getN()
   */
  private PIDController getPID(double targetAngle) {
    final double N = getMultConstant(targetAngle);

    return new PIDController(DriveTrainConstants.kRotationP * N, DriveTrainConstants.kRotationI * N,
        DriveTrainConstants.kRotationD * N);
  }

  /**
   * @param targetAngle - The PID's desired angle setpoint.
   * @return A constant value to multiply the PID's P, I, and D values by.
   */
  private double getMultConstant(double targetAngle) {
    final double BASE_ANGLE = 90;

    return BASE_ANGLE / Math.abs(targetAngle);
  }
}
