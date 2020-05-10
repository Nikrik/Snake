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
    private JLabel lb,lb2,seed;
    private myPanel pan;
    private Color line=new Color(0,0,255),fon=new Color(0,0,0);
    public int sloj;//сложность
    private JButton btn1,btn2,settings,pause;
    private boolean ispause=true;
    private int napr=0,buff=0;
    private class myKey implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e)
        {
            int key_ =e.getKeyCode();
            System.out.println(key_);
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
    private Color reverseColor(Color a)
    {
        return new Color(255-a.getRed(),255-a.getGreen(),255-a.getBlue());
    }
    public myPanel()
    {
        pan=this;
        addKeyListener(new myKey());
        this.setFocusable(true);
        myGame=new game(50);
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
        
        lb=new JLabel();
        lb.setForeground(reverseColor(fon));
        lb.setBounds(1100, 350, 150, 50);
        add(lb);
        
        lb2=new JLabel("Seed:");
        lb2.setForeground(reverseColor(fon));
        lb2.setBounds(1100, 375, 150, 50);
        add(lb2);
        
        seed=new JLabel();
        seed.setText(Long.toString(myGame.seed));
        seed.setForeground(reverseColor(fon));
        seed.setBounds(1100, 400, 150, 50);
        add(seed);
        
        btn1=new JButton();
        btn1.setText("Новая игра");
        btn1.setForeground(Color.BLUE);
        btn1.setFont(new Font("serif",0,20));
        btn1.setBounds(1100, 30, 150, 50);
        btn1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                myGame.start();
                napr=0;
                ispause=true;
                tmDraw.start();
                setfocus();
            }
        });
        add(btn1);
          
        pause = new JButton();
        pause.setText("Пауза");
        pause.setForeground(Color.BLUE);
        pause.setFont(new Font("serif",0,20));
        pause.setBounds(1100, 100, 150, 50);
        pause.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if (ispause)
                {
                    tmDraw.stop();
                }
                else
                {
                    setfocus();
                    tmDraw.start();
                }
                ispause=!ispause;
                repaint();
            }
        });
        add(pause);
        
        settings = new JButton();
        settings.setText("Настройки");
        settings.setForeground(Color.BLUE);
        settings.setFont(new Font("serif",0,20));
        settings.setBounds(1100, 170, 150, 50);
        settings.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //System.exit(0);
            }
        });
        add(settings);
        
                
        btn2 = new JButton();
        btn2.setText("Выход");
        btn2.setForeground(Color.RED);
        btn2.setFont(new Font("serif",0,20));
        btn2.setBounds(1100, 240, 150, 50);
        btn2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                System.exit(0);
            }
        });
        add(btn2);
    }
    private void setfocus()
    {
        btn1.setFocusable(false);
                    btn2.setFocusable(false);
                    pause.setFocusable(false);
                    settings.setFocusable(false);
                    pan.setFocusable(true);
    }
    @Override
    public void paintComponent(Graphics gr)
    {
        //super.paintComponent(gr);
        // Отрисовка фона
        gr.setColor(fon);
        gr.fillRect(0,0,1280, 1000);
        // Отрисовка игрового поля на основании массива
        for (int i = 0; i < myGame.razm; i++)
        {
            for (int j = 0; j < myGame.razm; j++)
            {
                if (myGame.mas[i][j]>0)
                {
                    gr.setColor(Color.red);
                    gr.fillRect(j*20, i*20,20,20);
                }
                else if (myGame.mas[i][j]==-1)
                {
                    gr.setColor(Color.green);
                    gr.fillRect(j*20, i*20,20,20);
                }
            }
        }
        // Отрисовка сетки игрового поля из синих линий
        gr.setColor(line);
        for (int i = 0; i <= myGame.razm; i++)
        {
            gr.drawLine(i*20, 0, i*20, myGame.razm*20);
            gr.drawLine(0, i*20, myGame.razm*20, i*20);
        }
        if (!ispause)
        {
            gr.setColor(fon);
            gr.fillRect(250,200,300,100);
            gr.setColor(reverseColor(fon));
            gr.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            gr.drawString("Пауза", 270, 275);
        }
        if (myGame.kon)
        {
            gr.setColor(fon);
            gr.fillRect(250,200,300,100);
            gr.setColor(reverseColor(fon));
            gr.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            gr.drawString("Конец игры", 270, 275);
        }
    }
}
