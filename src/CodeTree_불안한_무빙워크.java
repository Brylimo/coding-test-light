import java.util.*;
import java.io.*;

public class CodeTree_불안한_무빙워크 {
    public static int n, k;
    public static Pair[] belt1, belt2;

    static class Pair {
        int idx, energy;
        boolean occupied;

        public Pair(int idx, int energy, boolean occupied) {
            this.idx = idx;
            this.energy = energy;
            this.occupied = occupied;
        }
    }

    public static void simulate() {
        // 무빙워크 한칸 회전
        Pair temp1 = belt1[n - 1];
        for (int i = n - 1; i > 0; i--) {
            belt1[i] = belt1[i - 1];
        }

        Pair temp2 = belt2[n - 1];
        for (int i = n - 1; i > 0; i--) {
            belt2[i] = belt2[i - 1];
        }

        belt1[0] = temp2;
        belt2[0] = temp1;

        if (belt1[n - 1].occupied) {
            belt1[n - 1].occupied = false;
        }

        // 사람 이동
        for (int i = n - 1; i >= 0; i--) {
            if (belt1[i].occupied && i + 1 < n && belt1[i + 1].energy > 0 && !belt1[i + 1].occupied) {
                if (i + 1 < n - 1) {
                    belt1[i + 1].occupied = true;
                }
                belt1[i + 1].energy -= 1;

                belt1[i].occupied = false;
            }
        }

        // 사람 한명 올림
        if (!belt1[0].occupied && belt1[0].energy > 0) {
            belt1[0].energy -= 1;
            belt1[0].occupied = true;
        }
    }

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        belt1 = new Pair[n];
        belt2 = new Pair[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            belt1[i] = new Pair(i + 1, Integer.parseInt(st.nextToken()), false);
        }
        for (int i = 0; i < n; i++) {
            belt2[i] = new Pair(n + i + 1, Integer.parseInt(st.nextToken()), false);
        }

        int cnt = 0;
        while (true) {
            cnt += 1;
            simulate();

            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (belt1[i].energy == 0) sum += 1;
                if (belt2[i].energy == 0) sum += 1;
            }

            if (sum >= k) break;
        }

        System.out.println(cnt);
    }
}