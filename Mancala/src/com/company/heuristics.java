package com.company;

public class heuristics {
    private int w1;
    private int w2;
    private int w3;
    private int w4;

    public heuristics()
    {
        this.w1 = 1;
        this.w2 = 2;
        this.w3 = 3;
        this.w4 = 4;
    }

    public heuristics(int w1, int w2, int w3) {
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
    }

    public heuristics(int w1, int w2, int w3, int w4) {
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
    }

    public int getW1() {
        return w1;
    }

    public void setW1(int w1) {
        this.w1 = w1;
    }

    public int getW2() {
        return w2;
    }

    public void setW2(int w2) {
        this.w2 = w2;
    }

    public int getW3() {
        return w3;
    }

    public void setW3(int w3) {
        this.w3 = w3;
    }

    public int heuristic_1(int my_storage, int opponent_storage)
    {
        return my_storage - opponent_storage;
    }

    public int heuristic_2(board b, int option)
    {
        int stones_in_my_storage = b.getOpponent_store();
        int stones_in_opponent_storage = b.getMy_store();
        int stones_on_my_side = b.total_stones(b.getOpponent_pockets());
        int stones_on_opponent_side = b.total_stones(b.getMy_pockets());

        if (option == 1)
            return (this.w1*(stones_in_my_storage - stones_in_opponent_storage) + this.w2*(stones_on_my_side - stones_on_opponent_side));
        else
            return (this.w1*(stones_in_opponent_storage - stones_in_my_storage) + this.w2*(stones_on_opponent_side - stones_on_my_side));
    }

    public int heuristic_3(board b, int option)
    {
        int stones_in_my_storage = b.getOpponent_store();
        int stones_in_opponent_storage = b.getMy_store();
        int stones_on_my_side = b.total_stones(b.getOpponent_pockets());
        int stones_on_opponent_side = b.total_stones(b.getMy_pockets());
        int additional_move_earned = b.getAdditional_move_earned();

        if (option == 1)
            return (this.w1*(stones_in_my_storage - stones_in_opponent_storage) + this.w2*(stones_on_my_side - stones_on_opponent_side) + (this.w3*additional_move_earned));
        else
            return (this.w1*(stones_in_opponent_storage - stones_in_my_storage) + this.w2*(stones_on_opponent_side - stones_on_my_side) + (this.w3*additional_move_earned));
    }

    public int heuristic_4(board b, int option)
    {
        int y;
        if (option == 1)
        {
           int x = b.getOpponent_store();
           if (x <= 24)
           {
               y = ((x/24)*100);
           }
           else y = 100;
        }
        else
        {
            int x = b.getMy_store();
            if (x <= 24)
            {
                y = ((x/24)*100);
            }
            else y = 100;
        }

        return heuristic_2(b,option) + w4*y;
    }

    public int heuristic_5(board b, int option)
    {
        int sum_0 = 0;
        if (option == 0)
        {
            for (int i =0; i<6; i++)
            {
                int j = 5-i;
                if (b.getMy_pockets()[i] <= j)
                {
                    sum_0 = sum_0 + b.getMy_pockets()[i];
                }
                else if(b.getMy_pockets()[i] > j && b.getMy_pockets()[i]<= (j+6))
                {
                    sum_0 = sum_0 + j;
                }
            }
            return sum_0;
        }


        if (option == 1)
        {
            for (int i =5; i>=0; i--)
            {
                //int j = 5-i;
                if (b.getOpponent_pockets()[i] <= i+1)
                {
                    sum_0 = sum_0 + b.getOpponent_pockets()[i];
                }
                else if(b.getOpponent_pockets()[i] > i+1 && b.getMy_pockets()[i]<= (i+7))
                {
                    sum_0 = sum_0 + b.getOpponent_pockets()[i] - (i+1);
                }
            }
            return sum_0;
        }

        return sum_0;
    }

    public int heuristic_6(board b, int option)
    {
        int xtra_move = 0;
        if (option == 0)
        {
            for (int i =0; i<6; i++)
            {
                if (b.getMy_pockets()[i] == (6-i))
                    xtra_move++;
            }
        }
        else
        {
            for (int i =5; i>=0; i--)
            {
                if (b.getOpponent_pockets()[i] == (i+1))
                    xtra_move++;
            }
        }

        return xtra_move;
    }
}
