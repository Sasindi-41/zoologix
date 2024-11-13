/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shadex.zoologix.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

/**
 *
 * @author AT
 */
public class RoundedPanel extends JButton {
    public RoundedPanel(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:100");
    }
    
}
