package Baekjoon;
import java.util.*;
import java.io.*;

public class P16637_괄호_추가하기 {
    static int n;
    static String input;
    static ArrayList<Integer> candidates = new ArrayList<>();

    static void calculate(int idx, int cnt) {
        if (candidates.size() == cnt) {

        }

        if (idx == n - 1) { // 종료 조건
            if (candidates.size() == cnt) {

            }
            return;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = br.readLine();

        int size = input.length() / 2 + 1;
        int c = size / 2;

        for (int i = 0; i <= c; i++) {
            calculate(0, i);
        }
    }
}
