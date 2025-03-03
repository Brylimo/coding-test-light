package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * Main_1799_비숍
 * 난이도 5/10
 * 백트래킹
 * 184ms
 *
 * 흰색과 검정색 칸을 나눠서 생각했다. 흰색과 검정색을 따로 나눠서 생각해도 비숍 문제를 판단하는데 영향을 미치지 않기 때문에
 * 흰색에서 가능한 비숍의 최대 개수를 구하고 검정색에서 가능한 비숍의 최대 개수를 구해서 더해주었다.
 *
 * 비숍의 위치를 판별할 때는 nqueen 문제처럼 \, / 대각선을 배열로 나누어서 해당 배열로 방문 가능한지를 판단하였다.
 *
 */
public class P1799_비숍 {
    static int n, wcnt, bcnt;
    static int[][] grid;
    static int[] di1, di2;
    static ArrayList<int[]> whites = new ArrayList<>();
    static ArrayList<int[]> blacks = new ArrayList<>();

    static void calculate(ArrayList<int[]> loc, int idx, int count, boolean flag) {
        // 종료 조건
        if (idx == loc.size()) {
            return;
        }

        int column = loc.get(idx)[0];
        int row = loc.get(idx)[1];

        if (grid[column][row] == 1 && di1[column + row] == 0 && di2[column - row + n] == 0) { // 갈 수 있는지 여부 판별
            di1[row + column] = 1;
            di2[column - row + n] = 1;

            if (flag) {
                wcnt = Math.max(wcnt, count + 1);
            } else {
                bcnt = Math.max(bcnt, count + 1);
            }
            calculate(loc, idx + 1, count + 1, flag);

            di1[row + column] = 0;
            di2[column - row + n] = 0;
        }

        calculate(loc, idx + 1, count, flag);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];

        di1 = new int[2 * n];
        di2 = new int[2 * n];

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());

                if (grid[i][j] == 1) {
                    if ((i + j) % 2 == 0) { // i + j가 2의 배수이면 흰색
                        whites.add(new int[] {i, j});
                    } else { // i + j가 2의 배수가 아니면 검정색에 집어넣음
                        blacks.add(new int[] {i, j});
                    }
                }
            }
        }

        calculate(whites, 0, 0, true);
        calculate(blacks, 0, 0, false);

        System.out.println(wcnt + bcnt);

        long end = System.nanoTime();
        //System.out.println("실행 시간: " + (end - start) / 1_000_000.0 + " ms");
    }
}
