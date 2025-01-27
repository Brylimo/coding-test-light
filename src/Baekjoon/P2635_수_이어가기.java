package Baekjoon;
import java.util.*;
import java.io.*;

public class P2635_수_이어가기 {
    static int n;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        int max = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            ArrayList<Integer> list = new ArrayList<>();

            list.add(n);
            list.add(i);

            while (true) {
                int temp = list.get(list.size() - 2) - list.get(list.size() - 1);

                if (temp < 0) {
                    break;
                } else {
                    list.add(temp);
                }
            }

            if (max < list.size()) {
                max = list.size();
                ans = list;
            }
        }

        sb.append(max).append("\n");
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
