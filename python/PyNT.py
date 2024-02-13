import ntcore

INST = ntcore.NetworkTableInstance.getDefault()
TABLE = INST.getTable("RealSense Data")

# Set up the topic
screenX = TABLE.getDoubleTopic("ScreenX")
screenY = TABLE.getDoubleTopic("ScreenY")

targetPitch = TABLE.getDoubleTopic("TargetPitch")
targetRoll = TABLE.getDoubleTopic("TargetRoll")
targetYaw = TABLE.getDoubleTopic("TargetYaw")

optimalVelocity = TABLE.getDoubleTopic("Optimal Shooter Velocity")
shooterAngle = TABLE.getDoubleTopic("Optimal Shooter Angle")
deltaX = TABLE.getDoubleTopic("DeltaX")

# Set up the publishers
screenXPublisher = screenX.publish()
screenYPublisher = screenY.publish()

targetPitchPublisher = targetPitch.publish()
targetRollPublisher = targetRoll.publish()
targetYawPublisher = targetYaw.publish()

optimalVelocityPublisher = optimalVelocity.publish()
shooterAnglePublisher = shooterAngle.publish()
deltaXPublisher = deltaX.publish()

INST.startClient4("Test")
INST.setServer(1943) # where TEAM=190, 294, etc, or use inst.setServer("hostname") or similar
INST.startDSClient() # recommended if running on DS computer; this gets the robot IP from the DS

# Main loop
while True:
    # Get the data(Change to values gotten by the RealSense Camera)
    screenX = 0.0
    screenY = 0.0

    targetPitch = 0.0
    targetRoll = 0.0
    targetYaw = 0.0

    optimalVelocity = 0.0
    shooterAngle = 0.0
    deltaX = 0.0

    # Publish the data
    screenXPublisher.setDouble(screenX)
    screenYPublisher.setDouble(screenY)

    targetPitchPublisher.setDouble(targetPitch)
    targetRollPublisher.setDouble(targetRoll)
    targetRollPublisher.setDouble(targetYaw)

    optimalVelocityPublisher.setDouble(optimalVelocity)
    shooterAnglePublisher.setDouble(shooterAngle)
    deltaXPublisher.setDouble(deltaX)
