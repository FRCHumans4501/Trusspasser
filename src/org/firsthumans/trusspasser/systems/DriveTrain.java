/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.firsthumans.trusspasser.systems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.firsthumans.trusspasser.RobotSettings;
import org.firsthumans.trusspasser.util.DriveMode;

/**
 *
 * @author Humans
 */
public class DriveTrain {
    
    RobotDrive drive;
    DriveMode driveMode;
    int ticks;
    
    Gyro gyro;
    
    PIDController straightDrivingPID;
    GyroCorrectionPIDSource straightDrivingPIDSource;
    GyroCorrectionPIDOutput straightDrivingPIDOutput;
    
    Timer time;
    
    public DriveTrain() {
        // new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
        this.drive = new RobotDrive(1, 2, 3, 4);
        this.drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        this.drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
        this.drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        this.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
        this.driveMode = DriveMode.STRAIGHT;
        
        this.gyro = new Gyro(1);
        this.gyro.setSensitivity(0.0069);
        //this.gyro.setSensitivity(0.007039);  // error < 0.10%
        
        this.time = new Timer();
        this.time.start();
        
        this.straightDrivingPIDSource = new GyroCorrectionPIDSource(this.gyro, this.time);
        this.straightDrivingPIDOutput = new GyroCorrectionPIDOutput();
        this.straightDrivingPID = new PIDController(0.005, 0, 0, this.straightDrivingPIDSource, this.straightDrivingPIDOutput);
        this.straightDrivingPID.setPercentTolerance(5);
        this.straightDrivingPID.setContinuous(true);
        this.straightDrivingPID.enable();
        
        this.straightDrivingPID.setSetpoint(0);
    }
    
    public DriveMode getDriveMode() {
        return this.driveMode;
    }
    
    public void updateMode(float rotate) {
        if (rotate != 0) {
            this.driveMode = DriveMode.ROTATING; 
            this.ticks = 0;
        } else {
            if (this.ticks < RobotSettings.MODE_CHANGE_DELAY) {
                this.ticks += 1;
            } else if (this.ticks >= RobotSettings.MODE_CHANGE_DELAY) {
                this.driveMode = DriveMode.STRAIGHT;
            }
        }
        
        SmartDashboard.putNumber("PID Value", this.straightDrivingPIDOutput.getValue());
        SmartDashboard.putNumber("PID Setpoint", this.straightDrivingPID.getSetpoint());
        SmartDashboard.putNumber("Gyro Angle", this.gyro.getAngle());
    }
    
    public void drive(float x, float y, float rotate) {
        if (this.driveMode == DriveMode.STRAIGHT) {
            rotate = RobotSettings.PID ? -this.straightDrivingPIDOutput.getValue() : 0;
        } else if (this.driveMode == DriveMode.ROTATING) {
            this.straightDrivingPID.setSetpoint(this.gyro.getAngle());
        }
        
        this.drive.mecanumDrive_Cartesian(x, y, rotate, 0);
    }
    
    public void resetGyroAndPID() {
        this.gyro.reset();
        this.time.reset();
        this.straightDrivingPID.setSetpoint(0);
    }
}

class GyroCorrectionPIDSource implements PIDSource {
    Gyro gyro;
    Timer time;
    
    public GyroCorrectionPIDSource(Gyro gyro, Timer time) {
        this.gyro = gyro;
        this.time = time;
    }

    public double pidGet() {
        double value = 0;
        if (this.time.get() > 1) {
            SmartDashboard.putBoolean("Sending Gyro to PID", true);
            value = this.gyro.getAngle();
        } else {
            SmartDashboard.putBoolean("Sending Gyro to PID", false);
        }
        //RobotMain.log("PID Get Time: "+this.time.get()
        //        + " || Gyro Angle (PID): " + value
        //        + " || Gyro Angle (Actual): " + this.gyro.getAngle());
        SmartDashboard.putNumber("Gyro Angle sent to PID", value);
        return value;
    }
}

class GyroCorrectionPIDOutput implements PIDOutput {
    private float value;
    
    public GyroCorrectionPIDOutput() {
        this.value = 0;
    }
    
    public float getValue() {
        return this.value;
    }

    public void pidWrite(double output) {
        //System.out.println("pidWrite Output:" + output);
        this.value = (float)output;
    }
}