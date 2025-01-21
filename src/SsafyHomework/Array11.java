package SsafyHomework;

public class Array11 {
    private static int R = 4, C = 5;
    private static char[][] map;
    // TODO: deltas 계열의 변수를 초기화 하시오.
    static int[] dx1 = new int[] {-1, 0, 1, 0};
    static int[] dy1 = new int[] {0, 1, 0, -1};

    static int[] dx2 = new int[] {-1, 1, 1, -1};
    static int[] dy2 = new int[] {1, 1, -1, -1};

    static int[] dx3 = new int[] {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dy3 = new int[] {-1, 1, 2, 2, 1, -1, -2, -2};
    // END

    private static void setup() {
        map = new char[R][C];
        char alpha = 'A';
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = alpha++;
            }
        }
    }

    public static void main(String[] args) {
        setup();
        checkCross();
        checkX();
        straight();
        moveHorse();
    }

    private static void checkCross() {
        int sum = 0;

        // TODO: 자음의주변을 +로 탐색하고 요소의 합을 출력하시오.A=0, B=1,..
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!(map[i][j] == 'A' || map[i][j] == 'E' || map[i][j] == 'I' || map[i][j] == 'O' || map[i][j] == 'U')) { // 자음
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx1[k];
                        int ny = j + dy1[k];

                        if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                        sum += map[nx][ny] - 'A';
                    }
                }
            }
        }

        // END
        System.out.println(sum == 498);
    }

    private static void checkX() {

        int sum = 0;
        // TODO: 모음의 주변을 X로 탐색하고 요소의 합을 출력하시오.(A=0, B=1, ...)
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'A' || map[i][j] == 'E' || map[i][j] == 'I' || map[i][j] == 'O' || map[i][j] == 'U') { // 자음
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx2[k];
                        int ny = j + dy2[k];

                        if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                        sum += map[nx][ny] - 'A';
                    }
                }
            }
        }

        // END
        System.out.println(sum == 72);
    }

    private static void straight() {
        int sum = 0;

        // TODO: 'K'의 사방으로 영역 내에서 모음을 만나기 전까지 모든 자음들을 합해서 출력하세요.('A'=0, 'B'=1, ...)
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'K') {
                    for (int k = 0; k < 4; k++) {

                        int sx = i;
                        int sy = j;
                        while (true) {
                            int nx = sx + dx1[k];
                            int ny = sy + dy1[k];

                            if (nx < 0 || nx >= R || ny < 0 || ny >= C) break;

                            if (!(map[nx][ny] == 'A' || map[nx][ny] == 'E' || map[nx][ny] == 'I' || map[nx][ny] == 'O' || map[nx][ny] == 'U')) {
                                // 모음
                                sum += map[nx][ny] - 'A';
                            } else {
                                break;
                            }

                            sx = nx;
                            sy = ny;
                        }
                    }
                }
            }
        }

        // END
        System.out.println(sum == 56);
    }

    private static void moveHorse() {
        char maxChar = 'A';
        int maxSum = 0;
        // TODO: 모음에서 장기의 말(馬)이동 했을 때 만나는 지점의 합이 가장 큰 알파벳과 그때의 합은? 합이 같다면 큰 알파벳

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int sum = 0;
                if (map[i][j] == 'A' || map[i][j] == 'E' || map[i][j] == 'I' || map[i][j] == 'O' || map[i][j] == 'U') {
                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx3[k];
                        int ny = j + dy3[k];

                        if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                        sum += map[nx][ny] - 'A';
                    }

                    if (maxSum < sum) {
                        maxSum = sum;
                        maxChar = map[i][j];
                    }
                }
            }
        }

        // END
        System.out.println(maxChar == 'I' && maxSum == 48);
    }
}
