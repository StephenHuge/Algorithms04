import edu.princeton.cs.algs4.In;

public class Board {
    private final int[][] blockz;
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)           
    {
        validate(blocks);
        blockz = new int[blocks.length][blocks[0].length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blockz[i][j] = blocks[i][j];
            }
        }
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
    public int dimension()                 // board dimension n
    {
        return blockz.length;
    }
    public int hamming()                   // number of blocks out of place
    {
        return -1;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        return -1;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        return false;
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
        StringBuilder sb = new StringBuilder();
        sb.append(dimension() + "\n");
        for (int i = 0; i < blockz.length; i++) {
            for (int j = 0; j < blockz[0].length; j++) {
                sb.append(" " + blockz[i][j]);
            }
            sb.append("\n");
        }
        return new String(sb);
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
    }
}
