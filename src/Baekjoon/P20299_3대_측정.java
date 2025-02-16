package Baekjoon;
import java.util.*;
import java.io.*;

public class P20299_3대_측정 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            boolean canGo = true;
            long sum = 0;
            for (int j = 0; j < 3; j++) {
                int current = Integer.parseInt(st.nextToken());

                if (current < l) {
                    canGo = false;
                    break;
                }

                temp.add(current);
                sum += current;
            }

            if (sum < k) {
                canGo = false;
            }

            if (canGo) {
                cnt++;
                list.addAll(temp);
            }

            temp.clear();
        }

        sb.append(cnt).append("\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
