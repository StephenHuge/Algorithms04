import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
/**
 * @author evasean www.cnblogs.com/evasean/
 */
public class Solver2 {
    
    private Priority min;
    private Priority twin;
    private Stack<Board> solution;
    
    private class Priority implements Comparable<Priority>{
        public Board board;
        public int moves;
        public Priority father;
        
        public final int priority;
        
        public Priority(Board inboard, Priority inPrePriority){
            board = inboard;
            father = inPrePriority;
            if(inPrePriority == null) moves = 0;    
            else moves = inPrePriority.moves + 1;
            priority = moves + board.manhattan();
        }

        @Override
        public int compareTo(Priority o) {
            return Integer.compare(this.priority, o.priority);
        }
    }
    
    
    public Solver2(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        if(initial == null)
            throw new IllegalArgumentException("Constructor argument Board is null!");
        
        min = new Priority(initial,null);                // 两个新建节点
        twin = new Priority(initial.twin(),null);
        
        MinPQ<Priority> minPQ = new MinPQ<Priority>();
        MinPQ<Priority> twinPQ = new MinPQ<Priority>();
        
        minPQ.insert(min);
        twinPQ.insert(twin);
        
        while(true){
            min = minPQ.delMin();
            if(min.board.isGoal()) break;
            putNeighBorsIntoPQ(min,minPQ);
            
            twin = twinPQ.delMin();
            if(twin.board.isGoal()) break;
            putNeighBorsIntoPQ(twin,twinPQ);
        }
    }
    
    private void putNeighBorsIntoPQ(Priority searchNode, MinPQ<Priority> pq){
        Iterable<Board> neighbors = searchNode.board.neighbors();
        for(Board neighbor : neighbors){
            //只有在当前搜索节点的邻居们的borad不与当前节点的father的borad相同
            //才将该邻居放入优先队列
            
            if(searchNode.father==null || !neighbor.equals(searchNode.father.board))
                pq.insert(new Priority(neighbor,searchNode));
        }
    }

    public boolean isSolvable() {
        // is the initial board solvable?
        return min.board.isGoal();
    }

    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        if(min.board.isGoal())
            return min.moves;
        else
            return -1;
    }

    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if(min.board.isGoal()){
            solution = new Stack<Board>();
            Priority node = min;
            while(node != null){
                solution.push(node.board);
                node = node.father;
            }
            return solution;
        }else
            return null;
    }

    public static void main(String[] args) {
        // solve a slider puzzle (given below)
        // create initial board from file
//        In in = new In(args[0]);
        In in = new In("8puzzle/puzzle3x3-unsolvable.txt"); //本地测试之用
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