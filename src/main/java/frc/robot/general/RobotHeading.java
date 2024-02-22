package frc.robot.general;

import frc.robot.Constants.DriveTrainConstants;

public enum RobotHeading {
    INTAKE(DriveTrainConstants.kIntakeHeadingLeftInverted, DriveTrainConstants.kIntakeHeadingRightInverted),
    SHOOTER(DriveTrainConstants.kShooterHeadingLeftInverted, DriveTrainConstants.kShooterHeadingRightInverted);

    private boolean m_invertLeftMotor, m_invertRightMotor;

    private RobotHeading(boolean invertLeftMotor, boolean invertRightMotor) {
        m_invertLeftMotor = invertLeftMotor;
        m_invertRightMotor = invertRightMotor;
    }

    public boolean shouldInvertLeftMotors() {
        return m_invertLeftMotor;
    }

    public boolean shouldInvertRightMotors() {
        return m_invertRightMotor;
    }
}
