/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.firsthumans.trusspasser;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.firsthumans.trusspasser.components.AutonomousComponent;
import org.firsthumans.trusspasser.components.DriveComponent;
import org.firsthumans.trusspasser.components.PneumaticComponent;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.firsthumans.trusspasser.systems.Catapult;
import org.firsthumans.trusspasser.systems.DriveTrain;

public class RobotMain extends IterativeRobot {
    
    Controller controller;
    
    DriveTrain driveTrain;
    Catapult catapult;
    Relay lifter;
    DoubleSolenoid lifterPiston;
    
    AutonomousComponent autonomous;
    DriveComponent drive;
    PneumaticComponent pneumatics;
    
    boolean delayed = false;
    boolean autonomousOn = false;
    boolean teleopOn = false;
    
    public void robotInit() {
        this.controller = new Controller(1);
        
        this.driveTrain = new DriveTrain();
        this.catapult = new Catapult();
        this.lifter = new Relay(1);
        this.lifterPiston = new DoubleSolenoid(3, 4);
        
        this.autonomous = new AutonomousComponent(this.driveTrain, this.catapult, this.lifter, this.lifterPiston);
        this.drive = new DriveComponent(this.controller, this.driveTrain, this.lifter);
        this.pneumatics = new PneumaticComponent(this.controller, this.catapult, this.lifterPiston);
    }
    
    public void autonomousInit() {
        this.autonomous.autonomousComplete = false;
        this.autonomous.ticksElapsed = 0;
        this.pneumatics.startCompressor();
        this.driveTrain.resetGyroAndPID();
        this.autonomousOn = true;
       
    }

    public void autonomousPeriodic() {
        this.autonomous.update();
        this.updateDriverStation();
    }
    
    public void teleopInit() {
        this.pneumatics.startCompressor();
        this.driveTrain.resetGyroAndPID();
        this.teleopOn = true;
    }
    
    public void teleopPeriodic() {
        this.drive.update();
        this.pneumatics.update();
        this.updateDriverStation();
    }
    
    public void testPeriodic() {
    
    }
    
    public void disabledInit() {
        this.autonomousOn = false;
        this.teleopOn = false;
        this.pneumatics.stopCompressor();
    }
    
    public static void log(String x) {
        if (RobotSettings.DEBUG_MODE) {
            System.out.println(x);
        }
    }
    
    public void updateDriverStation() {
        SmartDashboard.putNumber("Forward Drive", this.controller.getRawAxis(RobotSettings.CONTROL_FORWARD));
        SmartDashboard.putNumber("Strafe Drive", this.controller.getRawAxis(RobotSettings.CONTROL_STRAFE));
        SmartDashboard.putNumber("Rotation Control", this.controller.getRawAxis(RobotSettings.CONTROL_ROTATE));
    }
    
}