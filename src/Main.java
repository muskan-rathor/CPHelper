// Main.java (no package)
import cphelper.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        long a = 1000000000L;
        long b = 3000000000L;
        System.out.println("gcd = " + MathUtils.gcd(a, b));
        System.out.println("powMod 2^60 mod 1e9+7 = " + MathUtils.powMod(2, 60, 1_000_000_007L));
        System.out.println("isPrime(17) = " + MathUtils.isPrimeSimple(17));


        int[] arr = {1,2,4,4,5};
        System.out.println("lowerBound of 4: " + SearchSort.lowerBound(arr, 4));
        System.out.println("upperBound of 4: " + SearchSort.upperBound(arr, 4));

        int[][] edges = {{0,1},{1,2},{2,3}};
        List<Integer>[] g = GraphUtils.buildUndirected(4, edges);
        int[] dist = GraphUtils.bfs(g, 0);
        System.out.println("BFS dist from 0: " + Arrays.toString(dist));
    }
}
