package org.usfirst.frc.team977.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//MOTORS
	
	public static final int FrontRightDrivePWM = 0;
	public static final int FrontRightTurnPWM = 1;
	public static final int FrontLeftDrivePWM = 2;
	public static final int FrontLeftTurnPWM = 3;
	public static final int BackLeftDrivePWM = 4;
	public static final int BackLeftTurnPWM = 5;
	public static final int BackRightDrivePWM = 6;
	public static final int BackRightTurnPWM = 7;
	
	//speed modifiers
	
	//ENCODERS
	
	public static final int FrontRightEncoderAI = 0;
	public static final int FrontLeftEncoderAI = 1;
	public static final int BackRightEncoderAI = 3;
	public static final int BackLeftEncoderAI = 2;
	
	//Encoder Zeroing
	public static final double SpeedModifier = .5;
	public static final double KP = .5;
	public static final double KI = 0.01;
	public static final double KD = 0;
	public static final double deadzone = 0.1;
	

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
