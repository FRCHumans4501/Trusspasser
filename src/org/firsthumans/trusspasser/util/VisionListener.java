/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.firsthumans.trusspasser.util;

/**
 *
 * @author Humans
 */
public interface VisionListener {
    
    public void onVisionProcessed(boolean hot,
            float distance,
            int vertCenterX,
            int vertCenterY,
            int horizCenterX,
            int horizCenterY);
    
}
