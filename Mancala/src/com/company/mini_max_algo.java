package com.company;

import javax.swing.*;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class mini_max_algo {

    static int max ;
    static int min ;
    static board final_board;
    static heuristics h;
    static int selected_bin;
    static int move;
    private optimal_solution node;

    static {
        max = 1000000;
        min = -1000000;
        final_board = new board();
        h = new heuristics();
        selected_bin = 0;
        move = -1;
    }

    public mini_max_algo()
    {
        this.node = null;
    }

    public mini_max_algo(optimal_solution node) {
        this.node = node;
    }

    public optimal_solution getNode() {
        return node;
    }

    public void setNode(optimal_solution node) {
        this.node = node;
    }

    public static int minimax(board b, int depth, boolean maximizing_player, int alpha, int beta, int heuristic_no, int max_player, int min_player)
    {
        if (depth == 10 )
        {
            //optimal_solution os = new optimal_solution();
            if (heuristic_no == 1) {
                //os.copy_board(b);
                //os.setVal(h.heuristic_1(b.getOpponent_store(), b.getMy_store()));
                if (max_player == 1)
                    return h.heuristic_1(b.getOpponent_store(), b.getMy_store());
                else
                    return h.heuristic_1( b.getMy_store(), b.getOpponent_store());
            }
            else if (heuristic_no == 2) {
                //os.copy_board(b);
                //os.setVal(h.heuristic_2(b));
                return h.heuristic_2(b, max_player);
            }
            else if (heuristic_no == 3) {
                //os.copy_board(b);
                //os.setVal(h.heuristic_3(b));
                return h.heuristic_3(b, max_player);
            }
            else if (heuristic_no == 4)
            {
                return h.heuristic_4(b,max_player);
            }
            else if (heuristic_no == 5)
            {
                return h.heuristic_5(b,max_player);
            }
            else if (heuristic_no == 6)
            {
                return h.heuristic_6(b,max_player);
            }
        }


        if (maximizing_player)
        {
            //optimal_solution best = new optimal_solution(new board(),min);
            //board temp = null;
            int best = min;
            int curr_move = -1;

            //node.all_valid_childs();
            //int total_child = node.child_count();
            //final_board.clear_board();
            for (int i =0; i<6; i++)
            {
                if (max_player == 1) {
                    if (b.getOpponent_pockets()[i] == 0)
                        continue;
                }
                else if (max_player == 0)
                {
                    if (b.getMy_pockets()[i] == 0)
                        continue;
                }
                board b1 = new board();
                b.copy_board(b1);
                b1.change_state(i+1,max_player);

                //System.out.println();
                //b1.print_board();
                int val = -1000000;
                //if (b1.getAdditional_move_earned() == 0)
                    val = minimax(b1,depth+1,false,alpha,beta,heuristic_no,max_player,min_player);
                //else
                  //  val = minimax(b1,depth+1,true,alpha,beta,heuristic_no,max_player,min_player);
                //final_board.clear_board();
                //int val = osm.getVal();
                //if (depth == 0)
                //  System.out.println("val : " + val + " best : " + best + " depth : " + depth);
                if (val > best) {
                    b.copy_board(final_board);
                    //b.copy_board(temp);
                    //curr_move = node.getchild().elementAt(i).getParent();
                    selected_bin = i + 1;
                }
                /*if (best.getVal() >= val.getVal())
                {
                    best.copy_board(best.getB());
                    best.setVal(best.getVal());
                }
                else
                {
                    best.copy_board(val.getB());
                    best.setVal(val.getVal());
                }*/
                best = max(best,val);
                alpha = max(alpha,best);

                if (beta <= alpha)
                    break;
            }
            /*if (depth == 0)
            {
                temp.copy_board(final_board);
                System.out.println("printing final board");
                final_board.print_board();
            }*/
            return best;
        }
        else
        {
            //optimal_solution best = new optimal_solution(new board(),max);
            //board temp = null;
            int best = max;
            //final_board.clear_board();
            int curr_move = -1;
            for (int i =0; i<6; i++)
            {
                if (max_player == 1) {
                    if (b.getMy_pockets()[i] == 0)
                        continue;
                }
                else if (max_player == 0)
                {
                    if (b.getOpponent_pockets()[i] == 0)
                        continue;
                }
                board b2 = new board();
                b.copy_board(b2);
                b2.change_state(i+1,min_player);

                //System.out.println();
                //b2.print_board();
                int val = -1000000;
                //if (b2.getAdditional_move_earned() == 0)
                    val = minimax(b2,depth+1,true,alpha,beta,heuristic_no,max_player,min_player);
                //else
                  //  val = minimax(b2,depth+1,false,alpha,beta,heuristic_no,max_player,min_player);
                //final_board.clear_board();
                if (val < best) {
                    b.copy_board(final_board);
                    //b.copy_board(temp);
                    //curr_move = node.getchild().elementAt(i).getParent();
                    selected_bin = i + 1;
                }
                /*if (best.getVal() <= val.getVal())
                {
                    best.copy_board(best.getB());
                    best.setVal(best.getVal());
                }
                else
                {
                    best.copy_board(val.getB());
                    best.setVal(val.getVal());
                }*/
                best = min(best,val);
                beta = min(beta,best);

                if (beta <= alpha)
                    break;
            }
            return best;
        }



    }

    public void print()
    {
        final_board.print_board();
    }

    public optimal_solution minimax2(board b, int depth, int alpha, int beta, boolean maximizing_player, int heuristic_no, int max_player, int min_player)
    {
        int[] temp = new int[6];
        int[] temp2 = new int[6];
        for (int i =0;i<6;i++) {
            temp[i] = 0;
            temp2[i] = 0;
        }
        board my_board = new board(temp,temp2,0,0);
        b.copy_board(my_board);
        if (depth == 10 )
        {
            if (heuristic_no == 1) {
                //os.copy_board(b);
                //os.setVal(h.heuristic_1(b.getOpponent_store(), b.getMy_store()));
                if (max_player == 1)
                    return new optimal_solution(my_board,h.heuristic_1(b.getOpponent_store(), b.getMy_store()));
                else
                    return new optimal_solution(my_board,h.heuristic_1( b.getMy_store(), b.getOpponent_store()));
            }
            else if (heuristic_no == 2) {
                //os.copy_board(b);
                //os.setVal(h.heuristic_2(b));
                return new optimal_solution(my_board,h.heuristic_2(b, max_player));
            }
            else if (heuristic_no == 3) {
                //os.copy_board(b);
                //os.setVal(h.heuristic_3(b));
                return new optimal_solution(my_board,h.heuristic_3(b, max_player));
            }
            else if (heuristic_no == 4)
            {
                return new optimal_solution(my_board,h.heuristic_4(b,max_player));
            }
            else if (heuristic_no == 5)
            {
                return new optimal_solution(my_board,h.heuristic_5(b,max_player));
            }
            else if (heuristic_no == 6)
            {
                return new optimal_solution(my_board,h.heuristic_6(b,max_player));
            }
        }

        if (maximizing_player) {
            optimal_solution best = new optimal_solution(null, min);
            //optimal_solution temp = currnode;

            for (int i = 0; i < 6; i++) {
                if (max_player == 1) {
                    if (my_board.getOpponent_pockets()[i] == 0)
                        continue;
                } else if (max_player == 0) {
                    if (my_board.getMy_pockets()[i] == 0)
                        continue;
                }
                board child = null;
                my_board.change_state(i + 1, max_player);
                my_board.copy_board(child);
                if (child != null) {
                    optimal_solution val = null;
                    if (child.getAdditional_move_earned() == 1)
                        val = new optimal_solution(child, minimax2(child, depth + 1, alpha, beta,true,heuristic_no,max_player,min_player).getVal());
                    else
                        val = new optimal_solution(child, minimax2(child, depth + 1, alpha, beta, false, heuristic_no, max_player, min_player).getVal());

                    best = getmax(best, val);
                    alpha = max(alpha, best.getVal());

                    if (beta <= alpha)
                        break;
                }
            }
            return best;
        }
        else
        {
            optimal_solution best = new optimal_solution(null,max);
            for (int i =0; i<6; i++)
            {
                if (max_player == 1) {
                    if (my_board.getMy_pockets()[i] == 0)
                        continue;
                } else if (max_player == 0) {
                    if (my_board.getOpponent_pockets()[i] == 0)
                        continue;
                }

                board child = null;
                my_board.change_state(i + 1, max_player);
                my_board.copy_board(child);

                if (child != null) {
                    optimal_solution val = null;
                    if (child.getAdditional_move_earned() == 1)
                        val = new optimal_solution(child, minimax2(child, depth + 1, alpha,beta,false,heuristic_no, max_player, min_player).getVal());
                    else
                        val = new optimal_solution(child, minimax2(child, depth + 1, alpha, beta, true, heuristic_no, max_player, min_player).getVal());

                    best = getmin(best, val);
                    beta = max(beta, best.getVal());

                    if (beta <= alpha)
                        break;
                }
            }
            return best;
        }
    }






    public optimal_solution getmax(optimal_solution o1, optimal_solution o2)
    {
        if (o1.getB() == null)
            return o2;
        if (o2.getB() == null)
            return o1;
        if (o1.getB() == null && o2.getB() == null)
        {
            System.out.println("all null");
        }
        if (o1.getVal() >= o2.getVal())
            return o1;
        else return o2;
    }

    public optimal_solution getmin(optimal_solution o1, optimal_solution o2)
    {
        if (o1.getB() == null)
            return o2;
        if (o2.getB() == null)
            return o1;
        if (o1.getVal() <= o2.getVal())
            return o1;
        else return o2;
    }

    public int my_move(board b, int heuristic, int max_player, int min_player, int alpha, int beta)
    {
        my_node node = new my_node();
        board b1 = new board();
        b.copy_board(b1);
        node.setB(b1);
        move = -1;
        //minimax(b,0,true,alpha,beta,heuristic,max_player,min_player,node);
        return move;
    }


}
