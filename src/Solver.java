
public class Solver {
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {}
    public boolean isSolvable()            // is the initial board solvable?
    {
        return false;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return -1;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        return null;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {}
}
