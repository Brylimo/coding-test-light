package Baekjoon;
import java.io.*;

public class P15439_베라의_패션 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(n * (n - 1));
    }
}
