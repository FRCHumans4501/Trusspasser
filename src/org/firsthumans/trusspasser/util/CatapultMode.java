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
public class CatapultMode {
    private final int value;
    
    private static final int OFF_V = 0;
    private static final int REVERSE_V = 1;
    private static final int FORWARD_V = 2;
    
    public static final CatapultMode OFF = new CatapultMode(OFF_V);
    public static final CatapultMode REVERSE = new CatapultMode(REVERSE_V);
    public static final CatapultMode FORWARD = new CatapultMode(FORWARD_V);
    
    public CatapultMode(int value) {
        this.value = value;
    }
}
