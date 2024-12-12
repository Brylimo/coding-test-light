package Programmers;
import java.util.*;

public class P42578_의상 {
    public HashMap<String, ArrayList<String>> map = new HashMap<>();
    public ArrayList<String> candidates = new ArrayList<>();
    public int answer = 1;

    public int solution(String[][] clothes) {
        int size = clothes.length;

        for (int i = 0; i < size; i++) {
            String kind = clothes[i][1];
            String element = clothes[i][0];

            if (map.containsKey(kind)) {
                ArrayList<String> list = map.get(kind);
                list.add(element);

                map.put(kind, list);
            } else {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(element);

                map.put(kind, tempList);
            }
        }

        Set<String> keys = map.keySet();
        List<String> keyss = new ArrayList<>(keys);

        for (String key : keyss) {
            answer *= map.get(key).size() + 1;
        }

        return answer - 1;
    }
}