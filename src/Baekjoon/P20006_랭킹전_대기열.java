package Baekjoon;
import java.util.*;
import java.io.*;

public class P20006_랭킹전_대기열 {
    static ArrayList<ArrayList<Pair>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken()); // 플레이어 수
        int m = Integer.parseInt(st.nextToken()); // 방의 정원

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken()); // 레벨
            String n = st.nextToken(); // 닉네임

            if (list.size() == 0) {
                list.add(new ArrayList<>(Arrays.asList(new Pair(l, n))));
            } else {

                boolean done = false;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).size() < m) { // 방의 정원보다 작으면

                        Pair target = list.get(j).get(0); // 기준 노드를 꺼내옴

                        if (target.level - 10 <= l && target.level + 10 >= l) { // 입장 가능
                            list.get(j).add(new Pair(l, n)); // 넣어줌
                            done = true;
                            break;
                        }
                    }
                }

                if (!done) { // done이 여전히 false이면
                    list.add(new ArrayList<>(Arrays.asList(new Pair(l, n)))); // 새로 리스트를 하나 만들어줌
                }
            }

        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == m) {
                System.out.println("Started!");
            } else {
                System.out.println("Waiting!");
            }

            Collections.sort(list.get(i));
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.println(list.get(i).get(j).level + " " + list.get(i).get(j).nickname);
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int level;
        String nickname;

        public Pair(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public int compareTo(Pair other) {
            return this.nickname.compareTo(other.nickname);
        }
    }

}
