package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * P1654_랜선_자르기
 * 난이도 2/10
 * 이분탐색
 * 160ms
 *
 * 만들 수 있는 랜선의 길이는 최소 1에서 최대 arr의 최대값이다.
 * 이를 이분탐색의 start와 end로 놓고 이진 탐색을 돌리면서 랜선의 길이를 탐색하고
 * 해당 랜선의 길이로 n개를 만들 수 있으면 기록해주면서 랜선의 최대 길이를 갱신시킨다.
 *
 */
public class P1654_랜선_자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] array = new int[k];
        int max = 0;

        for (int i = 0; i < k; i++) {
            array[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, array[i]);
        }

        long start = 1, end = max; // 최소값 1, 최대값 max
        long result = 0;

        while (start <= end) { // 이분 탐색
            long mid = (start + end) / 2;

            if (mid == 0) { // mid가 0이면 무한루프 방지
                start = mid + 1;
                continue;
            }

            long cnt = 0;
            for (int wire : array) { // array에 있는 랜선들을 mid 길이로 잘라 총 몇 개를 만들 수 있는지 계산.
                cnt += wire / mid;
            }

            if (cnt < n) {
                end = mid - 1;
            } else {
                result = mid;
                start = mid + 1;
            }
        }

        System.out.println(result);
    }
}
