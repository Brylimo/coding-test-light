package Baekjoon;
import java.util.*;
import java.io.*;

public class P1822_차집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int na = Integer.parseInt(st.nextToken());
        int nb = Integer.parseInt(st.nextToken());

        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < na; i++) {
            aList.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nb; i++) {
            bList.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(aList);
        Collections.sort(bList);

        LinkedHashSet<Integer> aSet = new LinkedHashSet<>(aList);
        LinkedHashSet<Integer> bSet = new LinkedHashSet<>(bList);

        aSet.removeAll(bSet);

        sb.append(aSet.size()).append("\n");
        for (int element : aSet) {
            sb.append(element).append(" ");
        }

        System.out.println(sb);
    }
}
