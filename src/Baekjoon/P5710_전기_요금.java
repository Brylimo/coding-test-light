package Baekjoon;
import java.util.*;
import java.io.*;

public class P5710_전기_요금 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()); // 사용량을 합쳤을 때 요금
            int b = Integer.parseInt(st.nextToken()); // 이웃의 전기 요금과의 차이

            if (a == 0 && b == 0) break; // 종료 조건

            long total = convertToWh(a); // 사용량
            long start = 0;
            long end = total / 2; // 사용량

            while (start <= end) {
                long mid = (start + end) / 2; // 상근이 사용량

                long target = convertToCost(mid); // 상근
                long other = convertToCost(total - mid); // 이웃

                long res = Math.abs(other - target); // b에 대응

                if (res == b) {
                    sb.append(target).append("\n");
                    break;
                } else if (res < b) { // 상근이 줄여줘야 함
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        System.out.println(sb);
    }

    static long convertToWh(long a) {
        long tmp = a; // 요금
        long used = 0; // 사용량

        if (tmp <= 200) { // 1번째에서 끝
            used = tmp / 2;
        } else {
            used += 100;
            tmp -= 200;

            if (tmp <= 9900 * 3) { // 2번째에서 끝
                used += tmp / 3;
            } else {
                used += 9900;
                tmp -= 9900 * 3;

                if (tmp < 990000 * 5) { // 3번째에서 끝
                    used += tmp / 5;
                } else {
                    used += 990000;
                    tmp -= 990000 * 5;

                    // 그래도 또 있다면
                    used += tmp / 7;
                }
            }
        }

        return used;
    }

    static long convertToCost(long x) {
        if(1 <= x && x <= 100) {
            return 2 * x;
        }else if(101 <= x && x <= 10000) {
            return 200 + 3 * (x - 100);
        }else if(10001 <= x && x <= 1000000) {
            return 200 + (9900 * 3) + 5 * (x - 10000);
        }else if(x > 1000000) {
            return 200 + (9900 * 3) + (5 * 990000) + (7 * (x - 1000000));
        }

        return 0;
    }
}
