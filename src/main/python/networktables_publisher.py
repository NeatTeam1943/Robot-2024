from shooter_calculator import ProjectileSolver

import ntcore

CLIENT = "Test"
SERVER = 1943

DELTA_Y = 2.0445

"""
    1.984M LOW 
    2.1M HIGH
    2.0445M Average Height 
"""

inst = ntcore.NetworkTableInstance.getDefault()
table = inst.getTable("RealSense Data")

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

# DeltaX Subscriber
deltaX_subscriber = deltaX_topic.subscribe(-1)

inst.startClient4(CLIENT)
inst.setServer(SERVER)
inst.startDSClient()

calculator = ProjectileSolver()

calculator.set_initial_guess(v0_guess=10, theta_guess=55)

while True:
    screenX = deltaX_subscriber.getAsDouble()

    calculator.update_deltas(delta_x=screenX, delta_y=DELTA_Y)

    optimal_velocity, shooter_angle = calculator.solve()

    optimal_velocity_publisher.setDouble(optimal_velocity)
    shooter_angle_publisher.setDouble(shooter_angle)
