package Baekjoon;
import java.util.*;
import java.io.*;

public class P20922_겹치는_건_싫어 {
    static int[] arr;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int cnt = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && (!map.containsKey(arr[j]) || (map.containsKey(arr[j]) && map.get(arr[j]) < k))) {
                if (!map.containsKey(arr[j])) {
                    map.put(arr[j], 1);
                } else {
                    map.put(arr[j], map.get(arr[j]) + 1);
                }

                cnt += 1;
                j += 1;
            }

            ans = Math.max(ans, cnt);

            cnt -= 1;
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) - 1);
            }
        }

        System.out.println(ans);
    }
}
