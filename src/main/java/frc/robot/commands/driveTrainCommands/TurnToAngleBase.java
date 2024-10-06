// package frc.robot.commands.driveTrainCommands;

// import com.ctre.phoenix6.signals.NeutralModeValue;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.Constants.DriveTrainConstants;
// import frc.robot.general.RobotHeading;
// import frc.robot.general.RobotHeadingUtils;
// import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.RobotOdometry;

// public abstract class TurnToAngleBase extends Command {
//   private double m_initialHeading;

//   protected DriveTrain m_drive;
//   protected RobotOdometry m_odometry;
//   protected PIDController m_controller;

//   private boolean m_isSmallDistance;

//   /**
//    * @return The target angle of the robot without the initial heading of the
//    *         robot.
//    */
//   protected abstract double getTargetAngle();

//   public TurnToAngleBase(DriveTrain drive) {
//     m_drive = drive;
//     m_odometry = RobotOdometry.getInstance();

//     addRequirements(m_drive);
//   }

//   @Override
//   public void initialize() {
//     RobotOdometry.getInstance().setHeading(0);

//     m_drive.setMotorMode(NeutralModeValue.Brake);

//     m_initialHeading = m_odometry.getHeading();

//     double targetAngle = getRobotTargetAngle();

//     m_controller = getPID(targetAngle);
//     m_controller.setSetpoint(targetAngle);

//     m_isSmallDistance = Math.abs(m_controller.getPositionError()) < 4;
//   }

//   @Override
//   public void execute() {
//     double calculatedSpeed = m_controller.calculate(m_odometry.getHeading());

//     double pidSpeed = MathUtil.clamp(
//         RobotHeadingUtils.getInstance().isIntakeMode() ? calculatedSpeed : -calculatedSpeed,
//         DriveTrainConstants.kRotationClampLow, DriveTrainConstants.kRotationClampHigh);

//     // if (m_isSmallDistance) {
//     // double slowSpeed = RobotHeadingUtils.getInstance().isIntakeMode() ? 0.3 :
//     // -0.3;

//     // if (m_controller.getPositionError() < 0) {
//     // slowSpeed *= -1;
//     // }

//     // m_drive.rotateRobot(slowSpeed);
//     // } else {
//     // m_drive.rotateRobot(pidSpeed);
//     // }

//     m_drive.rotateRobot(pidSpeed);

//     SmartDashboard.putNumber("SPEED", pidSpeed);
//     SmartDashboard.putNumber("PURE SPEED", m_controller.calculate(m_odometry.getHeading()));
//   }

//   @Override
//   public void end(boolean interrupted) {
//     m_drive.rotateRobot(0);

//     m_drive.setMotorMode(NeutralModeValue.Coast);
//   }

//   @Override
//   public boolean isFinished() {
//     return shouldStop();
//   }

//   private boolean shouldStop() {
//     double positionError = m_controller.getPositionError();
//     double target = getRobotTargetAngle();

//     final double NEGETIVE_TOLERENCE = 2.5;
//     final double POSITIVE_TOLERENCE = -2.5;

//     if (positionError != 0) {
//       String errorType = (positionError > 0) ? "PositionError" : "NegativeError";
//       SmartDashboard.putString("TurnToAngle",
//           String.format("%s: %s, Target: %s", errorType, positionError, target));

//       SmartDashboard.putNumber("IMU HEADING", m_odometry.getHeading());
//       SmartDashboard.putNumber("ERROR", positionError);

//       double heading = m_odometry.getHeading();
//       double tolerance = (positionError > 0) ? POSITIVE_TOLERENCE : NEGETIVE_TOLERENCE;

//       if (positionError > 0) {
//         return target < heading + tolerance;
//       }

//       return target > heading + tolerance;
//     }

//     return true;
//   }

//   /**
//    * @return The target angle of the robot plus the initial heading of the robot.
//    */
//   private double getRobotTargetAngle() {
//     return getTargetAngle() + m_initialHeading;
//   }

//   /**
//    * @param targetAngle - The PID's desired angle setpoint.
//    * @return A PID controller with the correct P, I, and D values. The P, I, and D
//    *         values are multiplied by the constant from getN()
//    */
//   private PIDController getPID(double targetAngle) {
//     final double N = getMultConstant(targetAngle);

//     return new PIDController(DriveTrainConstants.kRotationP * N, DriveTrainConstants.kRotationI * N,
//         DriveTrainConstants.kRotationD * N);
//   }

//   /**
//    * @param targetAngle - The PID's desired angle setpoint.
//    * @return A constant value to multiply the PID's P, I, and D values by.
//    */
//   private double getMultConstant(double targetAngle) {
//     final double BASE_ANGLE = 90;

//     if (Math.abs(targetAngle) < 8) {
//         return 3; // TODO Check constant
//     }

//     return BASE_ANGLE / Math.abs(targetAngle);
//   }
// }
