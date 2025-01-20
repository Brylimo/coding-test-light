package Baekjoon;
import java.util.*;
import java.io.*;

public class P2309_일곱_난쟁이 {
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        int[] arr = new int[9];
        for (int i = 0; i < 9; i++) {
            int input = Integer.parseInt(br.readLine());

            list.add(input);
            sum += input;
        }

        System.out.println("eaf " + sum);
        OUT : for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                int vsum = sum;

                vsum -= list.get(i);
                vsum -= list.get(j);

                if (vsum == 100) {
                    list.remove(i);
                    list.remove(j - 1);

                    break OUT;
                }
            }
        }

        Collections.sort(list);

        for (int height : list) {
            System.out.println(height);
        }
    }

}
