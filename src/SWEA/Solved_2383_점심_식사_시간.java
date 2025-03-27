package SWEA;
import java.util.*;
import java.io.*;

/**
 * SWEA2383_점심_식사_시간
 * 난이도 5/10
 * 백트래킹
 * 157ms
 *
 * 계단이 2개이므로 백트래킹으로 첫번째 계단으로 가는 사람들을 구하고
 * 첫번째 계단으로 가는 사람들만을 기준으로 우선순위큐를 이용해 이동시간을 계산한다.
 * 이를 통해 걸리는 시간을 찾고 이와 동일한 방식으로 두번째 계단을 기준으로 적용하여 소요시간이 최소가 되는 시간을 찾는다.
 *
 */
public class Solved_2383_점심_식사_시간 {
    static int n, time;
    static int[][] arr;
    static Pair[] stairs;
    static ArrayList<Pair> people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());

            stairs = new Pair[2];
            arr = new int[n][n];
            people = new ArrayList<>();

            int scnt = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    if (arr[i][j] > 1) {
                        stairs[scnt++] = new Pair(i, j, arr[i][j]);
                    } else if (arr[i][j] == 1) {
                        people.add(new Pair(i, j, 0));
                    }
                }
            }

            int m = people.size();
            int snum = 1 << m;
            int ans = Integer.MAX_VALUE;

            for (int mask = 0; mask < snum; mask++) {
                List<Pair> subset1 = new ArrayList<>();
                List<Pair> subset2 = new ArrayList<>();

                for (int i = 0; i < m; i++) { // 부분조합을 구함
                    if ((mask & (1 << i)) != 0) {
                        subset1.add(people.get(i));
                    } else {
                        subset2.add(people.get(i));
                    }
                }

                ans = Math.min(ans, Math.max(getTime(subset1, stairs[0]), getTime(subset2, stairs[1])));
            }

            System.out.println("#" + t + " " + ans);
        }
    }

    static int getTime(List<Pair> subset, Pair stair) {
        List<Integer> arrivalTimes = new ArrayList<>();
        for (Pair p : subset) {
            int dist = Math.abs(p.x - stair.x) + Math.abs(p.y - stair.y);
            arrivalTimes.add(dist + 1);
        }

        Collections.sort(arrivalTimes);

        Queue<Integer> stairQueue = new LinkedList<>();
        int time = 0;
        for (int arrival : arrivalTimes) {
            while (!stairQueue.isEmpty() && stairQueue.peek() <= arrival) {
                stairQueue.poll();
            }

            if (stairQueue.size() < 3) {
                stairQueue.offer(arrival + stair.num);
            } else {
                int earliestExit = stairQueue.poll();
                stairQueue.offer(Math.max(earliestExit, arrival) + stair.num);
            }
        }

        while (!stairQueue.isEmpty()) {
            time = stairQueue.poll();
        }

        return time;
    }

    static class Pair {
        int x, y, num;

        public Pair(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
