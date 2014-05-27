/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.firsthumans.trusspasser;

/**
 *
 * @author Humans
 */
public class RobotSettings {
    
    public final static boolean DEBUG_MODE = true;
    
    public final static float DEADZONE = 0.2f;
    public final static int MODE_CHANGE_DELAY = 25;
    public static boolean PID = true;
    
    public final static float TIMER_PID_ANGLE_DELAY = 5.0f;
    public final static float TIMER_AUTO_DRIVE_DURATION = 3f; // 2.25f
    public final static float TIMER_AUTO_DRIVE_PAUSE = 5f; // 4.25f
    
    public final static int CONTROL_STRAFE = Controller.LEFT_AXIS_X;
    public final static int CONTROL_FORWARD = Controller.LEFT_AXIS_Y;
    public final static int CONTROL_ROTATE = Controller.TRIGGERS;
    
    public final static int CONTROL_SHOOT = Controller.BUTTON_A;
    public final static int CONTROL_SHOOT_SHORT = Controller.BUTTON_B;
    
    public final static int CONTROL_LIFTER_UP = Controller.BUMPER_L;
    public final static int CONTROL_LIFTER_DOWN = Controller.BUMPER_R;
    public final static int CONTROL_LIFTER_FORWARD = Controller.BUTTON_Y;
    public final static int CONTROL_LIFTER_BACKWARD = Controller.BUTTON_X;
    
}