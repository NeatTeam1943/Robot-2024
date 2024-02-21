    public static enum Target {
        AMP(FieldConstants.kAmpATHeightMeters, new double[] { FieldConstants.kAmpATId }),
        SPEAKER(FieldConstants.kSpeakerATHeightMeters, new double[] { FieldConstants.kSpeakerATFirstId, FieldConstants.kSpeakerATSecondId }),
        HUMAN_PLAYER(FieldConstants.kHumanPlayerATHeightMeters, new double[] { FieldConstants.kHumanATPlayerId });

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
