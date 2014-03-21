/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.firsthumans.trusspasser;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Humans
 */
public class Controller {
    
    Joystick controller;
    
    public static final int LEFT_AXIS_X = 1;
    public static final int LEFT_AXIS_Y = 2;
    public static final int RIGHT_AXIS_X = 4;
    public static final int RIGHT_AXIS_Y = 5;
    
    public static final int TRIGGERS = 3;
    
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 4;
    public static final int BUMPER_L = 6;    
    public static final int BUMPER_R = 5;    
    
    int currentButton = 0;
    int previousButton = 0;
    
    public Controller(int port) {
        this.controller = new Joystick(port);        
    }
    
    public float getRawAxis(int axis) {
        return (float)this.controller.getRawAxis(axis);
    }
    
    public boolean getRawButton(int button) {
        return this.controller.getRawButton(button);
    }
    
}
