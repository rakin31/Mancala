package com.company;

import java.util.Scanner;

public class moves {
    private board mancala;

    public  moves()
    {
        mancala = new board();
    }

    public moves(board mancala) {
        this.mancala = mancala;
    }

    public board getMancala() {
        return mancala;
    }

    public void setMancala(board mancala) {
        this.mancala = mancala;
    }

    public void play_game()
    {
        Scanner sc = new Scanner(System.in);
        int new_move;
        int x;
        System.out.println("Starting the game. The lower row is your pocket and upper row is opponents pocket.");
        this.mancala.print_board();
        while (true) {
            System.out.println("Your turn :");
            System.out.println("Enter the pocket no (1-6) :");
            x = sc.nextInt();
            new_move = this.mancala.change_state(x, 0);
            mancala.print_board();
            while (new_move == 1 && !this.mancala.pockets_empty(this.mancala.getMy_pockets()))
            {
                System.out.println("You have earned an extra move. ");
                System.out.println("Enter the pocket no (1-6) :");
                x = sc.nextInt();
                new_move = this.mancala.change_state(x, 0);
                mancala.print_board();
            }
            if (this.mancala.pockets_empty(this.mancala.getMy_pockets())) {
                int sum=0;
                for (int i = 0; i<this.mancala.getOpponent_pockets().length; i++) {
                    sum = sum + this.mancala.getOpponent_pockets()[i];
                    this.mancala.getOpponent_pockets()[i] = 0;
                }
                this.mancala.setOpponent_store(this.mancala.getOpponent_store()+sum);
                break;
            }

            if (this.mancala.pockets_empty(this.mancala.getOpponent_pockets()))
            {
                int sum=0;
                for (int i = 0; i<this.mancala.getMy_pockets().length; i++) {
                    sum = sum + this.mancala.getMy_pockets()[i];
                    this.mancala.getMy_pockets()[i] = 0;
                }
                this.mancala.setMy_store(this.mancala.getMy_store()+sum);
                break;
            }
            System.out.println("Opponents turn :");
            System.out.println("Enter the pocket no (1-6) :");
            x = sc.nextInt();
            new_move = this.mancala.change_state(x, 1);
            mancala.print_board();
            //System.out.println(new_move);
            while (new_move == 1 && !this.mancala.pockets_empty(this.mancala.getOpponent_pockets()))
            {
                System.out.println("You have earned an extra move. ");
                System.out.println("Enter the pocket no (1-6) :");
                x = sc.nextInt();
                new_move = this.mancala.change_state(x, 1);
                mancala.print_board();
            }
            if (this.mancala.pockets_empty(this.mancala.getOpponent_pockets()))
            {
                int sum=0;
                for (int i = 0; i<this.mancala.getMy_pockets().length; i++) {
                    sum = sum + this.mancala.getMy_pockets()[i];
                    this.mancala.getMy_pockets()[i] = 0;
                }
                this.mancala.setMy_store(this.mancala.getMy_store()+sum);
                break;
            }

            if (this.mancala.pockets_empty(this.mancala.getMy_pockets())) {
                int sum=0;
                for (int i = 0; i<this.mancala.getOpponent_pockets().length; i++) {
                    sum = sum + this.mancala.getOpponent_pockets()[i];
                    this.mancala.getOpponent_pockets()[i] = 0;
                }
                this.mancala.setOpponent_store(this.mancala.getOpponent_store()+sum);
                break;
            }
        }
        System.out.println("Final Mancala Board : ");
        this.mancala.print_board();
        if (this.mancala.getMy_store() > this.mancala.getOpponent_store())
            System.out.println("You have won");
        else if (this.mancala.getMy_store() < this.mancala.getOpponent_store())
            System.out.println("Opponent has won");
        else if (this.mancala.getMy_store() == this.mancala.getOpponent_store())
            System.out.println("Match drawn");

    }

    public void playAI_vs_human()
    {
        Scanner sc = new Scanner(System.in);
        while (true) {
            mini_max_algo mm = new mini_max_algo();
            int x = mini_max_algo.minimax(this.mancala, 0, true, -1000000, 1000000,1,1,0);
            //System.out.println(x.getVal());
            //mm.print();
            //System.out.println(mini_max_algo.selected_bin);
            //optimal_solution os1 = new optimal_solution(this.mancala,0,null);
            //optimal_solution os2 = mm.minimax2(this.mancala, 0, -1000000,1000000,true, 1,1,0);
            mini_max_algo.final_board.copy_board(this.mancala);
            //x.getB().copy_board(this.mancala);
            //os2.getB().copy_board(this.mancala);
            //os2.getB().copy_board(this.mancala);


            //int x = mm.my_move(this.mancala,1,1,0,-1000000,1000000);
            //System.out.println("x : " + x);
            //this.mancala.change_state(x,1);
            mancala.print_board();

            if (this.mancala.pockets_empty(this.mancala.getOpponent_pockets()))
            {
                int sum=0;
                for (int i = 0; i<this.mancala.getMy_pockets().length; i++) {
                    sum = sum + this.mancala.getMy_pockets()[i];
                    this.mancala.getMy_pockets()[i] = 0;
                }
                this.mancala.setMy_store(this.mancala.getMy_store()+sum);
                break;
            }

            if (this.mancala.pockets_empty(this.mancala.getMy_pockets())) {
                int sum=0;
                for (int i = 0; i<this.mancala.getOpponent_pockets().length; i++) {
                    sum = sum + this.mancala.getOpponent_pockets()[i];
                    this.mancala.getOpponent_pockets()[i] = 0;
                }
                this.mancala.setOpponent_store(this.mancala.getOpponent_store()+sum);
                break;
            }

            System.out.println("Your turn :");
            System.out.println("Enter the pocket no (1-6) :");
            int y;
            y = sc.nextInt();
            int new_move = this.mancala.change_state(y, 0);
            mancala.print_board();

            if (this.mancala.pockets_empty(this.mancala.getMy_pockets())) {
                int sum=0;
                for (int i = 0; i<this.mancala.getOpponent_pockets().length; i++) {
                    sum = sum + this.mancala.getOpponent_pockets()[i];
                    this.mancala.getOpponent_pockets()[i] = 0;
                }
                this.mancala.setOpponent_store(this.mancala.getOpponent_store()+sum);
                break;
            }

            if (this.mancala.pockets_empty(this.mancala.getOpponent_pockets()))
            {
                int sum=0;
                for (int i = 0; i<this.mancala.getMy_pockets().length; i++) {
                    sum = sum + this.mancala.getMy_pockets()[i];
                    this.mancala.getMy_pockets()[i] = 0;
                }
                this.mancala.setMy_store(this.mancala.getMy_store()+sum);
                break;
            }
        }

        System.out.println("Final Mancala Board : ");
        this.mancala.print_board();
        if (this.mancala.getMy_store() > this.mancala.getOpponent_store())
            System.out.println("You have won");
        else if (this.mancala.getMy_store() < this.mancala.getOpponent_store())
            System.out.println("AI has won");
        else if (this.mancala.getMy_store() == this.mancala.getOpponent_store())
            System.out.println("Match drawn");
    }

    public void play_AI_vs_AI()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Starting the game. The upper row is player 1's pocket and lower row is player 2's pocket");
        this.mancala.print_board();
        while (true) {
            mini_max_algo mm = new mini_max_algo();
            int x = mini_max_algo.minimax(this.mancala, 1, true, -1000000, 1000000,2, 1,0);
            //System.out.println(x);
            //mm.print();
            //System.out.println(mini_max_algo.selected_bin);
            mini_max_algo.final_board.copy_board(this.mancala);
            //x.getB().copy_board(this.mancala);
            mancala.print_board();

            //while ()
            if (this.mancala.pockets_empty(this.mancala.getOpponent_pockets()))
            {
                //System.out.println("in if 1");
                int sum=0;
                for (int i = 0; i<this.mancala.getMy_pockets().length; i++) {
                    sum = sum + this.mancala.getMy_pockets()[i];
                    this.mancala.getMy_pockets()[i] = 0;
                }
                this.mancala.setMy_store(this.mancala.getMy_store()+sum);
                break;
            }

            if (this.mancala.pockets_empty(this.mancala.getMy_pockets())) {
                //System.out.println("in if 2");
                int sum=0;
                for (int i = 0; i<this.mancala.getOpponent_pockets().length; i++) {
                    sum = sum + this.mancala.getOpponent_pockets()[i];
                    this.mancala.getOpponent_pockets()[i] = 0;
                }
                this.mancala.setOpponent_store(this.mancala.getOpponent_store()+sum);
                break;
            }

            //int a = sc.nextInt();
            //System.out.println("after if");
            int y = mini_max_algo.minimax(this.mancala, 0, true, -1000000, 1000000,2, 0,1);
            //System.out.println(y.getVal());
            //mm.print();
            //System.out.println(mini_max_algo.selected_bin);
            mini_max_algo.final_board.copy_board(this.mancala);
            //y.getB().copy_board(this.mancala);
            mancala.print_board();

            if (this.mancala.pockets_empty(this.mancala.getMy_pockets())) {
                int sum=0;
                for (int i = 0; i<this.mancala.getOpponent_pockets().length; i++) {
                    sum = sum + this.mancala.getOpponent_pockets()[i];
                    this.mancala.getOpponent_pockets()[i] = 0;
                }
                this.mancala.setOpponent_store(this.mancala.getOpponent_store()+sum);
                break;
            }

            if (this.mancala.pockets_empty(this.mancala.getOpponent_pockets()))
            {
                int sum=0;
                for (int i = 0; i<this.mancala.getMy_pockets().length; i++) {
                    sum = sum + this.mancala.getMy_pockets()[i];
                    this.mancala.getMy_pockets()[i] = 0;
                }
                this.mancala.setMy_store(this.mancala.getMy_store()+sum);
                break;
            }
            //System.out.println("i am here");
        }

        System.out.println("Final Mancala Board : ");
        this.mancala.print_board();
        if (this.mancala.getMy_store() > this.mancala.getOpponent_store())
            System.out.println("Player 2 has won");
        else if (this.mancala.getMy_store() < this.mancala.getOpponent_store())
            System.out.println("Player 1 has won");
        else if (this.mancala.getMy_store() == this.mancala.getOpponent_store())
            System.out.println("Match drawn");
    }
}
