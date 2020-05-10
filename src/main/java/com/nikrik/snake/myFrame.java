/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikrik.snake;

import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author Nikrik
 */
public class myFrame extends JFrame
{
    public myFrame()
    {
        // Создание объекта панели и подключения ее к окну
        myPanel pan = new myPanel();
        
        Container cont = getContentPane();
        cont.add(pan);
        
        setTitle("Игра \"Змейка\"");
        setBounds(500, 200, 800, 650);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(3);
    }
}
