package Baekjoon;
import java.util.*;
import java.io.*;

public class P2477_참외밭 {
    static int k;
    static final int MAX_N = 500;
    static int[] dx = new int[]{0, 0, 1, -1}; // 동, 서, 남, 북
    static int[] dy = new int[]{1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[MAX_N * 3][MAX_N * 3];

        int sx = MAX_N;
        int sy = MAX_N;

        int[] vertexs = new int[6];

        int idx = 0;

        int maxColumn = 0;
        int maxRow = 0;

        int maxColumnIdx = 0;
        int maxRowIdx = 0;
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            if (dir == 1 || dir == 2) { // 동

                if (length > maxRow) {
                    maxRow = length;
                    maxRowIdx = idx;
                }

            } else { // 북
                vertexs[idx] = length;

                if (length > maxColumn) {
                    maxColumn = length;
                    maxColumnIdx = idx;
                }

            }

            vertexs[idx++] = length;
        }

        int overall = maxRow * maxColumn;

        int subRow = 0;
        // 빠져야할 row 변 길이 구하기
        if (maxColumnIdx == 0) {
            subRow = Math.abs(vertexs[5] - vertexs[1]);
        } else if (maxColumnIdx == 5) {
            subRow = Math.abs(vertexs[4] - vertexs[0]);
        } else {
            subRow = Math.abs(vertexs[maxColumnIdx - 1] - vertexs[maxColumnIdx + 1]);
        }

        int subColumn = 0;
        // 빠져야할 row 변 길이 구하기
        if (maxRowIdx == 0) {
            subColumn = Math.abs(vertexs[5] - vertexs[1]);
        } else if (maxRowIdx == 5) {
            subColumn = Math.abs(vertexs[4] - vertexs[0]);
        } else {
            subColumn = Math.abs(vertexs[maxRowIdx - 1] - vertexs[maxRowIdx + 1]);
        }

        int area = overall - (subRow * subColumn);

        System.out.println(area * k);

    }
}