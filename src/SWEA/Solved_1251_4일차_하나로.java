package SWEA;
import java.util.*;
import java.io.*;

/**
 * SWEA1251_4일차_하나로
 * 난이도 3/10
 * MST
 * 1,137ms
 *
 * 입력으로 주어진 x와 y를 모두 연결해 보며 거리를 구하고 이 거리를 우선순위 큐에 x, y와 함께 저장하여(Pair 객체 형태)
 * 거리 기준으로 오름차순으로 정렬하고(그리디) 하나씩 빼보며 사이클을 이루지 않을 경우 선택한다. (크루스칼 알고리즘)
 * 크루스칼 알고리즘을 이용해 MST를 만들면 이 때의 환경 부담금이 최소가 된다.
 * n이 1000으로 충분히 작기 때문에 모든 선분을 연결하여 우선순위 큐에 집어넣는 것이 가능하다.
 *
 */
public class Solved_1251_4일차_하나로 {
    static int n; // 섬의 개수
    static int[] xArray; // x 좌표 순서대로 모은 배열
    static int[] yArray; // y 좌표 순서대로 모은 배열
    static int[] parent;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    // find 함수
    static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]); // 경로 압축
    }

    // union 함수
    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    // 거리를 구하는 유틸리티 함수 sqrt((x1 - x2) ^ 2 + (y1 - y2) ^ 2)
    static double getDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine()); // 섬의 개수

            xArray = new int[n]; // x 좌표 모은 배열
            yArray = new int[n]; // y 좌표 모은 배열

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                xArray[i] = Integer.parseInt(st.nextToken()); // x 좌표를 모두 받음
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                yArray[i] = Integer.parseInt(st.nextToken()); // y 좌표를 모두 받음
            }

            double E = Double.parseDouble(br.readLine()); // 해저터널 건설의 환경 부담 세율 실수

            parent = new int[n]; // 유니온 파인드 집합을 표현하기 위한 자료구조
            for (int i = 0; i < n; i++) {
                parent[i] = i; // parent 배열의 초기값은 자기 자신
            }

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    // 우선순위 큐에 모든 i, j, 거리 값을 저장한다.
                    pq.offer(new Pair(i, j, getDist(xArray[i], yArray[i], xArray[j], yArray[j])));
                }
            }

            // 크루스칼 알고리즘 수행, 우선순위 큐는 dist를 기준으로 오름차순으로 정렬되어 있음
            double result = 0;
            while (!pq.isEmpty()) {
                Pair current = pq.poll(); // pq에서 값을 꺼내서

                if (find(current.one) != find(current.two)) { // 사이클을 이루지 않으면
                    union(current.one, current.two); // union 연산 진행
                    result += E * Math.pow(current.dist, 2); // 환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L^2)만큼 매번 더함
                }
            }

            System.out.printf("#%d %d\n", t, Math.round(result)); // 소수 첫째 자리에서 반올림
            result = 0;
        }
    }

    static class Pair implements Comparable<Pair> {
        int one, two; // 첫번째 지점, 두번째 지점
        double dist; // 거리

        public Pair(int one, int two, double dist) { // 첫번째 지점, 두번째 지점, 거리 저장
            this.one = one;
            this.two = two;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return Double.compare(this.dist, other.dist);
        } // dist를 기준으로 오름차순으로 정렬한다.
    }
}
