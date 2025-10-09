package Programmers;
import java.util.*;

class P81301_숫자_문자열과_영단어 {
    static HashMap<String, Integer> map = new HashMap<>();

    public int solution(String s) {
        int answer = 0;

        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int target = s.charAt(i) - 'a';

            if (target >= 0 && target < 27) { // 문자
                sb.append(s.charAt(i));
            } else {
                answer = answer * 10 + (s.charAt(i) - '0');
            }

            if (sb.toString().length() > 0 && map.containsKey(sb.toString())) {
                answer = answer * 10 + map.get(sb.toString());
                sb.setLength(0);
            }
        }

        return answer;
    }
}
