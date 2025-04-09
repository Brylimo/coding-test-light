package Programmers;
import java.util.*;
import java.io.*;

public class P92345_양과늑대 {
    static class Solution {
        int[] sinfo;
        boolean[] visited;
        boolean[] visited2;
        int n, ans, ecnt = 1;
        ArrayList<Integer>[] graph;
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> candidates = new ArrayList<>();

        public Solution() {

        }

        void dfs(int idx, int lamb, int wolf) {
            for (int node : graph[idx]) {
                if (sinfo[node] == 0) { // 양이면
                    visited[node] = true;
                    dfs(node, lamb + 1, wolf);
                } else { // 늑대이면
                    if (lamb > wolf + 1) {
                        visited[node] = true;
                        dfs(node, lamb, wolf + 1);

                        visited[node] = false;
                        dfs(node, lamb, wolf + 1);
                    } else {
                        visited[node] = false;
                        dfs(node, lamb, wolf + 1);
                    }
                }
            }
        }

        /*void dfs(int idx) {
            for (int node : graph[idx]) {
                if (set.contains(node)) {
                    visited2[node] = true;
                    ecnt += 1;
                    dfs(node);
                }
            }
        }

        void calculate(int idx) {
            if (idx == n) {
                for (int i = 0; i < n; i++) {
                    if (visited[i]) {
                        candidates.add(i);
                    }
                }

                set = new HashSet<>(candidates);
                visited2 = new boolean[n];

                visited2[0] = true;
                dfs(0);

                if (ecnt == candidates.size()) {
                    int wolf = 0;
                    int lamb = 0;

                    for (int i = 0; i < candidates.size(); i++) {
                        if (sinfo[candidates.get(i)] == 0) {
                            lamb += 1;
                        } else {
                            wolf += 1;
                        }
                    }

                    if (lamb > wolf) {
                        ans = Math.max(ans, lamb);
                    }
                }
                ecnt = 1;
                candidates.clear();
                set.clear();

                return;
            }

            visited[idx] = true;
            calculate(idx + 1);

            if (idx != 0) {
                visited[idx] = false;
                calculate(idx + 1);
            }
        }*/

        public int solution(int[] info, int[][] edges) {
            int answer = 0;

            n = info.length;
            sinfo = info;

            visited = new boolean[n];
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
            }

            //calculate(0);
            dfs(0, 1, 0);
            answer = ans;

            return answer;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution s = new Solution();

        int t = s.solution(new int[] {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][] {
                {0, 1},
                {1, 2},
                {1, 4},
                {0, 8},
                {8, 7},
                {9, 10},
                {9, 11},
                {4, 3},
                {6, 5},
                {4, 6},
                {8, 9}
        });

        System.out.println(t);
    }
}
