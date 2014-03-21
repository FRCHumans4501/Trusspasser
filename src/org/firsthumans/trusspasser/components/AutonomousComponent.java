/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.firsthumans.trusspasser.components;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import org.firsthumans.trusspasser.RobotMain;
import org.firsthumans.trusspasser.RobotSettings;
import org.firsthumans.trusspasser.systems.Catapult;
import org.firsthumans.trusspasser.systems.DriveTrain;

/**
 *
 * @author Humans
 */
public class AutonomousComponent {
    
    DriveTrain driveTrain;
    Catapult catapult;
    Relay lifter;
    DoubleSolenoid lifterPiston;
    Timer time;
    
    public int ticksElapsed = 0;
    public boolean autonomousComplete = false;
    
    public AutonomousComponent(DriveTrain driveTrain, Catapult catapult, Relay lifter, DoubleSolenoid lifterPiston) {
        this.driveTrain = driveTrain;
        this.catapult = catapult;
        this.lifter = lifter;
        this.lifterPiston = lifterPiston;
        
        this.ticksElapsed = 0;
        
        this.time = new Timer();
        this.time.start();
    }
    
    public void update() {
        
        RobotMain.log("Ticks Elapsed: " + this.ticksElapsed + " "
                + "Timer Time: " + this.time.get());
        
        if (this.ticksElapsed == 0) {
            this.time.reset();
        }
        
        this.lifterPiston.set(DoubleSolenoid.Value.kReverse);
        
        if (this.time.get() < RobotSettings.TIMER_AUTO_DRIVE_DURATION) {
            this.driveTrain.drive(0, 0.333f, 0);
        } else if (this.time.get() < RobotSettings.TIMER_AUTO_DRIVE_PAUSE) {
            this.driveTrain.drive(0, 0, 0);
        } else {
            this.driveTrain.drive(0, 0, 0);
            if (!this.autonomousComplete) {
                //this.catapult.longShot();
                this.autonomousComplete = true;
            }
            this.catapult.update();
        }
        
        this.ticksElapsed += 1;
    }
    
}
