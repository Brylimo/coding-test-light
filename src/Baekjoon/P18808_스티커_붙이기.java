package Baekjoon;
import java.util.*;
import java.io.*;

public class P18808_스티커_붙이기 {
    static int n, m, k;
    static int[][] notebook;
    static ArrayList<Pair> stickers = new ArrayList<>();

    static int[][] rotate(int r, int c, int[][] sticker) {
        int[][] nextSticker = new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                nextSticker[j][r - i - 1] = sticker[i][j];
            }
        }

        return nextSticker;
    }

    static boolean isPossible(int i, int j, int r, int c, int[][] temp) {
        if (i + r > n || j + c > m) return false;

        for (int k = 0; k < r; k++) {
            for (int l = 0; l < c; l++) {
                if (i + k >= 0 && i + k < n && j + l >= 0 && j + l < m && (temp[k][l] == 1 && notebook[i + k][j + l] == 1)) {
                    return false;
                }
            }
        }

        return true;
    }

    static void paste(Pair sticker) { // 붙이는 과정 담당 함수
        int r = sticker.r;
        int c = sticker.c;

        int[][] temp = sticker.sticker;

        outer : for (int a = 0; a < 4; a++) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (isPossible(i, j, r, c, temp)) {
                        // 스티커 붙이기
                        for (int k = 0; k < r; k++) {
                            for (int l = 0; l < c; l++) {
                                if (temp[k][l] == 1 && i + k >= 0 && i + k < n && j + l >= 0 && j + l < m) {
                                    notebook[i + k][j + l] = 1;
                                }
                            }
                        }

                        break outer;
                    }

                }
            }

            // 회전
            temp = rotate(r, c, temp);

            r = temp.length;
            c = temp[0].length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        notebook = new int[n][m];

        for (int i = 0; i < k; i++) { // 스티커 초기화
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < c; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            stickers.add(new Pair(r, c, sticker));
        }

        for (Pair sticker : stickers) { // 스티커 모두 붙임
            paste(sticker);
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (notebook[i][j] == 1) {
                    cnt += 1;
                }
            }
        }

        System.out.println(cnt);
    }

    static class Pair {
        int r;
        int c;
        int[][] sticker;

        public Pair(int r, int c, int[][] sticker) {
            this.r = r;
            this.c = c;
            this.sticker = sticker;
        }
    }
}
