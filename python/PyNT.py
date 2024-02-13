import ntcore

CLIENT = "Test"
SERVER = 1943

INST = ntcore.NetworkTableInstance.getDefault()
TABLE = INST.getTable("RealSense Data")

# Set up the topic
screenX = TABLE.getDoubleTopic("ScreenX")
screenY = TABLE.getDoubleTopic("ScreenY")

target_pitch = TABLE.getDoubleTopic("TargetPitch")
target_roll = TABLE.getDoubleTopic("TargetRoll")
target_yaw = TABLE.getDoubleTopic("TargetYaw")

optimal_velocity = TABLE.getDoubleTopic("Optimal Shooter Velocity")
shooter_angle = TABLE.getDoubleTopic("Optimal Shooter Angle")
deltaX = TABLE.getDoubleTopic("DeltaX")

# Set up the publishers
screenX_publisher = screenX.publish()
screenY_publisher = screenY.publish()

target_pitch_publisher = target_pitch.publish()
target_roll_publisher = target_roll.publish()
target_yaw_publisher = target_yaw.publish()

optimal_velocity_publisher = optimal_velocity.publish()
shooter_angle_publisher = shooter_angle.publish()
deltaX_publisher = deltaX.publish()

INST.startClient4(CLIENT)
INST.setServer(SERVER) # where TEAM=190, 294, etc, or use inst.setServer("hostname") or similar
INST.startDSClient() # recommended if running on DS computer; this gets the robot IP from the DS

# Main loop
while True:
    # Get the data(Change to values gotten by the RealSense Camera)
    screenX = 0.0
    screenY = 0.0

    target_pitch = 0.0
    target_roll = 0.0
    target_yaw = 0.0

    optimal_velocity = 0.0
    shooter_angle = 0.0
    deltaX = 0.0

    # Publish the data
    screenX_publisher.setDouble(screenX)
    screenY_publisher.setDouble(screenY)

    target_pitch_publisher.setDouble(target_pitch)
    target_roll_publisher.setDouble(target_roll)
    target_roll_publisher.setDouble(target_yaw)

    optimal_velocity_publisher.setDouble(optimal_velocity)
    shooter_angle_publisher.setDouble(shooter_angle)
    deltaX_publisher.setDouble(deltaX)
