package com.company;

public class board {
    private int[] my_pockets;
    private int[] opponent_pockets;
    private int my_store;
    private int opponent_store;
    private int additional_move_earned;

    public board()
    {
        my_pockets = new int[6];
        opponent_pockets = new int[6];
        my_store = 0;
        opponent_store = 0;
        additional_move_earned = 0;

        for (int i =  0; i<6; i++)
        {
            my_pockets[i] = 4;
            opponent_pockets[i] = 4;
        }
    }

    public int[] getMy_pockets() {
        return my_pockets;
    }

    public void setMy_pockets(int[] my_pockets) {
        this.my_pockets = my_pockets;
    }

    public int[] getOpponent_pockets() {
        return opponent_pockets;
    }

    public void setOpponent_pockets(int[] opponent_pockets) {
        this.opponent_pockets = opponent_pockets;
    }

    public int getMy_store() {
        return my_store;
    }

    public void setMy_store(int my_store) {
        this.my_store = my_store;
    }

    public int getOpponent_store() {
        return opponent_store;
    }

    public void setOpponent_store(int opponent_store) {
        this.opponent_store = opponent_store;
    }

    public int getAdditional_move_earned() {
        return additional_move_earned;
    }

    public void setAdditional_move_earned(int additional_move_earned) {
        this.additional_move_earned = additional_move_earned;
    }

    public board(int[] my_pockets, int[] opponent_pockets, int my_store, int opponent_store) {
        this.my_pockets = my_pockets;
        this.opponent_pockets = opponent_pockets;
        this.my_store = my_store;
        this.opponent_store = opponent_store;
    }

    public int change_state(int pocket, int side)
    {
        this.additional_move_earned = 0;
        int array_pos = pocket - 1;
        int num_of_stones = 0;
        int array_pos_op = array_pos;
        int array_pos_own = array_pos;
        if ( side == 0 )
        {
            num_of_stones = my_pockets[array_pos];
            this.my_pockets[array_pos] = 0;
            while (num_of_stones > 0) {
                if (num_of_stones > (5 - array_pos_own)) {
                    for (int i = array_pos_own + 1; i < 6; i++) {
                        this.my_pockets[i] = this.my_pockets[i] + 1;
                        num_of_stones = num_of_stones - 1;
                    }
                    array_pos_own = -1;
                    if (num_of_stones > 0) {
                        this.my_store = this.my_store + 1;
                        num_of_stones = num_of_stones - 1;
                        if (num_of_stones == 0) {
                            this.my_pockets[array_pos] = 0;
                            this.additional_move_earned = 1;
                            return 1;
                        }
                    }
                    if (num_of_stones > 0 && num_of_stones <= 6) {
                        for (int i = 5; num_of_stones > 0; i--) {
                            this.opponent_pockets[i] = this.opponent_pockets[i] + 1;
                            num_of_stones = num_of_stones - 1;
                        }
                    } else if (num_of_stones > 0 && num_of_stones > 6) {
                        for (int i = 5; i >= 0; i--) {
                            this.opponent_pockets[i] = this.opponent_pockets[i] + 1;
                            num_of_stones = num_of_stones - 1;
                        }
                    }
                } else {
                    for (int i = array_pos_own + 1;  num_of_stones> 0; i++) {
                        if (num_of_stones == 1 )
                        {
                            if (this.my_pockets[i] == 0 && this.opponent_pockets[i] != 0)
                            {
                                this.my_store = this.my_store + this.opponent_pockets[i] + 1;
                                this.opponent_pockets[i] = 0;
                                this.my_pockets[i] = 0;
                                break;
                            }
                        }
                        this.my_pockets[i] = this.my_pockets[i] + 1;
                        num_of_stones = num_of_stones - 1;

                    }
                    num_of_stones = 0;
                }
            }

        }

        if ( side == 1 )
        {
            num_of_stones = opponent_pockets[array_pos];
            this.opponent_pockets[array_pos] = 0;
            while (num_of_stones > 0) {
                if (num_of_stones >  array_pos_op) {
                    for (int i = array_pos_op - 1; i >= 0; i--) {
                        this.opponent_pockets[i] = this.opponent_pockets[i] + 1;
                        num_of_stones = num_of_stones - 1;
                    }
                    array_pos_op = 6;
                    if (num_of_stones > 0) {
                        this.opponent_store = this.opponent_store + 1;
                        num_of_stones = num_of_stones - 1;
                        if (num_of_stones == 0) {
                            this.opponent_pockets[array_pos] = 0;
                            this.additional_move_earned = 1;
                            return 1;
                        }
                    }
                    if (num_of_stones > 0 && num_of_stones <= 6) {
                        for (int i = 0; num_of_stones > 0; i++) {
                            this.my_pockets[i] = this.my_pockets[i] + 1;
                            num_of_stones = num_of_stones - 1;
                        }
                    } else if (num_of_stones > 0 && num_of_stones > 6) {
                        for (int i = 0; i < 6; i++) {
                            this.my_pockets[i] = this.my_pockets[i] + 1;
                            num_of_stones = num_of_stones - 1;
                        }
                    }
                } else {
                    for (int i = array_pos_op - 1;  num_of_stones> 0; i--) {
                        if (num_of_stones == 1 )
                        {
                            if (this.opponent_pockets[i] == 0 && this.my_pockets[i] != 0)
                            {
                                this.opponent_store = this.opponent_store + this.my_pockets[i] + 1;
                                this.opponent_pockets[i] = 0;
                                this.my_pockets[i] = 0;
                                break;
                            }
                        }
                        this.opponent_pockets[i] = this.opponent_pockets[i] + 1;
                        num_of_stones = num_of_stones - 1;

                    }
                    num_of_stones = 0;
                }
            }

        }

        return 0;
    }

    boolean pockets_empty(int[] pockets)
    {
        for (int i=0; i<pockets.length; i++)
        {
            if (pockets[i] != 0)
                return false;
        }
        return true;
    }

    public void copy_board(board b)
    {
        //b.setMy_pockets(this.getMy_pockets());
        if (b == null)
            b = new board();
        for (int i =0; i<6; i++)
            b.getMy_pockets()[i] = this.getMy_pockets()[i];
        for (int i =0; i<6; i++)
            b.getOpponent_pockets()[i] = this.getOpponent_pockets()[i];

        //b.setOpponent_pockets(this.getOpponent_pockets());
        b.setMy_store(this.getMy_store());
        b.setOpponent_store(this.getOpponent_store());
        b.setAdditional_move_earned(this.getAdditional_move_earned());
    }

    public void clear_board()
    {
        for (int i =0; i<6; i++)
            this.my_pockets[i] = 0;
        for (int i=0; i<6; i++)
            this.opponent_pockets[i] = 0;
        this.my_store = 0;
        this.opponent_store = 0;
    }

    public int total_stones(int[] pockets)
    {
        int sum = 0;
        for (int i =0; i<6; i++)
            sum = sum + pockets[i];
        return sum;
    }

    public void print_board()
    {
        System.out.println("Game Board : ");
        System.out.print("      ");
        for (int i = 0; i<6; i++)
            System.out.print(opponent_pockets[i] + "  ");
        System.out.println();
        System.out.print(opponent_store + "                          " + my_store);
        System.out.println();
        System.out.print("      ");
        for (int i = 0; i<6; i++)
            System.out.print(my_pockets[i] + "  ");
        System.out.println();
        System.out.println();
    }
}
