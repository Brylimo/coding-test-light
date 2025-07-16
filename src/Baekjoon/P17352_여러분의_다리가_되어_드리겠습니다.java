package Baekjoon;
import java.util.*;
import java.io.*;

public class P17352_여러분의_다리가_되어_드리겠습니다 {
    static int n;
    static int[] parents;

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px < py) {
            parents[py] = px;
        } else {
            parents[px] = py;
        }
    }

    static int find(int x) {
        if (parents[x] == x) return x;

        return parents[x] = find(parents[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        // parents 배열 초기화
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < n - 2; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // x와 y는 연결되어 있음
            union(x, y);
        }

        // n까지 하나씩 진행하면서 연결되어 있나 살펴봄
        for (int i = 1; i < n; i++) {
            if (find(i) != find(i + 1)) { // 현재 idx와 다음 idx를 비교해봄
                // 연결되어 있음
                System.out.println(i + " " + (i + 1));
                break;
            }
        }

    }
}
