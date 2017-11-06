package org.usfirst.frc.team977.robot.commands;

import org.usfirst.frc.team977.robot.OI;
import org.usfirst.frc.team977.robot.Robot;
import org.usfirst.frc.team977.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Command {
	private double[] rawAxis;
	private double x;
	private double y;
	private double r;
	private double theta = 0;
	private double[] error1 = {0,0,0,0};
	private double[] error2 = {0,0,0,0};
	private double[] error3 = {0,0,0,0};
	private double[] error = {0,0,0,0};
	private double[] pOut = {0,0,0,0};
	private double[] iOut = {0,0,0,0};
	private double[] dOut = {0,0,0,0};
	private double[] output = {0,0,0,0};
	private double[] currentEncoderPos = {0,0,0,0};
	private double[] delta_error = {0,0,0,0};
	private double[] prevError = {0,0,0,0};
	private double[] sumError = {0,0,0,0};

	

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.RobotDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//get joystick
    	rawAxis = OI.getJoy();    
    	
    	x = rawAxis[0]*Math.sqrt(1-rawAxis[1]*rawAxis[1]/2);
    	y = rawAxis[1]*Math.sqrt(1-rawAxis[0]*rawAxis[0]/2);
    	
    	//convert to polar
    	r = Math.sqrt(x*x + y*y);
    	
    	//insert deadzone code here (don't update theta if r is close to zero)
    	if (r >= RobotMap.deadzone){
    		theta = Math.atan2(y,-x) + Math.PI;
    	}
    			
    	SmartDashboard.putNumber("r", r);
    	SmartDashboard.putNumber("theta", theta);
    			
    	//read encoder positions
    	currentEncoderPos = Robot.RobotDrive.reportEncoders();
    	  			
    	
    	for (int i=0; i<4; i++){
    		
    		//generate error arrays
    		error1[i] = theta - currentEncoderPos[i];
    		error2[i] = theta + 2*Math.PI - currentEncoderPos[i];
    		error3[i] = theta - 2*Math.PI - currentEncoderPos[i];
    		
  
    		if (Math.abs(error1[i]) < Math.abs(error2[i]) && Math.abs(error1[i]) < Math.abs(error3[i])){
    			error[i]=error1[i];
    		}
    		else if (Math.abs(error2[i]) < Math.abs(error1[i]) && Math.abs(error2[i]) < Math.abs(error3[i])){
    			error[i] = error2[i];
    		}
    		else {
    			error[i] = error3[i];
    		}
    		
    		//error[i] = Math.min(error1[i],error2[i]);
    		
    		delta_error[i] = prevError[i] - error[i];
    		sumError[i] += error[i];
    	  	
    		//generate pid array

    		pOut[i] = error[i] * RobotMap.KP;
    		iOut[i] = sumError[i] * RobotMap.KI;
    		dOut[i] = delta_error[i] * RobotMap.KD;
    	    	
    		//generate output array
    		output[i] = pOut[i] + iOut[i] + dOut[i];
    	
    		prevError[i] = error[i];
    		
    	}    	
    	
    	Robot.RobotDrive.turn(output);
    	Robot.RobotDrive.drive(r);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
