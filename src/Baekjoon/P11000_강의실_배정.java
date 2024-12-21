package Baekjoon;
import java.util.*;
import java.io.*;

public class P11000_강의실_배정 {
    static int ans = 0; // 최종 강의실 개수
    static ArrayList<Pair> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list.add(new Pair(0, s));
            list.add(new Pair(1, t));
        }

        Collections.sort(list);

        int cnt = 0; // 강의실의 현재 개수를 나타냄
        for (Pair p : list) {
            if (p.type == 0) { // 강의 시작이면 강의실 개수 하나 증가
                cnt += 1;
            } else { // 강의 끝이면 강의실 개수 하나 감소
                cnt -= 1;
            }

            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }

    static class Pair implements Comparable<Pair> {
        int type; // 강의의 시작 지점인지 끝 지점인지를 나타냄
        int time; // 시간

        public Pair(int type, int time) {
            this.type = type;
            this.time = time;
        }

        @Override
        public int compareTo(Pair p) {
            if (this.time != p.time) {
                return this.time - p.time;
            }

            return p.type - this.type;
        }
    }
}
