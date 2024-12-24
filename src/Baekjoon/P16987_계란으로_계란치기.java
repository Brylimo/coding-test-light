package Baekjoon;
import java.util.*;
import java.io.*;

public class P16987_계란으로_계란치기 {
    static int n, ans; // ans는 인범이가 깰 수 있는 계란의 최대 개수
    static Egg[] eggs;

    static void calculate(int current) { // 백트래킹 처리 함수
        if (current == n) { // 종료 조건
            int cnt = 0; // 인범이가 현재 깬 계란의 최대 개수
            for (int i = 0; i < n; i++) {
                if (eggs[i].s <= 0) {
                    cnt += 1;
                }
            }

            ans = Math.max(ans, cnt);
            return;
        }

        if (eggs[current].s <= 0) {
            calculate(current + 1); // 현재 계란이 깨져 있으면 넘어감
            return;
        }

        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (current == i) continue; // 계란이 자기 자신을 깰 수는 없음

            if (eggs[i].s <= 0) continue; // 이미 깨진 계란을 들었으면 넘어감

            flag = true; // 하나라도 백트래킹이 진행되었으면 true로 flag를 표시한다.

            int cw = eggs[current].w; // 현재 current 인덱스 계란의 무게
            int iw = eggs[i].w; // i 인덱스 계란의 무게

            // 계란으로 깸
            eggs[current].s -= iw;
            eggs[i].s -= cw;

            calculate(current + 1);

            // 원상 복구
            eggs[current].s += iw;
            eggs[i].s += cw;
        }

        if (!flag) { // 백트래킹이 진행되지 않았어도 무조건 다음 계란으로 이동
            calculate(current + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        eggs = new Egg[n]; // 계란을 순서대로 담은 배열

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(s, w);
        }

        calculate(0);

        System.out.println(ans);
    }

    static class Egg {
        int s; // 내구도
        int w; // 무게

        public Egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }
}
