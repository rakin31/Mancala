package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        board b = new board();
        //b.print_board();
        //b.print_board();
        //b.change_state(3,0);
        //b.print_board();
        //b.change_state(6,0);
        //b.print_board();
        /*Scanner sc = new Scanner(System.in);
        while (true) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            b.change_state(x, y);
            b.print_board();
        }*/
        moves mv = new moves();
        //mv.play_game();
        //mv.playAI_vs_human();
        mv.play_AI_vs_AI();

        //mini_max_algo mmm = new mini_max_algo();
        //mmm.minmax();

        //my_node n1 = new my_node();
        //for (int i =0; i<6;i++)
          //  n1.create_child(b)[i].print_board();
    }
}
