package Programmers;

import java.util.*;

class P64065_튜플 {

    public int[] solution(String s) {
        ArrayList<Integer> answer = new ArrayList<>();
        String[][] array;

        String str = s.substring(2, s.length() - 2);
        String[] arr = str.split("\\},\\{");

        array = new String[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            String[] subArr = arr[i].split(",");

            array[i] = subArr;
        }

        // String[] 길이로 정렬
        Arrays.sort(array, (a, b) -> a.length - b.length);

        HashSet<Integer> set = new HashSet<>();
        for (String[] k : array) {
            for (String ss : k) {
                if (!set.contains(Integer.parseInt(ss))) {
                    set.add(Integer.parseInt(ss));
                    answer.add(Integer.parseInt(ss));
                }
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
