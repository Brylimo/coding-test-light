package Baekjoon;
import java.util.*;
import java.io.*;

public class P7490_0_만들기 {
    static int n;
    static String[] ops = new String[] { " ", "+", "-" };
    static ArrayList<String> candidates = new ArrayList<>();

    public static void calculate(int idx) {
        if (idx == n - 1) {
            int sum = 1;

            StringBuilder sb = new StringBuilder();

            sb.append("1");
            int temp = 1;
            for (int i = 1; i < n; i++) {
                if (candidates.get(i - 1).equals("+")) {
                    sum += i + 1;

                    temp = i + 1;
                    sb.append("+").append(i + 1);
                } else if (candidates.get(i - 1).equals("-")) {
                    sum -= (i + 1);

                    temp = -(i + 1);
                    sb.append("-").append(i + 1);
                } else if (candidates.get(i - 1).equals(" ")) {
                    // 복구
                    sum -= temp;

                    if (temp < 0) {
                        sum += temp * 10 - (i + 1);

                        temp = temp * 10 - (i + 1);
                    } else {
                        sum += temp * 10 + i + 1;

                        temp = temp * 10 + i + 1;
                    }
                    sb.append(" ").append(i + 1);
                }
            }

            if (sum == 0) {
                System.out.println(sb);
                sb.setLength(0);
            }

            return;
        }

        for (int i = 0; i < 3; i++) {
            candidates.add(ops[i]);
            calculate(idx + 1);
            candidates.remove(candidates.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            calculate(0);
            System.out.println();
        }
    }
}
