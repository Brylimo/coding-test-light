package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * P1759_암호만들기
 * 난이도 4/10
 * 백트래킹
 * 160ms
 *
 * 모음과 자음으로 각각 조합을 구하여 합치고 정렬한다
 *
 */
public class P1759_암호만들기 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Character> vowels = new ArrayList<>();
        List<Character> consonants = new ArrayList<>();

        while (st.hasMoreTokens()) {
            char ch = st.nextToken().charAt(0);
            if ("aeiou".indexOf(ch) >= 0) {
                vowels.add(ch); // 모음 저장
            } else {
                consonants.add(ch); // 자음 저장
            }
        }

        List<String> playList = new ArrayList<>();

        for (int vCount = 1; vCount <= Math.min(vowels.size(), l - 2); vCount++) {
            int cCount = l - vCount;

            if (cCount < 2) continue;

            List<List<Character>> vowelCombinations = getCombinations(vowels, vCount); // 조합을 구함
            List<List<Character>> consonantCombinations = getCombinations(consonants, cCount); // 조합을 구함

            for (List<Character> vComb : vowelCombinations) {
                for (List<Character> cComb : consonantCombinations) {
                    List<Character> password = new ArrayList<>(vComb);
                    password.addAll(cComb);
                    Collections.sort(password); // 정렬

                    StringBuilder sb = new StringBuilder();
                    for (char ch : password) {
                        sb.append(ch);
                    }
                    playList.add(sb.toString());
                }
            }
        }

        Collections.sort(playList);
        for (String password : playList) {
            sb.append(password).append('\n');
        }

        System.out.println(sb);
    }

    private static List<List<Character>> getCombinations(List<Character> list, int size) {
        List<List<Character>> result = new ArrayList<>();
        generateCombinations(list, size, 0, new ArrayList<>(), result);
        return result;
    }

    // 조합 구함
    private static void generateCombinations(List<Character> list, int size, int index, List<Character> current, List<List<Character>> result) {
        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < list.size(); i++) {
            current.add(list.get(i));
            generateCombinations(list, size, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}

