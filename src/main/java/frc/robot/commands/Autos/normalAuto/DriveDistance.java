package frc.robot.commands.Autos.normalAuto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.general.RobotHeadingUtils;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends Command {
  private DriveTrain m_drive;

  private double m_setpoint;
  private double m_currentDistance;
  private double m_speed;
  private RobotHeadingUtils m_currentHeading;

  public DriveDistance(DriveTrain drive, double distance, double speed) {
    m_drive = drive;

    m_setpoint = distance;
    m_speed = speed;

    m_currentHeading = RobotHeadingUtils.getInstance();

    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    System.out.println(
        "========== Start DriveDistance( "
            + m_setpoint
            + " meters, "
            + m_currentHeading
            + " ) ==========");

    m_currentDistance = m_drive.getLeftFrontMotorTraveledDistance();

    if (m_currentHeading.isIntakeMode()) {
      m_setpoint = m_currentDistance - m_setpoint;
    } else {
      m_setpoint = m_currentDistance + m_setpoint;
    }
  }

  @Override
  public void execute() {
    m_currentDistance = m_drive.getLeftFrontMotorTraveledDistance();

    if (m_currentHeading.isIntakeMode()) {
      m_drive.driveArcade(m_speed, 0);
    } else {
      m_drive.driveArcade(-m_speed, 0);
    }

    System.out.println("Drove: " + m_currentDistance + " meters");
    System.out.println("Distance to setpoint: " + (m_currentDistance - m_setpoint) + " meters");
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("========== Finished DriveDistance() ==========");
  }

  @Override
  public boolean isFinished() {
    if (m_currentHeading.isIntakeMode()) {
      return m_currentDistance < m_setpoint;
    } else {
      return m_currentDistance > m_setpoint;
    }
  }
}