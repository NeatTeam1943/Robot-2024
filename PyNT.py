import ntcore

inst = ntcore.NetworkTableInstance.getDefault()
table = inst.getTable("RealSense Data")

# Set up the topic
m_screenX = table.getDoubleTopic("ScreenX")
m_screenY = table.getDoubleTopic("ScreenY")

m_targetPitch = table.getDoubleTopic("TargetPitch")
m_targetRoll = table.getDoubleTopic("TargetRoll")
m_targetYaw = table.getDoubleTopic("TargetYaw")

# Set up the publishers
m_screenXPublisher = m_screenX.publish()
m_screenYPublisher = m_screenY.publish()

m_targetPitchPublisher = m_targetPitch.publish()
m_targetRollPublisher = m_targetRoll.publish()
m_targetYawPublisher = m_targetYaw.publish()

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

    imuPitch = 0.0
    imuRoll = 0.0
    imuYaw = 0.0

    # Publish the data
    m_screenXPublisher.setDouble(screenX)
    m_screenYPublisher.setDouble(screenY)

    m_targetPitchPublisher.setDouble(targetPitch)
    m_targetRollPublisher.setDouble(targetRoll)
    m_targetYawPublisher.setDouble(targetYaw)