import ntcore

CLIENT = "Test"
SERVER = 1943

INST = ntcore.NetworkTableInstance.getDefault()
TABLE = INST.getTable("RealSense Data")

# Set up the topic
screenX_topic = table.getDoubleTopic("ScreenX")
screenY_topic = table.getDoubleTopic("ScreenY")

target_pitch_topic = table.getDoubleTopic("TargetPitch")
target_roll_topic = table.getDoubleTopic("TargetRoll")
target_yaw_topic = table.getDoubleTopic("TargetYaw")

optimal_velocity_topic = table.getDoubleTopic("Optimal Shooter Velocity")
shooter_angle_topic = table.getDoubleTopic("Optimal Shooter Angle")
deltaX_topic = table.getDoubleTopic("DeltaX")

# Set up the publishers
screenX_publisher = screenX_topic.publish()
screenY_publisher = screenY_topic.publish()

target_pitch_publisher = target_pitch_topic.publish()
target_roll_publisher = target_roll_topic.publish()
target_yaw_publisher = target_yaw_topic.publish()

optimal_velocity_publisher = optimal_velocity_topic.publish()
shooter_angle_publisher = shooter_angle_topic.publish()
deltaX_publisher = deltaX_topic.publish()

INST.startClient4(CLIENT)
INST.setServer(SERVER)
INST.startDSClient()

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
