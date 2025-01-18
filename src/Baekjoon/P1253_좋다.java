package Baekjoon;
import java.util.*;
import java.io.*;

public class P1253_좋다 {
    static HashMap<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int ans = 0;
        int n = Integer.parseInt(br.readLine());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1L);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        Arrays.sort(arr); // n 개의 수 정렬

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (map.keySet().contains(arr[i] + arr[j])) {
                    if (arr[i] == 0 && map.get(arr[j]) == 1) {
                        continue;
                    }
                    if (arr[i] == 0 && arr[j] == 0 && map.get(0L) == 2) {
                        continue;
                    }

                    System.out.println(i + " " + j + " " + (arr[i] + arr[j]));
                    if (map.get(arr[i] + arr[j]) == 1) {
                        map.remove(arr[i] + arr[j]);
                    } else {
                        map.put(arr[i] + arr[j], map.get(arr[i] + arr[j]) - 1);
                    }

                    ans += 1;
                }
            }
        }

        System.out.println(ans);
    }
}
