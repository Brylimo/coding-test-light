package Baekjoon;
import java.util.*;
import java.io.*;

public class P1011_Fly_me_to_the_Alpha_Centauri {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int dist = Math.abs(y - x);

            int k = (int)Math.sqrt(dist);
            int ans = 2 * k - 1;

            if (dist - Math.pow(k, 2) > 0) {
                ans += Math.ceil((dist - Math.pow(k, 2)) / k);
            }

            System.out.println(ans);
        }
    }
}
