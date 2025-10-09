package Programmers;
import java.util.*;

class P150370_개인정보_수집_유효기간 {
    HashMap<String, Integer> map = new HashMap<>();

    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<>();

        String[] tArray = today.split("\\.");

        Integer[] nArray = Arrays.stream(tArray)
                .map(Integer::valueOf)
                .toArray(Integer[]::new);

        // 약관 정보 저장
        for (String term : terms) {
            String[] arr = term.split(" ");

            map.put(arr[0], Integer.valueOf(arr[1]));
        }

        // 개인정보
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];

            String[] arr = privacy.split(" ");

            String[] date = arr[0].split("\\.");
            int month = map.get(arr[1]);

            int d = Integer.valueOf(date[2]);
            int m = Integer.valueOf(date[1]) + month;
            int y = Integer.valueOf(date[0]);

            y += m / 12;
            m %= 12;

            d -= 1;

            if (d == 0) {
                m -= 1;
                d = 28;
            }

            if (m == 0) {
                y -= 1;
                m = 12;
            }

            if (m > 12) {
                m -= 12;
                y += 1;
            }

            //System.out.println(y + " " + m + " " + d);

            if (nArray[0] > y) {
                answer.add(i + 1);
            } else if (nArray[0] == y) {
                if (nArray[1] > m) {
                    answer.add(i + 1);
                } else if (nArray[1] == m) {
                    if (nArray[2] > d) {
                        answer.add(i + 1);
                    }
                }
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
