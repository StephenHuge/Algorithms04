import java.util.Comparator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private int moves = 0;

    private boolean solvable = false;

    private final Stack<Board> solution;

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if (initial == null) throw new java.lang.IllegalArgumentException();
        
        moves = 0;
        MinPQ<Priority> minPQ = new MinPQ<>(new PriorityComparator());
        Priority min = new Priority(initial, 0, null);
        minPQ.insert(min);      // min is not the ans, insert it
        solution = new Stack<>();

        min = solve(minPQ, min, true);
        if (!solvable) {
            min = solve(minPQ, new Priority(initial.twin(), 0, null), false);
        }

        Priority sol = min;
        while (sol != null) {       // get solution
            solution.push(sol.board);
            sol = sol.father;
        }
    }
    private Priority solve(MinPQ<Priority> minPQ, Priority min, boolean mSolvable) {
        int count = 0;
        while (true) {
            count ++;
            if (count > 9999999)   break;
            min = minPQ.delMin();   // find the smallest one
            //            System.out.println(min.board);
            //            System.out.println("priority: " + min.getPriority() +
            //                    ", manhattan: " + min.board.manhattan() +
            //                    ", moves: " +min.moves + " ");
            if (min.board.isGoal()) {   // get ans
                solvable = mSolvable;
                moves = min.moves;
                break;
            } 

            /*******************insert neighbors*************************/
            Iterable<Board> it = min.board.neighbors(); // get smallest one's neighbors
            for (Board b : it) {
                if (!b.equals(min.board))   minPQ.insert(new Priority(b, min.moves + 1, min));
            }
            /*******************insert neighbors*************************/
        }
        System.out.println("count is : " + count);
        return min;
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
        return solvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return moves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        return solution;
    }
    private class PriorityComparator implements Comparator<Priority> {

        @Override
        public int compare(Priority p1, Priority p2) {
            if (p1.getPriority() < p2.getPriority())    return -1;
            if (p1.getPriority() > p2.getPriority())    return 1;
            else {
                if (p1.board.manhattan() < p2.board.manhattan())    return -1;
                if (p1.board.manhattan() > p2.board.manhattan())    return 1;
                return 0;
            }
        }

    }
    private class Priority {
        Board board;
        int moves;
        int priority;
        Priority father;
        Priority(Board mBoard, int mMoves, Priority mFather) {
            this.board = mBoard;
            this.moves = mMoves;
            this.priority = board.manhattan() + moves;
            this.father = mFather;
        }
        int getPriority() {
            return priority; 
        }
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
