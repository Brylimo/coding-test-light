package Baekjoon;
import java.util.*;
import java.io.*;

public class P1244_스위치_켜고_끄기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] states = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            states[i] = Integer.parseInt(st.nextToken());
        }

        int sNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < sNum; i++) {
            st = new StringTokenizer(br.readLine());

            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (gender == 1) { // 남자
                for (int j = 1; j <= n; j++) {
                    if (num * j <= n && states[num * j] == 0) {
                        states[num * j] = 1;
                    } else if (num * j <= n && states[num * j] == 1) {
                        states[num * j] = 0;
                    }
                }

            } else if (gender == 2) { // 여자
                int pointer1 = 0, pointer2 = 0;

                if (states[num] == 0) {
                    states[num] = 1;
                } else if (states[num] == 1) {
                    states[num] = 0;
                }

                while (true) {
                    pointer1--;
                    pointer2++;

                    if (num + pointer1 > 0 && num + pointer2 < n + 1 && states[num + pointer1] == states[num + pointer2]) {
                        if (states[num + pointer1] == 1) {
                            states[num + pointer1] = 0;
                            states[num + pointer2] = 0;
                        } else {
                            states[num + pointer1] = 1;
                            states[num + pointer2] = 1;
                        }
                    } else {
                        break;
                    }
                }
            }

        }

        for (int i = 1; i < states.length; i++) {
            if (i % 20 == 0) {
                sb.append(states[i]);
                sb.append("\n");
            } else {
                sb.append(states[i]).append(" ");
            }
        }

        System.out.println(sb);
    }
}
