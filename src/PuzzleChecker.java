/******************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PuzzleChecker {

    public static void main(String[] args) {

        // for each command-line argument
        test(args[0], 1);
    }
    private static void test(String args0, int times) {
        double time1 = 0;
        double time2 = 0;
        for (int i = 0; i < times; i++) {

            double allTime1 = calculate1(args0);
            time1 += allTime1;
            double allTime2 = calculate2(args0);
            time2 += allTime2;
        }
        time1 = time1 / times;
        time2 = time2 / times;
        
        StdOut.println("All running time1: " + time1);
//        StdOut.println("All running time2: " + time2);
    }
    private static double calculate1(String args0) {
        In inFiles = new In(args0);
        double allTime = 0;
        while (inFiles.hasNextLine()) {
            String filename = "src/" + inFiles.readLine();
            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Stopwatch sw = new Stopwatch();
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);
            double time = sw.elapsedTime();
            allTime += time;
                        StdOut.println(filename + ": " + solver.moves() + ", running time :" + time);
                        System.out.println("------------------------------------");
        }
        return allTime;
    }
    private static double calculate2(String args0) {
        In inFiles = new In(args0);
        double allTime = 0;
        while (inFiles.hasNextLine()) {
            String filename = "src/" + inFiles.readLine();
            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Stopwatch sw = new Stopwatch();
            Board initial = new Board(tiles);
            @SuppressWarnings("unused")
            Solver2 solver = new Solver2(initial);
            double time = sw.elapsedTime();
            allTime += time;
            //            StdOut.println(filename + ": " + solver.moves() + ", running time :" + time);
            //            System.out.println("------------------------------------");
        }
        return allTime;
    }
}
