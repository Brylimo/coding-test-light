package SsafyHomework;

public class Array10 {
    private static int R = 4, C = 5;
    private static char[][] map;
    private static StringBuilder output = new StringBuilder();

    private static void setup() {
        map = new char[R][C];
        // TODO: 위 배열을 알파벳 대문자 A ~ T로 초기화 하시오.

        int alpha = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = (char)(alpha + 'A');
                alpha++;
            }
        }

        // END
    }

    public static void main(String[] args) {
        setup();
        rowFirst();
        colFirst();
        zigzag();
        snail();
    }

    private static void rowFirst() {
        output = new StringBuilder();

        // TODO: 행우선 탐색을 작성하시오.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                output.append(map[i][j]);
            }
        }

        // END
        System.out.println(output.toString().equals("ABCDEFGHIJKLMNOPQRST"));
    }

    private static void colFirst() {
        output = new StringBuilder();

        // TODO: 열우선 탐색을 작성하시오.
        for (int j = 0; j < C; j++) {
            for (int i = 0; i < R; i++) {
                output.append(map[i][j]);
            }
        }

        // END
        System.out.println(output.toString().equals("AFKPBGLQCHMRDINSEJOT"));
    }

    private static void zigzag() {
        output = new StringBuilder();
        // TODO: zigzag 탐색을 작성하시오.
        for (int i = 0; i < R; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < C; j++) {
                    output.append(map[i][j]);
                }
            } else {
                for (int j = C - 1; j >= 0; j--) {
                    output.append(map[i][j]);
                }
            }
        }


        // END
        System.out.println(output.toString().equals("ABCDEJIHGFKLMNOTSRQP"));
    }

    private static void snail() {
        output = new StringBuilder();

        int[] dx = new int[] {0, 1, 0, -1}; // 오하좌상
        int[] dy = new int[] {1, 0, -1, 0};

        boolean[][] visited = new boolean[R][C];

        // TODO: 달팽이 탐색을 작성하시오.

        int sx = 0, sy = 0, dir = 0, cnt = 0;
        while (true) {
            visited[sx][sy] = true;
            output.append(map[sx][sy]);

            int nx = sx + dx[dir];
            int ny = sy + dy[dir];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) {
                dir = (dir + 1) % 4; // 시계방향으로 한칸 방향 바꿈
                nx = sx + dx[dir];
                ny = sy + dy[dir];
            }

            sx = nx;
            sy = ny;

            cnt += 1;
            if (cnt == R * C) break;
        }

        // END
        System.out.println(output.toString().equals("ABCDEJOTSRQPKFGHINML"));
    }

    private static boolean isIn(int r, int c) {
        return 0<=r && r<R && 0<=c && c<C;
    }
}
