package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.VisionConstants;
import frc.robot.Constants.MeasurementConstants;
import frc.robot.Constants.FieldConstants;

public class Limelight {
    public enum Target {
        AMP(FieldConstants.kAmpATHeightMeters),
        SPEAKER(0), // TEMP VALUE
        HUMAN_PLAYER(0); // TEMP VALUE

        private double m_goalHeight;

        private Target(double goalHeight) {
            m_goalHeight = goalHeight;
        }

        public double getGoalHeight() {
            return m_goalHeight;
        }
    }

    public void showAxes() {
        double yaw = LimelightHelpers.getTX(VisionConstants.kLimelightName);
        double pitch = LimelightHelpers.getTY(VisionConstants.kLimelightName);
        double roll = LimelightHelpers.getTA(VisionConstants.kLimelightName);

        SmartDashboard.putNumber("YAW", yaw);
        SmartDashboard.putNumber("PITCH", pitch);
        SmartDashboard.putNumber("ROLL", roll);
    }

    public double getTargetYaw() {
        return LimelightHelpers.getTX(VisionConstants.kLimelightName);
    }

    public boolean hasTarget() {
        return LimelightHelpers.getTV(VisionConstants.kLimelightName);
    }

    public double getTargetArea() {
        return LimelightHelpers.getTA(VisionConstants.kLimelightName);
    }

    public double getTargetWidth() {
        return NetworkTableInstance.getDefault().getTable(VisionConstants.kLimelightName).getEntry("tshort")
                .getDouble(0);
    }

    public double getTargetHeight() {
        return NetworkTableInstance.getDefault().getTable(VisionConstants.kLimelightName).getEntry("tlong")
                .getDouble(0);
    }

    public double getDistanceFrom(Target target) {
        double targetOffsetAngle_Vertical = LimelightHelpers.getTY(VisionConstants.kLimelightName);

        double limelightMountAngleDegrees = VisionConstants.kLimelightPitch;

        double limelightLensHeightInches = VisionConstants.kLimelightHeightMeters * MeasurementConstants.kMeterToInches;

        double goalHeightInches = target.getGoalHeight() * MeasurementConstants.kMeterToInches;

        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (MeasurementConstants.kDegToRad);

        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)
                / Math.tan(angleToGoalRadians);

        return distanceFromLimelightToGoalInches * MeasurementConstants.kInchesToMeters;
    }
}
