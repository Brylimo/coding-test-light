package Programmers;
import java.util.*;

public class P43163_단어_변환 {
    public int solution(String begin, String target, String[] words) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        // map 초기화
        map.put(begin, new ArrayList<>());
        for (String word : words) {
            map.put(word, new ArrayList<>());
        }

        // begin에 대해 조사
        for (int j = 0; j < words.length; j++) {
            int diffCnt = 0;
            for (int k = 0; k < words[j].length(); k++) {
                if (begin.charAt(k) != words[j].charAt(k)) {
                    diffCnt++;
                }
            }

            if (diffCnt == 1) {
                map.get(begin).add(words[j]);
            }
        }

        // map에 연관 단어 집어넣기
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {

                int diffCnt = 0;
                for (int k = 0; k < words[j].length(); k++) {
                    if (words[i].charAt(k) != words[j].charAt(k)) {
                        diffCnt++;
                    }
                }

                if (diffCnt == 1) {
                    map.get(words[i]).add(words[j]);
                    map.get(words[j]).add(words[i]);
                }
            }
        }

        // bfs
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String, Integer> stepMap = new HashMap<>();

        stepMap.put(begin, 0);
        for (String word : words) {
            stepMap.put(word, 0);
        }

        visited.add(begin);
        queue.offer(begin);

        while (!queue.isEmpty()) {
            String str = queue.poll();

            if (str.equals(target)) { // 종료조건
                break;
            }

            for (String word : map.get(str)) {
                if (!visited.contains(word)) {
                    visited.add(word);
                    stepMap.replace(word, stepMap.get(str) + 1);
                    queue.offer(word);
                }
            }
        }

        return stepMap.getOrDefault(target, 0);
    }
}
