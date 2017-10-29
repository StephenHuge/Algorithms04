import java.util.Comparator;
import java.util.Stack;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private Stack<Board> solution;
    private int moves;
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        solution = new Stack<>();
        MinPQ<Board> minPQ = new MinPQ<>(new Comparator<Board>() {
            @Override
            public int compare(Board b1, Board b2) {
                //                if (b1.manhattan() + b1.moves < b2.manhattan() + b2.moves)    return -1;
                //                if (b1.manhattan() + b1.moves > b2.manhattan() + b2.moves)    return 1;
                //                else {
                //                    if (b1.manhattan() < b2.manhattan())    return -1;
                //                    if (b1.manhattan() > b2.manhattan())    return 1;
                return 0;
                //                }
            }

        });
        minPQ.insert(initial);
        solution.push(initial);
        //        System.err.println("init is : " + initial);
        Board board = minPQ.delMin();
        Iterable<Board> it;
        int count = 0;
        while (!board.isGoal()) {
            count++;
            it = board.neighbors();
            for (Board b : it) {
                minPQ.insert(b);
            }
            board = minPQ.delMin();
            //            if (board.manhattan() + board.moves <= solution.peek().manhattan() + board.moves) {
            //                                System.out.println(String.format("priority is %d, manhattan is %d " + 
            //                                        "moves is %d \n" +   
            //                                        "insert min: \n%s ", 
            //                                        board.manhattan() + board.moves(), board.manhattan(), board.moves(), board.toString()));

            solution.push(board);
        }
        //        }
        //        moves = board.moves;
        System.out.println("count is " + count);
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
        return false;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return moves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        return solution;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {}
}
