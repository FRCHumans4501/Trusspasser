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
public class DriveMode {
    private final int value;
    
    private static final int STRAIGHT_V = 0;
    private static final int ROTATE_V = 1;
    
    public static final DriveMode STRAIGHT = new DriveMode(STRAIGHT_V);
    public static final DriveMode ROTATING = new DriveMode(ROTATE_V);
    
    public DriveMode(int value) {
        this.value = value;
    }
}