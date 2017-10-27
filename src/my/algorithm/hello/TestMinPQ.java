package my.algorithm.hello;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

public class TestMinPQ {
    public static void main(String[] args) {
        In in = new In(args[0]);
        MinPQ<Integer> pq = new MinPQ<Integer>();
        
        System.out.print("Insert: \t");
        while (in.hasNextLine()) {
            int i = in.readInt();
            pq.insert(i);
            System.out.print(i + "\t");
        }
        System.out.print("\nAfter sort: \t");
        for (Integer i : pq)
            System.out.print(i + "\t");
    }
}
