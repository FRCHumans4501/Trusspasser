/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.firsthumans.trusspasser.systems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.firsthumans.trusspasser.util.CatapultMode;

/**
 *
 * @author Humans
 */
public class Catapult {
    DoubleSolenoid noid1;
    CatapultMode mode;
    
    public static final int FULL_SHOT_DELAY = 50;
    public static final int SHORT_SHOT_DELAY = 5;
    int shotDelay = FULL_SHOT_DELAY;
    
    boolean shot = false;
    int ticksSinceShot = 0;
    
    public Catapult() {
        this.noid1 = new DoubleSolenoid(1, 2);
        this.mode = CatapultMode.OFF;
    }
    
    public void update() {
        if (this.shot == true) {
            this.ticksSinceShot++;
            this.mode = CatapultMode.REVERSE;
            if (this.ticksSinceShot == this.shotDelay) {
                this.shot = false;
                this.ticksSinceShot = 0;
            }
        } else {
            this.mode = CatapultMode.FORWARD;
        }
        
        if (this.mode == CatapultMode.FORWARD) {
            this.noid1.set(DoubleSolenoid.Value.kForward);
        } else if (this.mode == CatapultMode.REVERSE) {
            this.noid1.set(DoubleSolenoid.Value.kReverse);
        }
    }
    
    public void shortShot() {
        this.shotDelay = SHORT_SHOT_DELAY;
        this.shot = true;
    }
    
    public void longShot() {
        this.shotDelay = FULL_SHOT_DELAY;
        this.shot = true;
    }
}
