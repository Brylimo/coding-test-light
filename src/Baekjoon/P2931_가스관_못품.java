package Baekjoon;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class P2931_가스관_못품 {
    static int r, c, mx, my, zx, zy, fx, fy;
    static char fShape;
    static char[][] grid;
    static boolean[][][] visited;
    static Queue<int[]> queue = new ArrayDeque<>();

    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    static int[][][] delta = new int[][][] {
            { {-1, 0}, {1,0} }, // |
            { {0, 1}, {0, -1} }, // -
            { {-1, 0}, {1,0}, {0, 1}, {0, -1} }, // +
            { {0, 1}, {1, 0} }, // 1
            { {-1, 0}, {0, 1} }, // 2
            { {-1, 0}, {0, -1} }, // 3
            { {0, -1}, {1, 0} } // 4
    };

    static int convert(char op) {
        if (op == '|') {
            return 0;
        } else if (op == '-') {
            return 1;
        } else if (op == '+') {
            return 2;
        } else if (op == '1') {
            return 3;
        } else if (op == '2') {
            return 4;
        } else if (op == '3') {
            return 5;
        } else if (op == '4') {
            return 6;
        }

        return -1;
    }

    static void move() {
        int nx, ny;

        int cx = mx;
        int cy = my;

        for (int i = 0; i < 4; i++) {
            visited[cx][cy][i] = true;
        }

        // 모스크바에서 가스관을 찾는다.
        for (int i = 0; i < 4; i++) {
            nx = cx + dx[i];
            ny = cy + dy[i];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

            if (!visited[nx][ny][i] && grid[nx][ny] != '.' && grid[nx][ny] != 'Z') {

                if (grid[nx][ny] != '+') {
                    visited[nx][ny][0] = true;
                    visited[nx][ny][1] = true;
                    visited[nx][ny][2] = true;
                    visited[nx][ny][3] = true;
                }
                queue.offer(new int[] {nx, ny, dx[i], dy[i]});
                break;
            }
        }

        // 모스크바에서 가스관으로 이동
        OUT : while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int dir = 1;
            if (grid[current[0]][current[1]] == '.') {
                HashSet<List<Integer>> set = new HashSet<>();
                set.add(Arrays.asList(current[2] * -1, current[3] * -1));

                for (int i = 0; i < 4; i++) {
                    nx = current[0] + dx[i];
                    ny = current[1] + dy[i];

                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                    if (!visited[nx][ny][i] && grid[nx][ny] != '.') {
                        set.add(Arrays.asList(dx[i], dy[i]));
                    }
                }

                fx = current[0];
                fy = current[1];

                if (set.size() == 4) {
                    fShape = '+';
                    break OUT;
                } else {
                    for (int i = 0; i < delta.length; i++) {
                        boolean pass = true;
                        for (int j = 0; j < delta[i].length; j++) {
                            if (!set.contains(Arrays.stream(delta[i][j]).boxed().collect(Collectors.toList()))) {
                                pass = false;
                            }
                        }

                        if (pass) {
                            if (i == 0) {
                                fShape = '|';
                            } else if (i == 1) {
                                fShape = '-';
                            } else if (i == 2) {
                                fShape = '+';
                            } else if (i == 3) {
                                fShape = '1';
                            } else if (i == 4) {
                                fShape = '2';
                            } else if (i == 5) {
                                fShape = '3';
                            } else if (i == 6) {
                                fShape = '4';
                            }
                            break OUT;
                        }

                    }
                }

            } else {
                dir = convert(grid[current[0]][current[1]]); // 방향
            }

            boolean isGo = false;
            for (int[] way : delta[dir]) {
                nx = current[0] + way[0];
                ny = current[1] + way[1];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                int idx = 0;
                if (way[0] == -1 && way[1] == 0) {
                    idx = 0;
                } else if (way[0] == 0 && way[1] == 1) {
                    idx = 1;
                } else if (way[0] == 1 && way[1] == 0) {
                    idx = 2;
                } else if (way[0] == 0 && way[1] == -1) {
                    idx = 3;
                }

                if (!visited[nx][ny][idx]) {
                    if (grid[current[0]][current[1]] == '+') {
                        if (way[0] == current[2] && way[1] == current[3]) {
                            if (grid[nx][ny] != '+') {
                                visited[nx][ny][0] = true;
                                visited[nx][ny][1] = true;
                                visited[nx][ny][2] = true;
                                visited[nx][ny][3] = true;
                            } else {
                                visited[nx][ny][idx] = true;
                                visited[nx][ny][(idx + 2) % 4] = true;
                            }
                            queue.offer(new int[] {nx, ny, way[0], way[1]});
                        }
                    } else {
                        if (grid[nx][ny] != '+') {
                            visited[nx][ny][0] = true;
                            visited[nx][ny][1] = true;
                            visited[nx][ny][2] = true;
                            visited[nx][ny][3] = true;
                        } else {
                            visited[nx][ny][idx] = true;
                            visited[nx][ny][(idx + 2) % 4] = true;
                        }
                        queue.offer(new int[] {nx, ny, way[0], way[1]});
                    }
                }
            }

        }

        System.out.println((fx + 1) + " " + (fy + 1) + " " + fShape);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        grid = new char[r][c];
        visited = new boolean[r][c][4];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                grid[i][j] = line.charAt(j);

                if (grid[i][j] == 'M') {
                    mx = i;
                    my = j;
                } else if (grid[i][j] == 'Z') {
                    zx = i;
                    zy = j;
                }
            }
        }

        move();
    }
}
