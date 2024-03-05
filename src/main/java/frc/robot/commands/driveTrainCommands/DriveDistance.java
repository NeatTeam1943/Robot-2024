package frc.robot.commands.driveTrainCommands;

import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.general.RobotHeadingUtils;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends Command {
  private DriveTrain m_drive;

  private double m_setpoint;
  private double m_currentDistance;
  private double m_speed;

  public DriveDistance(DriveTrain drive, double distance, double speed) {
    m_drive = drive;

    m_setpoint = distance;
    m_speed = speed;

    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.setMotorMode(NeutralModeValue.Brake);

    System.out.println(
        "========== Start DriveDistance( "
            + m_setpoint
            + " meters, "
            + " ) ==========");

    m_currentDistance = RobotHeadingUtils.getInstance().isIntakeMode()
        ? m_drive.getAverageDistance()
        : m_drive.getAverageDistance();

    m_setpoint = RobotHeadingUtils.getInstance().isIntakeMode()
        ? m_currentDistance - m_setpoint
        : m_currentDistance + m_setpoint;
  }

  @Override
  public void execute() {
    m_currentDistance = RobotHeadingUtils.getInstance().isIntakeMode()
        ? m_drive.getAverageDistance()
        : m_drive.getAverageDistance();

    double speed = RobotHeadingUtils.getInstance().isShooterMode() ? -m_speed : m_speed;

    m_drive.driveTank(speed, speed);

    System.out.println("Drove: " + m_currentDistance + " meters");
    System.out.println("Distance to setpoint: " + (m_currentDistance - m_setpoint) + " meters");

    SmartDashboard.putNumber("Current Distance", m_currentDistance);
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("========== Finished DriveDistance() ==========");
  }

  @Override
  public boolean isFinished() {
    return RobotHeadingUtils.getInstance().isIntakeMode()
        ? m_currentDistance < m_setpoint
        : m_currentDistance > m_setpoint;
  }
}