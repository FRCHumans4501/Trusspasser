/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package org.firsthumans.trusspasser.systems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.firsthumans.trusspasser.RobotMain;
import org.firsthumans.trusspasser.util.VisionListener;
import org.firsthumans.trusspasser.util.VisionProcessing;

/**
*
* @author Humans
*/
public class Vision implements VisionListener {
    
    VisionProcessing vision;
    
    boolean goalDetected = false;
    boolean hotGoalDetected = false;
    float latestDistance = 0;
    
    public Vision() {
        this.vision = new VisionProcessing();
        this.vision.setTargetListener(this);
    }
    
    public void update() {
        this.vision.update();
    }
    
    public float getLatestDistance() {
        return this.latestDistance;
    }
    
    public boolean hotGoalDetected() {
        return this.hotGoalDetected;
    }

    public void onVisionProcessed(boolean hot, float distance, int vertCenterX, int vertCenterY, int horizCenterX, int horizCenterY) {
        this.hotGoalDetected = hot;
        this.latestDistance = distance;
        
        if (hot)
            RobotMain.log("Hot");
        else
            RobotMain.log("Not");
        
        if (vertCenterX - horizCenterX > 0)
            RobotMain.log("Left Target");
        else
            RobotMain.log("Right Target");
        
        SmartDashboard.putNumber("Distance to Target", distance);
    }
}