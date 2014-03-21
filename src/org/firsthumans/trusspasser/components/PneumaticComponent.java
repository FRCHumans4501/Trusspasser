/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.firsthumans.trusspasser.components;

import org.firsthumans.trusspasser.Controller;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.firsthumans.trusspasser.RobotSettings;
import org.firsthumans.trusspasser.systems.Catapult;

/**
 *
 * @author Humans
 */
public class PneumaticComponent {

    Controller controller;
    
    DoubleSolenoid lifter;
    Catapult catapult;
    Compressor compressor;
    
    public PneumaticComponent(Controller controller, Catapult catapult, DoubleSolenoid lifterPiston) {
        this.controller = controller;
        
        this.catapult = catapult;
        
        this.compressor = new Compressor(1, 8);
        //this.compressor.start();
        
        this.lifter = lifterPiston;
    }
    
    public void startCompressor() {
        this.compressor.start();
    }
    
    public void stopCompressor() {
        this.compressor.stop();
    }
    
    public void update() {
        if (this.controller.getRawButton(RobotSettings.CONTROL_SHOOT)) {
            this.catapult.longShot();
        } else if (this.controller.getRawButton(RobotSettings.CONTROL_SHOOT_SHORT)) {
            this.catapult.shortShot();
        }
        
        if (this.controller.getRawButton(RobotSettings.CONTROL_LIFTER_UP)) {
            this.lifter.set(DoubleSolenoid.Value.kForward);
        } else if (this.controller.getRawButton(RobotSettings.CONTROL_LIFTER_DOWN)) {
            this.lifter.set(DoubleSolenoid.Value.kReverse);
        }
        
        this.catapult.update();
    }
    
    private void toggleSolenoid(DoubleSolenoid noid) {
        DoubleSolenoid.Value value = noid.get();
        if (value == DoubleSolenoid.Value.kOff
                || value == DoubleSolenoid.Value.kForward) {
            value = DoubleSolenoid.Value.kReverse;
        } else {
            value = DoubleSolenoid.Value.kForward;
        }
        noid.set(value);
    }
}