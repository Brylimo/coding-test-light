package Baekjoon;
import java.util.*;
import java.io.*;

public class P3151_합이0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 오름차순으로 정렬

        long cnt = 0; // 그녀가 고를 수 있는 팀의 수
        for (int i = 0; i < n; i++) { // 앞에서부터 하나씩 고름
            int target = -arr[i]; // 합이 target이 되어야 함

            // two pointer
            int x = i + 1;
            int y = n - 1;

            while (x < y) {
                if (arr[x] + arr[y] == target) {
                    if (arr[x] == arr[y]) { // 왼쪽과 오른쪽이 같음
                        int len = y - x + 1;
                        cnt += (long) len * (len - 1) / 2; // 조합 공식
                        break; // 그냥 빠져나옴
                    } else { // 왼쪽과 오른쪽이 서로 다름
                        int leftCount = 1;
                        int rightCount = 1;

                        while (x + 1 < y && arr[x] == arr[x + 1]) {
                            x++;
                            leftCount++;
                        }

                        while (y - 1 > x && arr[y] == arr[y - 1]) {
                            y--;
                            rightCount++;
                        }

                        cnt += (long) leftCount * rightCount;
                        x++;
                        y--;
                    }
                } else if (arr[x] + arr[y] < target) {
                    x++; // x를 증가시킨다
                } else {
                    y--; // y를 감소시킨다
                }
            }

        }

        System.out.println(cnt);
    }
}
