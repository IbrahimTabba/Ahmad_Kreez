package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static square [][] grid;
    static Queue<square> queue;
    static ArrayList<square> nexts;
    static int x;
    static int y;
    static int ix;
    static int jy;
    static Scanner scanner1;
    static Scanner scanner2;
    static Scanner scanner3;

    public static void main(String[] args) {


        queue=new LinkedList<>();
        nexts = new ArrayList<>();

        System.out.println("Enter the dimensions of grid :");
        scanner1=new Scanner( System.in );
        scanner2=new Scanner( System.in );

         x=scanner1.nextInt();
         y=scanner2.nextInt();

         grid=new square[x][y];

         System.out.println("select the color : (a,b,c,d,e,f,w)");
         initialize_grid(x,y);
         print_grid( grid);
         int w_i,w_j;
         w_i=0;
         w_j=0;
         for(int i=0;i<x;i++)
         {
             for(int j=0;j<y;j++)
             {
                 if(grid[i][j].color.equals( "w" ))
                 {
                    w_i=i;
                    w_j=j;
                 }
             }
         }
        System.out.println("chose betwen 1- bfs  or  2- dfs");
        scanner3=new Scanner( System.in );
         if(scanner3.nextInt()==1)
            bfs(grid[w_i][w_j],w_i,w_j);
         else
             dfs(grid[w_i][w_j],w_i,w_j);

         /*while (true)
         {
             System.out.println("enter your move x and y :" +"\n" +"press z to exit");
             if(scanner1.next().equals( "z" ))
                 break;
             play();
             print_grid(grid);

         }

         if(win())
             System.out.println("you win ...");
         else
             System.out.println("you loss ...");

         if(insid_grid( x,y ))
             System.out.println("insid");
         else
             System.out.println("not insid");*/



    }

    static void initialize_grid(int ii, int jj)
    {
        for(int i=0;i<ii;i++)
        {
            for(int j=0;j<jj;j++)
            {
                System.out.println("Enter the color of square : "+i+" "+j);
                Scanner scanner=new Scanner( System.in );
                grid[i][j]= new square();
                grid[i][j].color=scanner.next();
                grid[i][j].direction="n";

            }
        }
    }

    static ArrayList<square> around_square(square squaree,int i,int j )
    {
        ArrayList<square> arrayList = new ArrayList<>(  );

                    if (insid_grid( i + 1, j ) ) {
                        grid[i + 1][j].direction = "d";
                        grid[i + 1][j].prev=grid[i][j];
                       // System.out.println((i+1)+" "+j);
                       // System.out.println(grid[i + 1][j].visited);
                        arrayList.add( grid[i + 1][j] );
                    }
                    if (insid_grid( i - 1, j ) ) {
                        grid[i - 1][j].direction = "u";
                        grid[i - 1][j].prev=grid[i][j];
                       // System.out.println("up");
                        //System.out.println((i-1)+" "+j);
                       // System.out.println(grid[i - 1][j].visited);
                        arrayList.add( grid[i - 1][j] );
                    }
                    if (insid_grid( i, j + 1 ) ) {
                        grid[i][j + 1].direction = "r";
                        grid[i][j+1].prev=grid[i][j];
                       // System.out.println(i+" "+(j+1));
                       // System.out.println(grid[i ][j+ 1].visited);
                        arrayList.add( grid[i][j + 1] );
                    }
                    if (insid_grid( i, j - 1 ) ) {
                        grid[i][j - 1].direction = "l";
                        grid[i][j-1].prev=grid[i][j];
                       // System.out.println(i+" "+(j-1));
                       // System.out.println(grid[i ][j- 1].visited);
                        arrayList.add( grid[i][j - 1] );
                    }
        return arrayList;
    }

    static  Pair<Boolean,String> can_move(int i, int j)
    {
        if(!grid[i][j].color.equals("w"))
        {
            if(insid_grid(i+1,j))
            {
                if(grid[i+1][j].color.equals("w"))
                    return new Pair<>(true,"u");
            }
            else if(insid_grid(i-1,j))
            {
                if(grid[i-1][j].color.equals("w"))
                    return new Pair<>(true,"d");
            }
            else if(insid_grid(i,j+1))
            {
                if(grid[i][j+1].color.equals("w"))
                    return new Pair<>(true,"r");
            }
            else if(insid_grid(i,j-1))
            {
                if(grid[i][j-1].color.equals("w"))
                    return new Pair<>(true,"l");
            }
        }

            return new Pair<>(false,"");

    }

    static boolean win()
    {
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {

                if(!around( i,j ))
                    return false;

            }
        }

        return true;
    }

    static boolean around(int i,int j)
    {
        if(insid_grid(i+1,j))
        {
            if(grid[i][j].color.equals(grid[i+1][j].color))
                return false;

        }
        if(insid_grid(i-1,j))
        {
            if(grid[i][j].color.equals( grid[i-1][j].color ))
                return false;

        }
        if(insid_grid(i,j+1))
        {
            if(grid[i][j].color.equals( grid[i][j+1].color ))
                return false;
        }
        if(insid_grid(i,j-1))
        {
            if(grid[i][j].color.equals( grid[i][j-1].color ))
                return false;
        }
        return true;
    }

    static  boolean insid_grid(int i,int j)
    {
        if(i<0 || i>=x || j<0 || j>=y)
            return false;
        else
            return true;
    }

    static void print_grid(square[][] s)
    {
        for(int i = 0; i < x ; i ++){

            System.out.println();
            for(int j = 0 ; j < y ; j++){
                System.out.print(" | " + s[i][j].color  );
            }
            System.out.print(" |");
            System.out.println();
        }
    }

    static void play()
    {
        int xxx=scanner1.nextInt();
        int yyy=scanner2.nextInt();
        if(can_move(xxx,yyy).getKey())
        {
            if(can_move(xxx,yyy).getValue().equals( "u" ))
            {
                grid[xxx+1][yyy].color=grid[xxx][yyy].color;
                grid[xxx][yyy].color="w";

            }
            else if(can_move(xxx,yyy).getValue().equals( "d" ))
            {
                grid[xxx-1][yyy].color=grid[xxx][yyy].color;
                grid[xxx][yyy].color="w";

            }
            else if(can_move(xxx,yyy).getValue().equals( "r" ))
            {
                grid[xxx][yyy+1].color=grid[xxx][yyy].color;
                grid[xxx][yyy].color="w";

            }
            else if(can_move(xxx,yyy).getValue().equals( "d" ))
            {
                grid[xxx][yyy-1].color=grid[xxx][yyy].color;
                grid[xxx][yyy].color="w";

            }
        }
        else
        {
            System.out.println( "renter your move x and y :" );
            play();
        }
    }

    static void dfs(square squaree, int i, int j)
    {

        if(win())
        {
            System.out.println("solve :");
            print_grid( grid );
            return;
        }

            ArrayList<square> squares=around_square(squaree,i,j);
            grid[i][j].visited=true;
            for(int ii=0;ii<squares.size();ii++)
            {
                square s=squares.get( ii );
                if(!s.visited )
                {
               // System.out.println(s.direction);
                   // System.out.println(squares.size());
                    if(s.direction.equals( "d" ))
                    {
                        grid[i][j].color=grid[i+1][j].color;
                        grid[i+1][j].color="w";
                        grid[i+1][j].visited=true;
                        dfs( grid[i+1][j] ,i+1,j);
                        grid[i+1][j].visited=false;
                        grid[i+1][j].color=grid[i][j].color;
                        grid[i][j].color="w";
                       // print_grid( grid );
                    }
                    else if(s.direction.equals( "u" ))
                    {
                        grid[i][j].color=grid[i-1][j].color;
                        grid[i-1][j].color="w";
                        grid[i-1][j].visited=true;
                        dfs(  grid[i-1][j] ,i-1,j);
                        grid[i-1][j].visited=false;
                        grid[i-1][j].color=grid[i][j].color;
                        grid[i][j].color="w";
                      //  print_grid( grid );

                    }
                    else if(s.direction.equals( "r" ))
                    {
                        grid[i][j].color=grid[i][j+1].color;
                         grid[i][j+1].color="w";
                        grid[i][j+1].visited=true;
                        dfs( grid[i][j+1] ,i,j+1);
                        grid[i][j+1].visited=false;
                        grid[i][j+1].color=grid[i][j].color;
                        grid[i][j].color="w";
                     //   print_grid( grid );
                    }
                    else if(s.direction.equals( "l" ))
                    {
                        grid[i][j].color=grid[i][j-1].color;
                        grid[i][j-1].color="w";
                        grid[i][j-1].visited=true;
                        dfs( grid[i][j-1] ,i,j-1);
                        grid[i][j-1].visited=false;
                        grid[i][j-1].color=grid[i][j].color;
                        grid[i][j].color="w";
                       // print_grid( grid );
                    }


            }
        }
        squares.clear();

    }

    static void bfs(square squaree,int i,int j) {

        ix=i;
        jy=j;
        queue.add( squaree );
        squaree.visited = true;

        while (!queue.isEmpty()) {

            square element = queue.remove();

            if(!element.color.equals( "w" ))
            {
                nexts.clear();
                mnext(element,i,j);
                //print_grid( grid );
            }

            ArrayList<square> neighbours = around_square(element,ix,jy);

            for (int ii = 0; ii < neighbours.size(); ii++) {
                square s = neighbours.get( ii );

                if ( !s.visited) {
                    queue.add( s );
                    //System.out.println(s.direction);
                    if(s.direction.equals( "d" ))
                    {
                        grid[ix][jy].color=grid[ix+1][jy].color;
                        grid[ix+1][jy].color="w";
                        if(win())
                        {
                            System.out.println("solve :");
                            print_grid( grid );
                        }
                        grid[ix+1][jy].color=grid[ix][jy].color;
                        grid[ix][jy].color="w";
                    }
                    else if(s.direction.equals( "u" ))
                    {
                        grid[ix][jy].color=grid[ix-1][jy].color;
                        grid[ix-1][jy].color="w";
                        if(win())
                        {
                            System.out.println("solve :");
                            print_grid( grid );
                        }
                        grid[ix-1][jy].color=grid[ix][jy].color;
                        grid[ix][jy].color="w";
                    }
                    else if(s.direction.equals( "r" ))
                    {
                        grid[ix][jy].color=grid[ix][jy+1].color;
                        grid[ix][jy+1].color="w";
                        if(win())
                        {
                            System.out.println("solve :");
                            print_grid( grid );
                        }
                        grid[ix][jy+1].color=grid[ix][jy].color;
                        grid[ix][jy].color="w";
                    }
                    else if(s.direction.equals( "l" ))
                    {
                        grid[ix][jy].color=grid[ix][jy-1].color;
                        grid[ix][jy-1].color="w";
                        if(win())
                        {
                            System.out.println("solve :");
                            print_grid( grid );
                        }
                        grid[ix][jy-1].color=grid[ix][jy].color;
                        grid[ix][jy].color="w";
                    }

                    s.visited = true;
                }
            }
            mbefor();
        }
    }

    static void mbefor()
    {
        for(int i=0;i<nexts.size();i++)
        {

            if( nexts.get( i ).direction.equals( "d" ))
            {
                grid[ix][jy].color=grid[ix-1][jy].color;
                grid[ix-1][jy].color="w";
                ix=ix-1;

            }
            else if(nexts.get( i ).direction.equals( "u" ))
            {
                grid[ix][jy].color=grid[ix+1][jy].color;
                grid[ix+1][jy].color="w";
                ix=ix+1;

            }
            else if(nexts.get( i ).direction.equals( "r" ))
            {
                grid[ix][jy].color=grid[ix][jy-1].color;
                grid[ix][jy-1].color="w";
                jy=jy-1;

            }
            else if(nexts.get( i ).direction.equals( "l" ))
            {
                grid[ix][jy].color=grid[ix][jy+1].color;
                grid[ix][jy+1].color="w";
                jy=jy+1;

            }
        }
    }

    static ArrayList<square> next(square s) {



        if (s.color.equals( "w" ))
            return nexts;
        else
        {
            nexts.add( s );

            next( s.prev );
        }
        return nexts;
    }

    static void mnext(square s,int ii ,int jj) {

        ArrayList<square> move_w = next( s );
        //System.out.println(move_w.size());

        ix=ii;
        jy=jj;

        for(int i=(move_w.size()-1);i>=0;i--)
        {

            if( move_w.get( i ).direction.equals( "d" ))
            {
                grid[ix][jy].color=grid[ix+1][jy].color;
                grid[ix+1][jy].color="w";
                ix=ix+1;
            }
            else if(move_w.get( i ).direction.equals( "u" ))
            {
                grid[ix][jy].color=grid[ix-1][jy].color;
                grid[ix-1][jy].color="w";
                ix=ix-1;
            }
            else if(move_w.get( i ).direction.equals( "r" ))
            {
                grid[ix][jy].color=grid[ix][jy+1].color;
                grid[ix][jy+1].color="w";
                jy=jy+1;
            }
            else if(move_w.get( i ).direction.equals( "l" ))
            {
                grid[ix][jy].color=grid[ix][jy-1].color;
                grid[ix][jy-1].color="w";
                jy=jy-1;
            }
        }


    }

}
