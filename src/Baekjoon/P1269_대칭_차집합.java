package Baekjoon;
import java.util.*;
import java.io.*;

/**
 * Main_1269_대칭_차집합
 * 난이도 1/10
 * 자료 구조
 * 780ms
 *
 * a와 관련된 집합, b와 관련된 집합을 만들어서 각각의 차집합을 구하고(removeAll) 개수를 더해준다.
 *
 */
public class P1269_대칭_차집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        HashSet<Integer> aSet = new HashSet<>();
        HashSet<Integer> bSet = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a; i++) {
            aSet.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b; i++) {
            bSet.add(Integer.parseInt(st.nextToken()));
        }

        HashSet<Integer> aaSet = new HashSet<>(aSet); // aSet을 복사한 set 하나를 더 생성
        aSet.removeAll(bSet); // 차집합
        bSet.removeAll(aaSet); // 차집합

        int cnt = aSet.size() + bSet.size(); // 개수를 구한다

        System.out.println(cnt);
    }
}
