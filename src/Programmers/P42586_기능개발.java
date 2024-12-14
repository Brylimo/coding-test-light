package Programmers;
import java.util.*;

public class P42586_기능개발 {
    public Set<Integer> set = new HashSet<>();
    public boolean[] visited;

    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();

        int size = progresses.length;
        int[] lefts = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(visited, false);

        // initialize
        for (int i = 0; i < size; i++) {
            lefts[i] = (int)Math.ceil((100 - progresses[i]) / (double)speeds[i]);
        }

        for (int i = 0; i < size; i++) {
            if (visited[i]) continue;

            int value = lefts[i];
            int cnt = 1;

            visited[i] = true;
            for (int j = i + 1; j < size; j++) {
                if (!visited[j] && value >= lefts[j]) {
                    visited[j] = true;
                    cnt += 1;
                } else {
                    break;
                }
            }

            answer.add(cnt);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
