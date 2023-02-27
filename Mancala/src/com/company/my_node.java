package com.company;

import java.util.Vector;

public class my_node {
    private board b;
    private int parent;
    private Vector<my_node> v;

    public my_node()
    {
        this.b = new board();
        v = new Vector<my_node>();
        parent = 0;
    }

    public my_node(board b, int parent, Vector<my_node> v) {
        this.b = b;
        this.parent = parent;
        this.v = v;
    }

    public board getB() {
        return b;
    }

    public void setB(board b) {
        b.copy_board(this.b);
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public Vector<my_node> getV() {
        return v;
    }

    public void setV(Vector<my_node> v) {
        this.v = v;
    }


    public board[] create_child(board b)
    {
        board[] my_child = new board[6];
        for (int i =0; i<6;i++)
            my_child[i] = new board();

        //b1.print_board();
        for (int i =0; i<6;i++)
        {
            if (b.getOpponent_pockets()[i] != 0)
            {
                //System.out.println("here");
                board b1 = new board();
                b.copy_board(b1);
                //b1.print_board();
                b1.change_state(i+1,1);
                //b1.print_board();
                b1.copy_board(my_child[i]);
                //my_child[i].print_board();
            }
            else
                my_child[i] = null;
        }
        return my_child;
    }

    public void all_valid_childs()
    {
        board[] my_child = create_child(this.b);
        for (int i =0; i<6; i++)
        {
            if (my_child[i] != null)
            {
                my_node mn = new my_node();
                mn.setB(my_child[i]);
                mn.setParent(i+1);
                v.add(mn);
            }
        }
    }

    public int child_count()
    {
        return this.v.size();
    }

    public Vector<my_node> getchild()
    {
        Vector<my_node> v1 = new Vector<my_node>();
        for (int i =0; i<this.v.size(); i++)
        {
            v1.add(this.v.elementAt(i));
        }
        return v1;
    }
}
