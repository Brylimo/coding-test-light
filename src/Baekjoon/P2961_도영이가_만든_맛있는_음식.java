package Baekjoon;
import java.util.*;
import java.io.*;

public class P2961_도영이가_만든_맛있는_음식 {
    static int n;
    static int[] sours;
    static int[] bitters;
    static int ans = Integer.MAX_VALUE;

    static ArrayList<Integer> candidates = new ArrayList<>();

    static void calculate(int current, boolean flag) {
        if (flag) {
            int sourSum = 1;
            int bitterSum = 0;

            boolean used = false;
            for (int i = 0; i < candidates.size(); i++) {
                used = true;

                sourSum *= sours[candidates.get(i)];
                bitterSum += bitters[candidates.get(i)];
            }

            if (used) {
                ans = Math.min(ans, Math.abs(sourSum - bitterSum));
            }
        }

        if (current == n) {
            return;
        }

        candidates.add(current);
        calculate(current + 1, true);
        candidates.remove(candidates.size() - 1);

        calculate(current + 1, false);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        sours = new int[n];
        bitters = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            sours[i] = Integer.parseInt(st.nextToken());
            bitters[i] = Integer.parseInt(st.nextToken());
        }

        calculate(0, true);

        System.out.println(ans);
    }
}
