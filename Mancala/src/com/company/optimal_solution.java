package com.company;

public class optimal_solution {
    private board b;
    private int val;
    //private optimal_solution parent;

    public optimal_solution()
    {
        this.b = new board();
    }


    public optimal_solution(board b, int val) {
        this.b = b;
        this.val = val;
    }



    public board getB() {
        return b;
    }

    public void setB(board b) {
        this.b = b;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void copy_board(board b)
    {
        this.b.copy_board(b);
    }


}
