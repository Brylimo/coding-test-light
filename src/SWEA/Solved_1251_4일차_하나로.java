package SWEA;
import java.util.*;
import java.io.*;

public class Solved_1251_4일차_하나로 {
    static int n;
    static int[] xArray;
    static int[] yArray;
    static int[] parent;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    static double getDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());

            xArray = new int[n];
            yArray = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                xArray[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                yArray[i] = Integer.parseInt(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());

            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    pq.offer(new Pair(i, j, getDist(xArray[i], yArray[i], xArray[j], yArray[j])));
                }
            }

            // MST
            double result = 0;
            while (!pq.isEmpty()) {
                Pair current = pq.poll();

                if (find(current.one) != find(current.two)) {
                    union(current.one, current.two);
                    result += E * Math.pow(current.dist, 2);
                }
            }

            System.out.printf("#%d %d\n", t, Math.round(result));
            result = 0;
        }
    }

    static class Pair implements Comparable<Pair> {
        int one, two;
        double dist;

        public Pair(int one, int two, double dist) {
            this.one = one;
            this.two = two;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return Double.compare(this.dist, other.dist);
        }
    }
}
