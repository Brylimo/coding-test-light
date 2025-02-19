package Baekjoon;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/**
 * P1021_회전하는_큐
 * 난이도 2/10
 * 덱
 * 116ms
 *
 * 왼쪽으로 이동할 수도 있고 오른쪽으로 이동할 수도 있기 때문에 양방향 순환 큐에서 각각의 경우를 모두 시뮬레이션하고
 * 연산이 더 적은 쪽을 항상 선택해 양방향 순환 큐를 갱신하도록 했다.
 *
 */
public class P1021_회전하는_큐 {
    static int n, m;
    static int[] arr;
    static ArrayList<Integer> list;
    static List<Integer> rList, lList;
    static ArrayDeque<Integer> deque = new ArrayDeque<>();

    static int rotateRight(int target) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(list);

        int cnt = 0;
        while (true) {
            int num = deque.pollLast();

            if (num == target) {
                cnt++;
                rList = deque.stream().collect(Collectors.toList());
                return cnt;
            }

            cnt++;
            deque.offerFirst(num);
        }
    }

    static int rotateLeft(int target) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(list);

        int cnt = 0;
        while (true) {
            int num = deque.pollFirst();

            if (num == target) {
                lList = deque.stream().collect(Collectors.toList());
                return cnt;
            }

            cnt++;
            deque.offerLast(num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        int cnt = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        for (int i = 0; i < m; i++) {
            int target = arr[i];

            int cntLeft = rotateLeft(target);
            int cntRight = rotateRight(target);

            int subCnt = 0;
            if (cntLeft <= cntRight) {
                list = (ArrayList<Integer>) lList;
                subCnt = cntLeft;
            } else {
                list = (ArrayList<Integer>) rList;
                subCnt = cntRight;
            }

            cnt += subCnt;
        }

        System.out.println(cnt);
    }
}
