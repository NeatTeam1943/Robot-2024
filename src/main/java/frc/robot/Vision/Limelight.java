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

