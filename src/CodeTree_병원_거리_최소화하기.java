import java.util.*;
import java.io.*;

public class CodeTree_병원_거리_최소화하기 {
    public static final int MAX_INT = Integer.MAX_VALUE;
    public static final int MAX_N = 50;

    public static int n, m, ans = MAX_INT;

    public static int[][] grid = new int[MAX_N][MAX_N];
    public static ArrayList<Point> hospitals = new ArrayList<>();
    public static ArrayList<Point> people = new ArrayList<>();
    public static ArrayList<Point> candidate = new ArrayList<>();

    public static int getDist(Point p1, Point p2) {
        return (Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));
    }

    public static int calcDist() {
        int dist = 0;

        for (Point pPoint : people) {
            int subMax = MAX_INT;
            for (Point hPoint : candidate) {
                subMax = Math.min(subMax, getDist(pPoint, hPoint));
            }

            dist += subMax;
        }

        return dist;
    }

    public static void calculate(int current, int cnt) {
        if (cnt == m) {
            ans = Math.min(ans, calcDist());
            return;
        }

        if (current == hospitals.size()) {
            if (cnt == m) {
                ans = Math.min(ans, calcDist());
            }
            return;
        }

        candidate.add(new Point(hospitals.get(current).x, hospitals.get(current).y));
        calculate(current + 1, cnt + 1);
        candidate.remove(candidate.size() - 1);

        calculate(current + 1, cnt);
    }

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // GRID 채우기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());

                if (grid[i][j] == 1) {
                    people.add(new Point(i, j));
                } else if (grid[i][j] == 2) {
                    hospitals.add(new Point(i, j));
                }
            }
        }

        calculate(0, 0);
        System.out.println(ans);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}