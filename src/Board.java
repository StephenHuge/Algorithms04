import edu.princeton.cs.algs4.In;

public class Board {
    private final int[][] blockz;

    private final int[][] goal;
    
    private final int n; 

    public Board(int[][] blocks)    // construct a board from an n-by-n array of blocks           
    {
        validate(blocks);
        n = blocks.length;
        blockz = new int[n][n];
        int num  = 0;
        goal = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blockz[i][j] = blocks[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1)   break;
                goal[i][j] = ++num;
            }
        }
    }

    public int dimension()                 // board dimension n
    {
        return n;
    }
    public int hamming()                   // number of blocks out of place
    {
        if (isGoal())   return 0;
        int hamming = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blockz[i][j] != 0 && blockz[i][j] != goal[i][j])    hamming++;
            }
        }
        return hamming;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        if (isGoal())   return 0;
        int manhattan = 0;
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
                    manhattan += xDistance + yDistance;
                }
            }
        }
        return manhattan;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blockz[i][j] != goal[i][j])    return false;
            }
        }
        return true;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        return null;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        return false;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        return null;
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
    public static void main(String[] args) // unit tests (not graded)
    {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blocks[i][j] = in.readInt();
                //                System.out.print(blocks[i][j] + "\t");
            }
            //            System.out.println();
        }
        Board board = new Board(blocks);
        //        for (int i = 0; i < blocks.length; i++) {
        //            for (int j = 0; j < blocks[0].length; j++) {
        //                System.out.print(board.blockz[i][j] + "\t");
        //            }
        //            System.out.println();
        //        }
                System.out.println(board);

        System.out.println("Hamming solution is " + board.hamming() + 
                "\nManhattan solution is " + board.manhattan());
        System.out.println("This array is goal? " + board.isGoal());
    }
}
