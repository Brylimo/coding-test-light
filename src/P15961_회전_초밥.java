import java.util.*;
import java.io.*;

public class P15961_회전_초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] array = new int[2 * n];
        for (int i = 0; i < n; i++) {
            int kind = Integer.parseInt(br.readLine());

            array[i] = kind;
            array[i + n] = kind;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(c, 1); // 쿠폰을 집어넣음
        for (int i = 0; i < k; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, map.keySet().size());

            if (map.containsKey(array[i])) {
                if (map.get(array[i]) == 1) {
                    map.remove(array[i]);
                } else {
                    map.put(array[i], map.get(array[i]) - 1);
                }
            }

            if (i < n) {
                if (map.containsKey(array[i + k])) {
                    map.put(array[i + k], map.get(array[i + k]) + 1);
                } else {
                    map.put(array[i + k], 1);
                }
            }
        }

        System.out.println(ans);
    }
}