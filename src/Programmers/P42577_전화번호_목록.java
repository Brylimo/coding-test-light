package Programmers;
import java.util.*;

public class P42577_전화번호_목록 {
    public HashSet<String> set = new HashSet<>();

    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book, (String a, String b) -> a.length() - b.length());

        for (String phone : phone_book) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phone.length(); i++) {
                sb.append(phone.charAt(i));

                if (set.contains(sb.toString())) {
                    answer = false;
                }
            }

            set.add(phone);
        }

        return answer;
    }
}
