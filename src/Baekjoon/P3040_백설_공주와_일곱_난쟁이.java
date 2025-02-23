package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * P3040_백설_공주와_일곱_난쟁이
 * 난이도 1/10
 * 백트래킹
 * 136ms
 *
 * 백트래킹으로 일곱 난쟁이가 될 수 있는 모든 조합을 구하고 각 경우의 수마다 전체 합이 100이 되는지를 확인하여
 * 100이 되면 print하였다.
 *
 */
public class P3040_백설_공주와_일곱_난쟁이 {
    static ArrayList<Integer> candidates = new ArrayList<>();
    static int[] arr;

    static void calculate(int idx) {
        if (candidates.size() == 7) { // 종료 조건
            int sum = 0;
            for (int i = 0; i < candidates.size(); i++) {
                sum += candidates.get(i);
            }

            if (sum == 100) {
                for (int i = 0; i < 7; i++) {
                    System.out.println(candidates.get(i));
                }
                System.exit(0);
            }

            return;
        }

        if (idx == 9) { // 종료 조건
            if (candidates.size() == 7) {
                int sum = 0;
                for (int i = 0; i < candidates.size(); i++) {
                    sum += candidates.get(i);
                }

                if (sum == 100) {
                    for (int i = 0; i < 7; i++) {
                        System.out.println(candidates.get(i));
                    }
                    System.exit(0);
                }
            }
            return;
        }

        // 유도 파트
        candidates.add(arr[idx]);
        calculate(idx + 1);
        candidates.remove(candidates.size() - 1);

        calculate(idx + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[9];
        for (int i = 0; i < 9; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        calculate(0);
    }
}
