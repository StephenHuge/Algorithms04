public class Board {
    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
    {}
    public int dimension()                 // board dimension n
    {
        return -1;
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
        return null;
    }

    public static void main(String[] args) // unit tests (not graded)
    {}
}
/*private final char[] blockz;

private final char[] goal;

private final int n; 

private final int manhattan;    // cache manhattan value of this broad

private final int hamming;      // cache hamming value of this broad

private int vacancy;            // position of vacancy block in the array blockz  

private byte lastMoveDir;          // last movement's direction of this board, 1 - 4 means up, right, down, left 

public Board(int[][] blocks)    // construct a board from an n-by-n array of blocks           
{
    validate(blocks);
    n = blocks.length;
    blockz = new char[n * n];
    int num  = 0;
    goal = new char[n * n];
    lastMoveDir = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (blocks[i][j] == 0)  vacancy = i * n + j; 
            blockz[i * n + j] = (char) (blocks[i][j] + 48);
        }
    }
    // can be improved to a method, waiting  
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == n - 1 && j == n - 1)   break;
            goal[i * n + j] = (char) (++num + 48);
        }
    }
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
    Stack<Board> mNeighbors = new Stack<>();
    
    int x = vacancy / n;    // axis of vacancy block, like n = 3, 5 --> (1, 2)
    int y = vacancy % n;
    
    if (x != 0) {       // if vacancy block is not in the first row, move upward is available
        exch(vacancy, vacancy - n);
        mNeighbors.push(this);
    }
    if (x != n - 1)  {  // if vacancy block is not in the last row, move downward is available
        exch(vacancy, vacancy + n);
        mNeighbors.push(this);
    }
    if (y != 0) {       // if vacancy block is not in the first column, move leftward is available
        exch(vacancy, vacancy - 1);
        mNeighbors.push(this);
    }
    if (y != n - 1)  {  // if vacancy block is not in the last column, move rightward is available
        exch(vacancy, vacancy + 1);
        mNeighbors.push(this);
    }
    return mNeighbors;
}
public String toString()               // string representation of this board (in the output format specified below)
{
    StringBuilder s = new StringBuilder();
    s.append(n + "\n");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            s.append(String.format("%2c ", blockz[i * n + j]));
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
            if (blockz[i * n + j] != '0') {

                // e.g.: 5 is in (0, 0)
                // 5 - 1 = 4, x = 4 / 3 - 0 = 1, y = 4 % 3 - 0 = 1
                // so manhattan distance of 5 is 1 + 1 = 2
                int node = blockz[i * n + j] - 48 - 1;
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
            if (blockz[i * n + j] != '0' && blockz[i * n + j] != goal[i * n + j])    mHamming++;
        }
    }
    return mHamming;
}
*//**
 * this method is just for verifying whether 2 arrays is equal, we **don't** offer validation
 * for these two's length
 *//*
private boolean isArrayEqual(char[] a, char[] b) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i * n + j] != b[i * n + j])    return false;
        }
    }
    return true;
}
private void exch(int a, int b) {
    char t = blockz[a];
    blockz[a] = blockz[b];
    blockz[b] = t;
}
public static void main(String[] args) // unit tests (not graded)
{
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];

    for (int i = 0; i < blocks.length; i++) {
        for (int j = 0; j < blocks[0].length; j++) {
            blocks[i][j] = in.readInt();
            //                                        System.out.print(blocks[i][j] + "\t");
        }
        //                                System.out.println();
    }
    Board board = new Board(blocks);
    for (int i = 0; i < blocks.length; i++) {
        for (int j = 0; j < blocks[0].length; j++) {
            System.out.print(board.blockz[i * n + j] + "\t");
        }
        System.out.println();
    }
    System.out.println(board);

    System.out.println("Hamming solution is " + board.hamming() + 
            "\nManhattan solution is " + board.manhattan() + 
            "\nvacancy position is " + board.vacancy);
    System.out.println("This array is goal? " + board.isGoal());
}
*/