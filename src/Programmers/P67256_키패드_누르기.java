package Programmers;

import java.util.*;

class P67256_키패드_누르기 {
    String[][] grid = new String[][] {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"},
            {"*", "0", "#"}
    };
    int[][] step = new int[4][3];
    boolean[][] visited = new boolean[4][3];

    HashMap<Integer, Integer[]> map = new HashMap<>(Map.of(
            1, new Integer[] {0, 0},
            2, new Integer[] {0, 1},
            3, new Integer[] {0, 2},
            4, new Integer[] {1, 0},
            5, new Integer[] {1, 1},
            6, new Integer[] {1, 2},
            7, new Integer[] {2, 0},
            8, new Integer[] {2, 1},
            9, new Integer[] {2, 2},
            0, new Integer[] {3, 1}
    ));

    int[] left = {3, 0};
    int[] right = {3, 2};

    HashSet<Integer> leftSet = new HashSet<>(Arrays.asList(1, 4, 7));
    HashSet<Integer> rightSet = new HashSet<>(Arrays.asList(3, 6, 9));

    Queue<int[]> queue = new LinkedList<>();
    public void bfs() {
        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 3) continue;

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    step[nx][ny] = step[current[0]][current[1]] + 1;
                    queue.offer(new int[] { nx, ny });
                }
            }
        }
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        for (int number : numbers) {
            // 왼손으로 가능한지 확인
            if (leftSet.contains(number)) {
                Integer[] pos = map.get(number);

                left[0] = pos[0];
                left[1] = pos[1];
                sb.append("L");
            } else if (rightSet.contains(number)) { // 오른손으로 가능한지 확인
                Integer[] pos = map.get(number);

                right[0] = pos[0];
                right[1] = pos[1];
                sb.append("R");
            } else { // 거리 계산하여 확인
                Integer[] pos = map.get(number);

                // 초기화
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        step[i][j] = 0;
                        visited[i][j] = false;
                    }
                }

                visited[pos[0]][pos[1]] = true;
                queue.offer(new int[] { pos[0], pos[1] });
                bfs();

                int lStep = step[left[0]][left[1]];
                int rStep = step[right[0]][right[1]];

                if (lStep < rStep) {
                    left[0] = pos[0];
                    left[1] = pos[1];

                    sb.append("L");
                } else if (lStep > rStep) {
                    right[0] = pos[0];
                    right[1] = pos[1];

                    sb.append("R");
                } else {
                    if (hand.equals("left")) {
                        left[0] = pos[0];
                        left[1] = pos[1];

                        sb.append("L");
                    } else if (hand.equals("right")) {
                        right[0] = pos[0];
                        right[1] = pos[1];

                        sb.append("R");
                    }
                }

            }
        }

        return sb.toString();
    }
}
