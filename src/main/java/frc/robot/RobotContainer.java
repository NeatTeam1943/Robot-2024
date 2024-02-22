package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.driveTrainCommands.TurnToAngle;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotOdometry;

public class RobotContainer {
  private final CommandXboxController m_driverController;
  private final RobotOdometry m_odometry;
  private final DriveTrain m_drive;

  public RobotContainer() {
    m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
    m_odometry = new RobotOdometry();
    m_drive = new DriveTrain();

    configureBindings();
  }

  private void configureBindings() {
    
    m_driverController.a().onTrue(new TurnToAngle(m_drive, m_odometry, 90));
    m_driverController.b().whileTrue(new RunCommand(() -> m_odometry.setHeading(0)));
    m_drive.setDefaultCommand(new RunCommand(() -> m_drive.driveArcade(m_driverController), m_drive));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
