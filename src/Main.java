import java.util.*;
import java.io.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int MAX_N = 20, MAX_M = 10;
    public static final int MAX_INT = Integer.MAX_VALUE;
    public static int n, m, f;
    public static int startX, startY;

    public static int[][] wall = new int[MAX_M * 3][MAX_M * 3];
    public static boolean[][] wVisited = new boolean[MAX_M * 3][MAX_M * 3];
    public static int[][] step = new int[MAX_M * 3][MAX_M * 3];

    public static int[][] space = new int[MAX_N][MAX_N];

    public static Queue<Pair> queue = new LinkedList<>();

    public static void print() {
        for (int i = 0; i < 3 * m; i++) {
            System.out.println(Arrays.toString(step[i]));
        }
    }

    public static int[][] rotate(int m, int cnt, int[][] temp) {
        int[][] newTemp = new int[m][m];

        for (int k = 0; k < cnt; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    newTemp[j][m - i - 1] = temp[i][j];
                }
            }
        }

        return newTemp;
    }

    public static boolean inRange(int x, int y) {
        return (0 <= x && x < m * 3 && 0 <= y && y < m * 3);
    }

    public static void bfs() {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (!inRange(nx, ny)) {
                    continue;
                }

                if (!wVisited[nx][ny] && wall[nx][ny] == 0) {
                    wVisited[nx][ny] = true;
                    step[nx][ny] = step[current.x][current.y] + 1;
                    queue.add(new Pair(nx, ny));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // wall 초기화
        for (int i = 0; i < 3 * MAX_M; i++) {
            Arrays.fill(wall[i], 1);
        }

        int[][] temp = new int[m][m];

        // 동쪽
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                temp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        temp = rotate(m, 3, temp);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                wall[i + m][j + 2 * m] = temp[i][j];
            }
        }

        // 서쪽
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                temp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        temp = rotate(m, 1, temp);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                wall[i + m][j] = temp[i][j];
            }
        }

        // 남쪽
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                temp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                wall[i + 2 * m][j + m] = temp[i][j];
            }
        }

        // 북쪽
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                temp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        temp = rotate(m, 2, temp);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                wall[i][j + m] = temp[i][j];
            }
        }

        // 위쪽
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                temp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                wall[i + m][j + m] = temp[i][j];

                // 출발 위치 표시
                if (wall[i + m][j + m] == 2) {
                    startX = i + m;
                    startY = j + m;
                }
            }
        }

        // 시간의 벽 bfs
        wVisited[startX][startY] = true;
        queue.add(new Pair(startX, startY));

        bfs();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = MAX_INT;
            }
        }

        // 서쪽
        for (int i = 0; i < m; i++) {
            if (step[i + m][3 * m - 1] != 0)
                temp[i][m - 1] = Math.min(temp[i][m - 1], step[i + m][3 * m - 1]);
        }

        // 동쪽
        for (int i = 0; i < m; i++) {
            if (step[i + m][3 * m - 1] != 0)
                temp[i][0] = Math.min(temp[i][0], step[i + m][0]);
        }

        // 남쪽
        for (int i = 0; i < m; i++) {
            if (step[i + m][3 * m - 1] != 0)
                temp[m - 1][i] = Math.min(temp[m - 1][i], step[3 * m - 1][i + m]);
        }

        // 북쪽
        for (int i = 0; i < m; i++) {
            if (step[0][m + i] != 0)
                temp[0][i] = Math.min(temp[0][i], step[0][i + m]);
        }

        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(temp[i]));
        }
    }

}
