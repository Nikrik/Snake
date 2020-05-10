/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikrik.snake;

import java.util.Random;

/**
 *
 * @author Nikrik
 */
public class game
{
    public int[][] mas;
    public Random rand=new Random();
    long seed;
    boolean kon;
    public game()
    {
        mas=new int[30][30];
        //this.start();
    }
    private void make_new()
    {
        int x,y;
        do
        {
            do
            {
                x=rand.nextInt();
            } while (x<0);
            do
            {
                y=rand.nextInt();
            } while (y<0);
            y=y%30;
            x=x%30;
            if (!(mas[x][y]>0))
            {
                mas[x][y]=-1;
            }
        } while(!(mas[x][y]==-1));
    }
    public void tick(int napr)
    {   
        switch(napr)
        {
            case 1:
                steep(0,-1);
                break;
            case 2:
                steep(-1,0);
                break;
            case 3:
                steep(0,1);
                break;
            case 4:
                steep(1,0);
                break;
        }
        
    }
    
    public int score()
    {
        int max=mas[0][0];
        int x=0,y=0;
        for (int i = 0; i < 30; i++)
        {
            for (int j = 0; j < 30; j++)
            {
                if (max<mas[i][j])
                {
                    max=mas[i][j];
                    x=i;
                    y=j;
                }
            }
        }
        return max;
    }
    private void steep(int m, int n)
    {
        if (kon)
        {
            return;
        }
        int max=mas[0][0];
        int x=0,y=0;
        for (int i = 0; i < 30; i++)
        {
            for (int j = 0; j < 30; j++)
            {
                if (max<mas[i][j])
                {
                    max=mas[i][j];
                    x=i;
                    y=j;
                }
            }
        }
        
        m=x+m;
        n=y+n;
        m=m%30;
        n=n%30;
        if (n<0)
        {
            n=30+n;
        }
        if (m<0)
        {
            m=30+m;
        }
        if (mas[m][n]==mas[x][y]-1)
        {
            m=x+x-m;
            n=y+y-n;
        }
        m=m%30;
        n=n%30;
        if (n<0)
        {
            n=30+n;
        }
        if (m<0)
        {
            m=30+m;
        }
        if (mas[m][n]==-1)
        {
            mas[m][n]=mas[x][y]+1;
            make_new();
        }
        else if (mas[m][n]>1)
        {
            kon=true;
            System.out.println("Конец игры");//TO DO
        }
        else if (mas[m][n]==0||mas[m][n]==1)
        {
         for (int i = 0; i < 30; i++)
            {
                for (int j = 0; j < 30; j++)
                {
                    if (mas[i][j]>0)
                    {
                        mas[i][j]=mas[i][j]-1;
                    }
                }
            }
            mas[m][n]=mas[x][y]+1;   
        }
        else System.out.println("WTF, такого не должно было произойти");
    }
    
    public void start()
    {
        kon=false;
        seed=rand.nextLong();
        rand.setSeed(seed);
        for (int i = 0; i < 30; i++)
        {
            for (int j = 0; j < 30; j++)
            {
                mas[i][j]=0;
            }
        }
        mas[15][15]=3;
        make_new();
        
//        for (int i = 0; i < 30; i++)
//        {
//            for (int j = 0; j < 30; j++)
//            {
//                System.out.print(mas[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
}