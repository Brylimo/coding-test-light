package Baekjoon;
import java.util.*;
import java.io.*;

public class P14696_딱지_놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int[] aInfo = new int[5];
            int[] bInfo = new int[5];

            // A의 정보 구하기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a; j++) {
                int shape = Integer.parseInt(st.nextToken());
                aInfo[shape] += 1;
            }

            // B의 정보 구하기
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a; j++) {
                int shape = Integer.parseInt(st.nextToken());
                bInfo[shape] += 1;
            }

            // 결과 판단
            if (aInfo[4] != bInfo[4]) { // 별의 개수
                if (aInfo[4] > bInfo[4]) {
                    System.out.println("A");
                } else {
                    System.out.println("B");
                }
            } else if (aInfo[3] != bInfo[3]) {
                if (aInfo[3] > bInfo[3]) {
                    System.out.println("A");
                } else {
                    System.out.println("B");
                }
            } else if (aInfo[2] != bInfo[2]) {
                if (aInfo[2] > bInfo[2]) {
                    System.out.println("A");
                } else {
                    System.out.println("B");
                }
            } else if (aInfo[1] != bInfo[1]) {
                if (aInfo[1] > bInfo[1]) {
                    System.out.println("A");
                } else {
                    System.out.println("B");
                }
            } else {
                System.out.println("D");
            }
        }
    }
}
