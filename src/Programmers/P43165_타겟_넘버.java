package Programmers;
import java.util.*;

public class P43165_타겟_넘버 {
    public static final int MAX_N = 20;
    public static int n, k, cnt;

    static class Pair {
        int idx, num;

        public Pair(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    public static ArrayList<Pair>[] graph = new ArrayList[MAX_N + 1];

    public static Queue<Pair> queue = new LinkedList<>();

    public void bfs() {
        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            if (current.idx == n - 1 && current.num == k) {
                cnt += 1;
            }

            for (Pair elem : graph[current.idx]) {
                queue.add(new Pair(elem.idx, current.num + elem.num));
                queue.add(new Pair(elem.idx, current.num - elem.num));
            }
        }
    }

    public int solution(int[] numbers, int target) {
        n = numbers.length;
        k = target;

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // graph를 만듦
        for (int i = 0; i < n - 1; i++) {
            graph[i].add(new Pair(i + 1, numbers[i + 1]));
        }

        queue.add(new Pair(0, numbers[0]));
        queue.add(new Pair(0, numbers[0] * (-1)));
        bfs();

        return cnt;
    }
}
