package Programmers;
import java.util.*;

class P92334_신고_결과_받기 {
    HashMap<String, HashSet<String>> map = new HashMap<>();
    HashMap<String, Integer> h = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        ArrayList<Integer> answer = new ArrayList<>();

        for (String id : id_list) {
            map.put(id, new HashSet<>());
            h.put(id, 0);
        }

        for (int i = 0; i < report.length; i++) {
            String entry = report[i];

            String[] array = entry.split(" ");

            map.get(array[1]).add(array[0]);
        }

        //System.out.println("uu " + map);
        for (String id : id_list) {
            if (map.get(id).size() >= k) {
                //System.out.println("tt " + map.get(id));
                for (String user : map.get(id)) {
                    h.put(user, h.get(user) + 1);
                }
            }
        }

        //System.out.println(h);
        for (String id : id_list) {
            answer.add(h.get(id));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
