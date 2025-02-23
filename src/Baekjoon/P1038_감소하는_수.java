package Baekjoon;

import java.util.*;
import java.io.*;

/**
 * P1038_감소하는_수
 * 난이도 3/10
 * 백트래킹
 * 172ms
 *
 * 백트래킹으로 감소하는 수에 해당하는 모든 수를 구하여 저장하고
 * 정렬을 한 뒤 입력으로 주어진 n 값에 맞는 감소하는 수를
 *
 */
public class P1038_감소하는_수 {
    static int n, ans;
    static int[] arr = new int[10];
    static ArrayList<Integer> candidates = new ArrayList<>();
    static ArrayList<String> list = new ArrayList<>();

    static void calculate(int idx, String s) {
        if (candidates.size() == 7) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < candidates.size(); i++) {
                sb.append(candidates.get(i));
            }

            if (sb.toString() != "1000000") {
                return;
            }
        }

        for (int i = 0; i < 10; i++) {

            if (s.length() == 0 || s.charAt(s.length() - 1) - '0' > i) {
                list.add(new String(s + i));
                calculate(idx + 1, new String(s + i));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }

        calculate(0, "");

        ArrayList<Long> alist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            alist.add(Long.parseLong(list.get(i)));
        }
        Collections.sort(alist);

        if (alist.size() <= n) {
            System.out.println(-1);
        } else {
            System.out.println(alist.get(n));
        }
    }
}
