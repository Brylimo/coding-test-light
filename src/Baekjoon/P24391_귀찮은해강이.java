package Baekjoon;
import java.util.*;
import java.io.*;

public class P24391_귀찮은해강이 {
    static int n, m;
    static int[] arr;
    static int[] parent;

    static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX < parentY) {
            parent[parentY] = parentX;
        } else {
            parent[parentX] = parentY;
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 강의 수
        m = Integer.parseInt(st.nextToken());

        arr = new int[n]; // 강의 순서
        parent = new int[n + 1];

        // parent 배열 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());

            // i번 건물과 j번 건물이 연결되어 있다.
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            union(i, j);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int before = -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int root = find(arr[i]);
            if (!(before == -1 || root == before)) {
                cnt += 1;
            }
            before = root;
        }

        System.out.println(cnt);
    }
}
