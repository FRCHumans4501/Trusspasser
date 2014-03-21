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
public class LifterPosition {
    final int value;
    
    static final int VALUE_UP = 0;
    static final int VALUE_MIDDLE = 1;
    static final int VALUE_DOWN = 2;
    
    public static final LifterPosition UP = new LifterPosition(VALUE_UP);
    public static final LifterPosition MIDDLE = new LifterPosition(VALUE_MIDDLE);
    public static final LifterPosition DOWN = new LifterPosition(VALUE_DOWN);
    
    public LifterPosition(int value) {
        this.value = value;
    }
}
