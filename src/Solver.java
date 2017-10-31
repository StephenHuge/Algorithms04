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
        Priority min = new Priority(initial, null);
        Priority twin = new Priority(initial.twin(), null);

        MinPQ<Priority> minPQ = new MinPQ<>();
        MinPQ<Priority> twinPQ = new MinPQ<>();

        minPQ.insert(min);      // insert min
        twinPQ.insert(twin);      
        Priority sol = solve(minPQ, twinPQ);

        
        /* *****************solution*********************** */
        solution = new Stack<>();
        while (sol != null) {       // get solution
            solution.push(sol.board);
            sol = sol.father;
        }
    }
    private Priority solve(MinPQ<Priority> minPQ, MinPQ<Priority> twinPQ) {
        Priority min;
        Priority twin;
        while (true) {
            min = minPQ.delMin();   // find the smallest one
            twin = twinPQ.delMin();   // find the smallest one

//                        System.out.println(min.board);
//                        System.out.println("priority: " + min.getPriority() +
//                                ", manhattan: " + min.board.manhattan() +
//                                ", moves: " +min.moves + " ");
                        
            if (min.board.isGoal()) {   // solvable
                solvable = true;
                moves = min.moves;
                break;
            } 
            if (twin.board.isGoal()) {   // unsolvable
                moves = -1;
                break;
            } 

            /*******************insert neighbors*************************/
            Iterable<Board> it = min.board.neighbors(); // get smallest one's neighbors
            for (Board b : it) {
                if (!b.equals(min.board))   minPQ.insert(new Priority(b, min));
            }
            it = twin.board.neighbors(); // get smallest one's neighbors
            for (Board b : it) {
                if (!b.equals(twin.board))   twinPQ.insert(new Priority(b, twin));
            }
            /*******************insert neighbors*************************/
        }
        if (solvable)   return min;
        return null;
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
        return solvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if (!solvable)  return -1;
        return moves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (!solvable)  return null;
        return solution;
    }
    private class Priority implements Comparable<Priority> {
        Board board;
        int moves;
        int manhattan;
        Priority father;
        
        Priority(Board mBoard, Priority mFather) {
            this.board = mBoard;
            if (mFather == null)    this.moves = 0;
            else                    this.moves = mFather.moves + 1;
            this.manhattan = board.manhattan(); 
            this.father = mFather;
        }
        int getPriority() {
            return manhattan + moves; 
        }
        @Override
        public int compareTo(Priority p) {
            if (getPriority() < p.getPriority())    return -1;
            if (getPriority() > p.getPriority())    return 1;
            else {
                if (manhattan < p.manhattan)    return -1;
                if (manhattan > p.manhattan)    return 1;
            }
            return 0;
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
//            for (Board board : solver.solution())
//                StdOut.println(board);
        }
    }
}
