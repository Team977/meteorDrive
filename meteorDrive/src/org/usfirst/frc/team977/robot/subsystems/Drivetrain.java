package org.usfirst.frc.team977.robot.subsystems;


import org.usfirst.frc.team977.robot.RobotMap;
import org.usfirst.frc.team977.robot.commands.Drive;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private SpeedController frontRightDrive, frontRightTurn, frontLeftDrive, frontLeftTurn;
	private SpeedController backLeftDrive, backLeftTurn, backRightDrive, backRightTurn;
	public AnalogInput frontRightEncoder, frontLeftEncoder, backLeftEncoder, backRightEncoder;
	
	public Drivetrain(){
		super();
		frontRightDrive = new VictorSP(RobotMap.FrontRightDrivePWM);
		frontRightTurn = new VictorSP(RobotMap.FrontRightTurnPWM);
		frontLeftDrive = new VictorSP(RobotMap.FrontLeftDrivePWM);
		frontLeftTurn = new VictorSP(RobotMap.FrontLeftTurnPWM);
		backLeftDrive = new VictorSP(RobotMap.BackLeftDrivePWM);
		backLeftTurn = new VictorSP(RobotMap.BackLeftTurnPWM);
		backRightDrive = new VictorSP(RobotMap.BackRightDrivePWM);
		backRightTurn = new VictorSP(RobotMap.BackRightTurnPWM);
		
		frontRightEncoder = new AnalogInput(RobotMap.FrontRightEncoderAI);
		frontLeftEncoder = new AnalogInput(RobotMap.FrontLeftEncoderAI);
		backRightEncoder = new AnalogInput(RobotMap.BackRightEncoderAI);
		backLeftEncoder = new AnalogInput(RobotMap.BackLeftEncoderAI);
		
		
		
	}
	
	public void drive(double[] speed){
		frontRightDrive.set(speed[0]*RobotMap.SpeedModifier);
		frontLeftDrive.set(speed[1]*RobotMap.SpeedModifier);
		backRightDrive.set(speed[2]*RobotMap.SpeedModifier);
		backLeftDrive.set(speed[3]*RobotMap.SpeedModifier);
	}
	
	public void drive(double speed){
		frontRightDrive.set(speed*RobotMap.SpeedModifier);
		frontLeftDrive.set(speed*RobotMap.SpeedModifier);
		backRightDrive.set(speed*RobotMap.SpeedModifier);
		backLeftDrive.set(speed*RobotMap.SpeedModifier);
	}
	
	public void turn(double[] speed){
		frontRightTurn.set(speed[0]);
		frontLeftTurn.set(speed[1]);
		backRightTurn.set(speed[2]);
		backLeftTurn.set(speed[3]);
	}
	
	
	
	public double[] reportEncoders(){
		/*double[] encoders = {Math.round(((frontRightEncoder.getVoltage()+RobotMap.FrontRightEncoderZero))*Math.PI*200d)/500d+Math.PI,
		Math.round(((frontLeftEncoder.getVoltage()+RobotMap.FrontLeftEncoderZero))*Math.PI*200d)/500d+Math.PI,
		Math.round(((backRightEncoder.getVoltage()+RobotMap.BackRightEncoderZero))*Math.PI*200d)/500d+Math.PI,
		Math.round(((backLeftEncoder.getVoltage()+RobotMap.BackLeftEncoderZero))*Math.PI*200d)/500d+Math.PI};
		*/
		
		double[] encoders = {Math.round(frontRightEncoder.getVoltage()*Math.PI*2)/4.95,
				Math.round(frontLeftEncoder.getVoltage()*Math.PI*2)/4.95,
				Math.round(backRightEncoder.getVoltage()*Math.PI*2)/4.95,
				Math.round(backLeftEncoder.getVoltage()*Math.PI*2)/4.95};
				
		SmartDashboard.putNumber("FrontRightEncoder", frontRightEncoder.getVoltage());
		SmartDashboard.putNumber("FrontLeftEncoder", frontLeftEncoder.getVoltage());
		SmartDashboard.putNumber("BackRightEncoder", backRightEncoder.getVoltage());
		SmartDashboard.putNumber("BackLeftEncoder", backLeftEncoder.getVoltage());
		/*
		SmartDashboard.putNumber("FrontRightEncoderADJ", encoders[0] );
		SmartDashboard.putNumber("FrontLeftEncoderADJ", encoders[1]);
		SmartDashboard.putNumber("BackRightEncoderADJ", encoders[2]);
		SmartDashboard.putNumber("BackLeftEncoderADJ", encoders[3]);
		*/
		return encoders;
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Drive());
    }
}

