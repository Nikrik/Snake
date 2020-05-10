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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

/**
 *
 * @author Nikrik
 */
public class myPanel extends JPanel
{
    private game myGame;
    private JComboBox lnColor,bgColor,apColor,snColor,papColor;
    private JSlider slider;
    private Timer tmDraw;
    private JLabel lb,lb2,seed;
    private myPanel pan;
    private Color line,fon,apple,snake,poapple;
    public int sloj;//сложность
    private JButton btn1,btn2,settings,pause;
    private boolean isplay=true,issettings=false;
    private int napr=0;
    private void PlayGame()
    {
        if (issettings)
        {
            return;
        }
        if (isplay)
        {
            tmDraw.stop();
            System.out.println("Game is stopped");
        }
        else
        {
            setfocus();
            tmDraw.start();
            System.out.println("Game is resumed");
        }
        isplay=!isplay;
        repaint();
    }
    private void PlayGame(boolean is)
    {
        if (!is)
        {
            tmDraw.stop();
            System.out.println("Game is stopped");
        }
        else
        {
            if (issettings)
            {
                return;
            }
            setfocus();
            System.out.println("Game is resumed");
            tmDraw.start();
        }
        isplay=is;
        repaint();
    }
    private Color converColor(int i)
    {
        switch(i)
        {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.WHITE;
            case 2:
                return Color.RED;
            case 3:
                return Color.ORANGE;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.GREEN;
            case 6:
                return Color.CYAN;
            case 7:
                return Color.BLUE;
            case 8:
                return Color.MAGENTA;
        };
        return new Color(0,0,0,0);
    }
    private void setColor()
    {
        
        line=converColor(lnColor.getSelectedIndex());
        fon=converColor(bgColor.getSelectedIndex());
        apple=converColor(apColor.getSelectedIndex());
        snake=converColor(snColor.getSelectedIndex());
        poapple=converColor(papColor.getSelectedIndex());
    }
    
    private void changeSettings()
    {
        
        issettings=!issettings;
        if (issettings)
        {
            System.out.println("Opened settings, stopping game...");
            PlayGame(false);
            bgColor.setVisible(true);
            lnColor.setVisible(true);
            snColor.setVisible(true);
            apColor.setVisible(true);
            papColor.setVisible(true);
            slider.setVisible(true);
        }
        else
        {
            System.out.println("closed settings");
            slider.setVisible(false);
            bgColor.setVisible(false);
            lnColor.setVisible(false);
            snColor.setVisible(false);
            apColor.setVisible(false);
            papColor.setVisible(false);
            setColor();
            lb.setForeground(reverseColor(fon));
            lb2.setForeground(reverseColor(fon));
            seed.setForeground(reverseColor(fon));
            repaint();
        }
    }
    private class myKey implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e)
        {
            int key_ =e.getKeyCode();
            //System.out.println(key_);
            switch (key_)
            {    
                case(37):
                    napr=1;
                    System.out.println("left");
                    break;
                case(38):
                    napr=2;
                    System.out.println("up");
                    break;
                case(39):
                    napr=3;
                    System.out.println("right");
                    break;
                case(40):
                    napr=4;
                    System.out.println("down");
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
        
        
        myGame=new game(50);
        myGame.start();
        tmDraw=new Timer(16, (ActionEvent e) ->
        {
            sloj+=1;
            if (1000/slider.getValue()<sloj*16)
            {
                myGame.tick(napr);
                lb.setText("Счёт: "+myGame.score());
                repaint();
                System.out.println(sloj*16+" ms, "+1000/(sloj*16)+" HZ");
                sloj=0;
            }
        });
        tmDraw.start();
        
        setLayout(null);
        
        lb=new JLabel();
        
        lb.setBounds(1100, 350, 150, 50);
        add(lb);
        
        lb2=new JLabel("Seed:");
        
        lb2.setBounds(1100, 375, 150, 50);
        add(lb2);
        
        seed=new JLabel();
        seed.setText(Long.toString(myGame.seed));
        
        seed.setBounds(1100, 400, 150, 50);
        add(seed);
        
        btn1=new JButton();
        btn1.setText("Новая игра");
        btn1.setForeground(Color.BLUE);
        btn1.setFont(new Font("serif",0,20));
        btn1.setBounds(1050, 30, 200, 50);
        btn1.addActionListener((ActionEvent arg0) -> {
            myGame.start();
            napr=0;
            if (issettings)
            {
                changeSettings();
            }
            PlayGame(true);
        });
        add(btn1);
          
        pause = new JButton();
        pause.setText("Пауза");
        pause.setForeground(Color.BLUE);
        pause.setFont(new Font("serif",0,20));
        pause.setBounds(1050, 100, 200, 50);
        pause.addActionListener((ActionEvent arg0) -> {
            PlayGame();
        });
        add(pause);
        
        settings = new JButton();
        settings.setText("Настройки");
        settings.setForeground(Color.BLUE);
        settings.setFont(new Font("serif",0,20));
        settings.setBounds(1050, 170, 200, 50);
        settings.addActionListener((ActionEvent arg0) -> {
            changeSettings();
        });
        add(settings);
        
        slider=new JSlider(1,60);
        //slider.setInverted(true);
        slider.setBounds(50,250,800,25);
        add(slider);
        slider.setVisible(false);
        
        String[] items =
        {
            "Чёрный",//0
            "Белый",//1
            "Красный",//2
            "Оранжевый",//3
            "Жёлтый",//4
            "Зелёный",//5
            "Циан",//6
            "Синий",//7
            "Маджента",//8
            "Прозрачный"
        };
        
        bgColor=new JComboBox(items);
        bgColor.setBounds(0, 100, 150, 25);
        bgColor.setSelectedIndex(0);
        add(bgColor);
        bgColor.setVisible(false);
        
        lnColor=new JComboBox(items);
        lnColor.setBounds(200, 100, 150, 25);
        lnColor.setSelectedIndex(5);
        add(lnColor);
        lnColor.setVisible(false);
        
        snColor=new JComboBox(items);
        snColor.setBounds(400, 100, 150, 25);
        snColor.setSelectedIndex(7);
        add(snColor);
        snColor.setVisible(false);
        
        apColor=new JComboBox(items);
        apColor.setBounds(600,100,150,25);
        apColor.setSelectedIndex(2);
        add(apColor);
        apColor.setVisible(false);
        
        papColor=new JComboBox(items);
        papColor.setBounds(800,100,150,25);
        papColor.setSelectedIndex(5);
        add(papColor);
        papColor.setVisible(false);
        setColor();
        lb.setForeground(reverseColor(fon));
        lb2.setForeground(reverseColor(fon));
        seed.setForeground(reverseColor(fon));
        //private JSlider slider;
        
        btn2 = new JButton();
        btn2.setText("Выход");
        btn2.setForeground(Color.RED);
        btn2.setFont(new Font("serif",0,20));
        btn2.setBounds(1050, 240, 200, 50);
        btn2.addActionListener((ActionEvent arg0) -> {
            System.exit(0);
        });
        add(btn2);
        setfocus();
    }
    private void setfocus()
    {
        pan.setFocusable(true);
        
        btn1.setFocusable(false);
        btn2.setFocusable(false);
        pause.setFocusable(false);
        settings.setFocusable(false);
        
        bgColor.setFocusable(false);
        lnColor.setFocusable(false);
        snColor.setFocusable(false);
        apColor.setFocusable(false);
        papColor.setFocusable(false);
        
        //slider.setFocusable(false);
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
                    gr.setColor(snake);
                    gr.fillRect(j*20, i*20,20,20);
                }
                else if (myGame.mas[i][j]==-1)
                {
                    gr.setColor(apple);
                    gr.fillRect(j*20, i*20,20,20);
                }
                else if (myGame.mas[i][j]==-2)
                {
                    gr.setColor(poapple);
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
        if (!isplay)
        {
            gr.setColor(fon);
            gr.fillRect(375,450,300,100);
            gr.setColor(reverseColor(fon));
            gr.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            gr.drawString("Пауза", 375+25,450+75);
        }
        if (myGame.kon)
        {
            gr.setColor(fon);
            gr.fillRect(375,450,300,100);
            gr.setColor(reverseColor(fon));
            gr.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            gr.drawString("Конец игры", 375+25,450+75);
        }
        if (issettings)
        {
            gr.setColor(fon);
            gr.fillRect(375,450,300,100);
            gr.setColor(reverseColor(fon));
            gr.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            gr.drawString("Настройки", 375+25,450+75);
            gr.setColor(fon);
            gr.fillRect(200,175,575,50);
            gr.fillRect(350,300,200,50);
            for (int i = 0; i < 5; i++)
            {
                gr.fillRect(i*200,75,150,25);
            }
            gr.setColor(reverseColor(fon));
            gr.setFont(new Font("TimesRoman",0,20));
            gr.drawString("Цвет фона", 0,75+20);
            gr.drawString("Цвет линий", 200,75+20);
            gr.drawString("Цвет змейки", 400,75+20);
            gr.drawString("Цвет яблока", 600,75+20);
            gr.drawString("Цвет яд. яблока", 800,75+20);
            gr.drawString("Изменения вступят в силу только после закрытия настроек",200+10,175+20+10);
            gr.drawString("Скорость игры",350+10,300+20+10);
        }
    }
}
