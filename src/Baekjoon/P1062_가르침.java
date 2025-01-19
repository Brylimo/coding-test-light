package Baekjoon;
import java.util.*;
import java.io.*;

public class P1062_가르침 {
    static int n, k, snum, ans;
    static char[] remnant = new char[] {'b', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'o', 'p', 'q', 'r', 's', 'u', 'v', 'w', 'x', 'y', 'z'};
    static String[] words;
    static ArrayList<Character> candidates = new ArrayList<>();

    static void calculate(int current) {
        if (k - 5 >= 0 && candidates.size() == k - 5) {
            int num = snum;

            // 비트마스킹을 계산
            for (int i = 0; i < candidates.size(); i++) {
                num |= (1 << (candidates.get(i) - 'a'));
            }

            // 전체 순회하며 확인
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                int tnum = 0;

                String s = words[i];

                for (int j = 0; j < s.length(); j++) {
                    tnum |= (1 << (s.charAt(j) - 'a'));
                }

                if (Integer.bitCount(tnum) <= k && ((tnum & num) == tnum)) {
                    cnt += 1;
                }
            }

            ans = Math.max(ans, cnt);
            return;
        }

        if (current == 21) {
            if (k - 5 > 0 && candidates.size() == k - 5) {
                int num = snum;

                // 비트마스킹을 계산
                for (int i = 0; i < candidates.size(); i++) {
                    num |= (1 << (candidates.get(i) - 'a'));
                }

                // 전체 순회하며 확인
                int cnt = 0;
                for (int i = 0; i < n; i++) {
                    int tnum = 0;
                    String s = words[i];

                    for (int j = 0; j < s.length(); j++) {
                        tnum |= (1 << (s.charAt(j) - 'a'));
                    }

                    if (Integer.bitCount(tnum) <= k && ((tnum & num) == tnum)) {
                        cnt += 1;
                    }
                }

                ans = Math.max(ans, cnt);
            }

            return;
        }

        candidates.add(remnant[current]);
        calculate(current + 1);
        candidates.remove(candidates.size() - 1);

        calculate(current + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        snum = 0;
        char[] essential = new char[]{'a', 'c', 'i', 'n', 't'};
        words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        // bit 합치기
        for (int i = 0; i < 5; i++) {
            snum |= (1 << (essential[i] - 'a'));
        }

        calculate(0);

        System.out.println(ans);
    }
}
