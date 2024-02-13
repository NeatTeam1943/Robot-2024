package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

/**
 * Sets the RPM of each of the shooter motors using PID control.
 */
public class SetShooterRPM extends Command {
  private Shooter m_shooter;
  
  private PIDController m_leftController;
  private PIDController m_rightController;

  private double m_setpoint;

  /** Creates a new shooting. */
  public SetShooterRPM(Shooter shooter, double setpoint) {
    m_setpoint = setpoint;
    m_shooter = shooter;
    
    m_leftController  = new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD);
    m_rightController = new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD);

    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    m_leftController.setTolerance(ShooterConstants.kLeftControllerThreshold);
    m_rightController.setTolerance(ShooterConstants.kRightControllerThreshold);

    m_leftController.setSetpoint(m_setpoint);
    m_rightController.setSetpoint(m_setpoint);
  }

  @Override
  public void execute() {
    m_shooter.setLeftShooterMotorSpeed(m_leftController.calculate(m_shooter.getLeftRPM(), m_setpoint));
    m_shooter.setRightShooterMotorSpeed(m_rightController.calculate(m_shooter.getRightRPM(), m_setpoint));
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.setShooterMotorsSpeed(0); // TODO Set the motors to a default speed.
  } 

  @Override
  public boolean isFinished() {
    return m_leftController.atSetpoint() && (m_rightController.atSetpoint());
  }
}