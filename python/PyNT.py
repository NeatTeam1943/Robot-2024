import ntcore

inst = ntcore.NetworkTableInstance.getDefault()
table = inst.getTable("RealSense Data")

# Set up the topic
SCREENX = table.getDoubleTopic("ScreenX")
SCREENY = table.getDoubleTopic("ScreenY")

TARGETPITCH = table.getDoubleTopic("TargetPitch")
TARGETROLL = table.getDoubleTopic("TargetRoll")
TARGETYAW = table.getDoubleTopic("TargetYaw")

OPTIMALVELOCITY = table.getDoubleTopic("Optimal Shooter Velocity")
SHOOTERANGLE = table.getDoubleTopic("Optimal Shooter Angle")
DELTAX = table.getDoubleTopic("DeltaX")

# Set up the publishers
SCREENXPUBLISHER = SCREENX.publish()
SCREENYPUBLISHER = SCREENY.publish()

TARGETPITCHPUBLISHER = TARGETPITCH.publish()
TARGETYAWPUBLISHER = TARGETROLL.publish()
TARGETYAWPUBLISHER = TARGETYAW.publish()

OPTIMALVELOCITYPUBLISHER = OPTIMALVELOCITY.publish()
SHOOTERANGLEPUBLISHER = SHOOTERANGLE.publish()
DELTAXPUBLISHER = DELTAX.publish()

inst.startClient4("Test")
inst.setServer(1943) # where TEAM=190, 294, etc, or use inst.setServer("hostname") or similar
inst.startDSClient() # recommended if running on DS computer; this gets the robot IP from the DS

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
    SCREENXPUBLISHER.setDouble(screenX)
    SCREENYPUBLISHER.setDouble(screenY)

    TARGETPITCHPUBLISHER.setDouble(targetPitch)
    TARGETYAWPUBLISHER.setDouble(targetRoll)
    TARGETYAWPUBLISHER.setDouble(targetYaw)

    OPTIMALVELOCITYPUBLISHER.setDouble(optimalVelocity)
    SHOOTERANGLEPUBLISHER.setDouble(shooterAngle)
    DELTAXPUBLISHER.setDouble(deltaX)
