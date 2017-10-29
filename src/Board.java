import java.util.Stack;

import edu.princeton.cs.algs4.In;

public class Board {
    //    private final char[] blockz;

    private final int[][] blockz;

    private final int[][] goal;

    private final int n; 

    private final int manhattan;    // cache manhattan value of this broad

    private final int hamming;      // cache hamming value of this broad

    private final int vacancy;            // position of vacancy block in the array blockz  

    private byte lastMoveDir;          // last movement's direction of this board, 1 - 4 means up, down, left, right

    private Stack<Board> neighbors; 
    
    public Board(int[][] blocks)    // construct a board from an n-by-n array of blocks           
    {
        validate(blocks);
        n = blocks.length;
        //        blockz = new char[n * n];
        blockz = new int[n][n];
        goal = new int[n][n];

        {
            int t = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (blocks[i][j] == 0)  t = i * n + j; 
                    //                    blockz[i][j] = (char) (blocks[i][j] + 48);
                    blockz[i][j] = blocks[i][j];
                }
            }
            vacancy = t;
        }
        // can be improved to a method, waiting
        {
            int num  = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == n - 1 && j == n - 1)   break;
                    goal[i][j] = ++num;      // int to char
                }
            }
        }
        lastMoveDir = 0;
        
        manhattan = getManhattan();
        hamming = getHamming();
    }
    public int dimension()                 // board dimension n
    {
        return n;
    }
    public int hamming()                   // number of blocks out of place
    {
        return hamming;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        return manhattan;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        return isArrayEqual(blockz, goal);
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        return null;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == this)  return true;
        if (y == null)  return false;
        if (y.getClass() != this.getClass())    return false;

        Board b = (Board) y;
        return isArrayEqual(this.blockz, b.blockz);
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        if (neighbors == null)  neighbors = getNeighbors();    // lazy loading
        return neighbors;
    }

    private Stack<Board> getNeighbors() {
        Stack<Board> s = new Stack<>();
        Board neighbor;
        int x = vacancy / n;    // axis of vacancy block, like n = 3, 5 --> (1, 2)
        int y = vacancy % n;
        //        System.out.println("start vacancy is " + vacancy);
        if (lastMoveDir != 1 && x != 0) {
            neighbor = generateNeighbor(vacancy - n); // if vacancy block is not in the first row, move upward is available
            s.push(neighbor);
            lastMoveDir = 1;
        }    
        if (lastMoveDir != 2 && x != n - 1) {
            neighbor = generateNeighbor(vacancy + n); // if vacancy block is not in the last row, move downward is available
            s.push(neighbor);
            lastMoveDir = 2;
        }
        if (lastMoveDir != 3 && y != 0) {
            neighbor = generateNeighbor(vacancy - 1); // if vacancy block is not in the first column, move leftward is available
            s.push(neighbor);
            lastMoveDir = 3;
        }
        if (lastMoveDir != 4 && y != n - 1) {
            neighbor = generateNeighbor(vacancy + 1); // if vacancy block is not in the last column, move rightward is available
            s.push(neighbor);
            lastMoveDir = 4;
        }
        return s;
    }
    private Board generateNeighbor(int b) {
        exch(vacancy, b);       // swap entry in vacancy and b 
        Board neighbor = new Board(blockz);
//        neighbor.moves = moves + 1;   // this board moved one step
        exch(b, vacancy);   // swap back for another use
        return neighbor;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blockz[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    private void validate(int[][] blocks) {
        if (blocks == null)
            throw new java.lang.IllegalArgumentException();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j] < 0)
                    throw new java.lang.IllegalArgumentException();
            }
        }
    }
    private int getManhattan() {
        if (isGoal())   return 0;
        int mManhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blockz[i][j] != 0) {

                    // e.g.: 5 is in (0, 0)
                    // 5 - 1 = 4, x = 4 / 3 - 0 = 1, y = 4 % 3 - 0 = 1
                    // so manhattan distance of 5 is 1 + 1 = 2
                    int node = blockz[i][j] - 1;
                    int x = node / 3 - i;        
                    int y = node % 3 - j;

                    int xDistance = x > 0 ? x : -x;     // make sure x distance is positive
                    int yDistance = y > 0 ? y : -y;     // make sure y distance is positive
                    mManhattan += xDistance + yDistance;
                }
            }
        }
        return mManhattan;
    }
    private int getHamming() {
        if (isGoal())   return 0;
        int mHamming = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blockz[i][j] != 0 && blockz[i][j] != goal[i][j])    mHamming++;
            }
        }
        return mHamming;
    }
    /**
     * this method is just for verifying whether 2 arrays is equal, we **don't** offer validation
     * for these two's length
     */
    private boolean isArrayEqual(int[][] a, int[][] b) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j])    return false;
            }
        }
        return true;
    }
    private void exch(int a, int b) {
        int t = blockz[a / n][a % n];
        blockz[a / n][a % n] = blockz[b / n][b % n];
        blockz[b / n][b % n] = t;
    }
    public static void main(String[] args) // unit tests (not graded)
    {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board board = new Board(blocks);
        //        for (int i = 0; i < blocks.length; i++) {
        //            for (int j = 0; j < blocks[0].length; j++) {
        //                System.out.print(board.blockz[i][j] + "\t");
        //            }
        //            System.out.println();
        //        }
                System.out.println(board);
        //
        //        System.out.println("Hamming solution is " + board.hamming() + 
        //                "\nManhattan solution is " + board.manhattan() + 
        //                "\nvacancy position is " + board.vacancy);
        //        System.out.println("This array is goal? " + board.isGoal());
        //
        //        System.out.println("This board's neighbors:");
        //        for (Board b : board.neighbors())
        //            System.out.println(b);
        Iterable<Board> it = board.getNeighbors();
        for (Board b : it)
            System.out.println(b);
    }
}
