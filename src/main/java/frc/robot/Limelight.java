package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.FieldConstants;
import frc.robot.Constants.LimeLightConstants;
import frc.robot.Constants.MeasurementConstants;
import frc.robot.Constants.VisionConstants;

/**
 * The Limelight class provides methods to interact with the Limelight vision
 * system.
 */
public class Limelight {

    /**
     * Enum representing different vision targets.
     */
    public static enum Target {
        AMP(FieldConstants.kAmpATHeightMeters, new double[] { FieldConstants.kRedAmpATId, FieldConstants.kBlueAmpATId }),
        SPEAKER(FieldConstants.kSpeakerATHeightMeters, new double[] { FieldConstants.kBlueSpeakerATFirstId, FieldConstants.kBlueSpeakerATSecondId, FieldConstants.kRedSpeakerATFirstId, FieldConstants.kRedSpeakerATSecondId }),
        HUMAN_PLAYER(FieldConstants.kHumanPlayerATHeightMeters, new double[] { FieldConstants.kBlueHumanATPlayerFirstId, FieldConstants.kBlueHumanATPlayerSecondId, FieldConstants.kRedHumanATPlayerFirstId, FieldConstants.kRedHumanATPlayerSecondId });

        private double m_goalHeight;
        private double[] m_aprilTagIds;

        private Target(double goalHeight, double[] aprilTagIds) {
            m_goalHeight = goalHeight;
            m_aprilTagIds = aprilTagIds;
        }

        /**
         * Gets the goal height associated with the target.
         * 
         * @return The goal height in meters.
         */
        public double getGoalHeight() {
            return m_goalHeight;
        }

        /**
         * Gets the array of AprilTag IDs associated with the target.
         * 
         * @return The array of AprilTag IDs.
         */
        public double[] getAprilTagIds() {
            return m_aprilTagIds;
        }
    }

    /**
     * Displays the Limelight axes values on the SmartDashboard.
     */
    public static void showAxes() {
        double yaw = LimelightHelpers.getTX("limelight-nt");
        double pitch = LimelightHelpers.getTY("limelight-nt");
        double roll = LimelightHelpers.getTA("limelight-nt");

        SmartDashboard.putNumber("YAW", yaw);
        SmartDashboard.putNumber("PITCH", pitch);
        SmartDashboard.putNumber("ROLL", roll);
    }

    /**
     * Gets the target yaw angle from the Limelight.
     * 
     * @return The target yaw angle in degrees.
     */
    public static double getTargetYaw() {
        return LimelightHelpers.getTX("limelight-nt");
    }

    /**[]\
     * Checks if the Limelight has a target in view.
     * 
     * @return True if a target is present, false otherwise.
     */
    public static boolean hasTarget() {
        return LimelightHelpers.getTV("limelight-nt");
    }

    // public static boolean seeSpeaker() {
    //     for (int i = (DriverStation.) 0; i < Target.SPEAKER.getAprilTagIds().length; i++){
    //         return false;
    //     }
    // }

    /**
     * Gets the area of the target from the Limelight.
     * 
     * @return The target area.
     */
    public static double getTargetArea() {
        return LimelightHelpers.getTA("limelight-nt");
    }

    /**
     * Calculates the distance from the Limelight to a specified target.
     * 
     * @param target The target for which the distance is calculated.
     * @return The distance from the Limelight to the target in meters.
     */
    public static double getDistanceFrom(Target target) {
        double targetOffsetAngle_Vertical = LimelightHelpers.getTY(LimeLightConstants.kLimelightName);

        double limelightMountAngleDegrees = LimeLightConstants.kLimelightPitch;

        double limelightLensHeightInches = FieldConstants.kCameraHeightMeters * MeasurementConstants.kMeterToInches;

        double goalHeightInches = target.getGoalHeight() * MeasurementConstants.kMeterToInches;

        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (MeasurementConstants.kDegToRad);

        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)
                / Math.tan(angleToGoalRadians);

        return distanceFromLimelightToGoalInches * MeasurementConstants.kInchesToMeters - VisionConstants.kLimelightToBamperMeters;
    }
}
