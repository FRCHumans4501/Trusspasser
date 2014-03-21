/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.firsthumans.trusspasser.components;

import edu.wpi.first.wpilibj.Relay;
import org.firsthumans.trusspasser.Controller;
import org.firsthumans.trusspasser.RobotSettings;

import org.firsthumans.trusspasser.systems.DriveTrain;

/**
 *
 * @author Humans
 */
public class DriveComponent {
    
    Controller controller;
    DriveTrain driveTrain;
    
    Relay lifter;
    
    public DriveComponent(Controller controller, DriveTrain driveTrain, Relay lifter) {
        this.controller = controller;
        this.driveTrain = driveTrain;
        this.lifter = lifter;
    }
    
    public void update() {
        float[] fixedCoords = deadzoneControl();
        float x = fixedCoords[0];
        float y = fixedCoords[1];
        float rotate = this.controller.getRawAxis(RobotSettings.CONTROL_ROTATE);
        
        this.driveTrain.updateMode(rotate);
        this.driveTrain.drive(x, y, rotate);
        
        if (this.controller.getRawButton(RobotSettings.CONTROL_LIFTER_FORWARD)) {
            this.lifter.set(Relay.Value.kForward);
        } else if (this.controller.getRawButton(RobotSettings.CONTROL_LIFTER_BACKWARD)) {
            this.lifter.set(Relay.Value.kReverse);
        } else {
            this.lifter.set(Relay.Value.kOff);
        }
    }
    
    private float[] deadzoneControl() {
        float x = this.controller.getRawAxis(RobotSettings.CONTROL_STRAFE);
        float y = this.controller.getRawAxis(RobotSettings.CONTROL_FORWARD);
        
        if (RobotSettings.DEADZONE == 0.0f) {
            float[] ret = new float[2];
            ret[0] = x;
            ret[1] = y;
            return ret;
        } else {
            float a = (float)Math.sqrt((x*x) + (y*y));
            float b = (RobotSettings.DEADZONE - a) / (1 - RobotSettings.DEADZONE);
            float xx = (x / a) * b;
            float yy = (y / a) * b;
            if (a > RobotSettings.DEADZONE) {
                float[] ret = new float[2];
                ret[0] = xx;
                ret[1] = yy;
                return ret;
            } else {
                float[] ret = new float[2];
                ret[0] = 0;
                ret[1] = 0;
                return ret;
            }
        }
    }
}