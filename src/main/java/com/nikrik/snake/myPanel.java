/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikrik.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Nikrik
 */
public class myPanel extends JPanel
{
    private game myGame;
    private Timer tmDraw;
    private JLabel lb,lb2;
    private JLabel seed;
    private myPanel pan;
    private JButton btn1,btn2;
    private int napr=0,buff=0;
    private class myKey implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e)
        {
            int key_ =e.getKeyCode();
            switch (key_)
            {
                case(0):
                    napr=0;
                case(37):
                    //x--;
                    //System.out.println("x--");
                    napr=1;
                    break;
                case(38):
                    napr=2;
                    //y--;
                    //System.out.println("y--");
                    break;
                case(39):
                    napr=3;
                    //x++;
                    //System.out.println("x++");
                    break;
                case(40):
                    napr=4;
                    //System.out.println("y++");
                    //y++;
                break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
    public myPanel()
    {
        pan=this;
        addKeyListener(new myKey());
        this.setFocusable(true);
        myGame=new game();
        myGame.start();
        tmDraw=new Timer(150,new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                myGame.tick(napr);
                lb.setText("Счёт: "+myGame.score());
                repaint();
            }
        });
        tmDraw.start();
        
        setLayout(null);
        
        lb=new JLabel("Счёт: 0");
        lb.setForeground(Color.white);
        lb.setBounds(630, 150, 150, 50);
        add(lb);
        
        lb2=new JLabel("Seed:");
        lb2.setForeground(Color.white);
        lb2.setBounds(630, 175, 150, 50);
        add(lb2);
        
        seed=new JLabel();
        seed.setText(Long.toString(myGame.seed));
        seed.setForeground(Color.white);
        seed.setBounds(630, 200, 150, 50);
        add(seed);
        
        btn1=new JButton();
        btn1.setText("Новая игра");
        btn1.setForeground(Color.BLUE);
        btn1.setFont(new Font("serif",0,20));
        btn1.setBounds(630, 30, 150, 50);
        btn1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                myGame.start();
                napr=0;
                seed.setText(Long.toString(myGame.seed));
                btn1.setFocusable(false);
                btn2.setFocusable(false);
                pan.setFocusable(true);
            }
        });
        add(btn1);
          
        btn2 = new JButton();
        btn2.setText("Выход");
        btn2.setForeground(Color.RED);
        btn2.setFont(new Font("serif",0,20));
        btn2.setBounds(630, 100, 150, 50);
        btn2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                System.exit(0);
            }
        });
        add(btn2);
    }
    @Override
    public void paintComponent(Graphics gr)
    {
        //super.paintComponent(gr);
        // Отрисовка фона
        gr.setColor(Color.black);
        gr.fillRect(0,0,800,650);
        // Отрисовка игрового поля на основании массива
        for (int i = 0; i < 30; i++)
        {
            for (int j = 0; j < 30; j++)
            {
                if (myGame.mas[i][j]>0)
                {
                    gr.setColor(Color.red);
                    gr.fillRect(10+j*20, 10+i*20,20,20);
                }
                else if (myGame.mas[i][j]==-1)
                {
                    gr.setColor(Color.green);
                    gr.fillRect(10+j*20, 10+i*20,20,20);
                }
            }
        }
        // Отрисовка сетки игрового поля из синих линий
        gr.setColor(Color.BLUE);
        for (int i = 0; i <= 30; i++)
        {
            gr.drawLine(10+i*20, 10, 10+i*20, 610);
            gr.drawLine(10, 10+i*20, 610, 10+i*20);
        }
        if (myGame.kon)
        {
            gr.fillRect(250,200,300,100);
            gr.setColor(Color.red);
            gr.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            gr.drawString("Конец игры", 270, 275);
        }
    }
}
