package Baekjoon;
import java.util.*;
import java.io.*;

public class P9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            boolean flag = false;
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '(') {
                    stack.push(j);
                } else {

                    if (!stack.isEmpty()) {
                        stack.pop();
                    } else {
                        flag = true;
                        break;
                    }
                }
            }

            if (!flag && stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
