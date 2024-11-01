import java.util.*;
import java.io.*;

public class P12891_DNA_비밀번호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        String dna = br.readLine();

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[4];

        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Character, Integer> mapper = new HashMap<>();
        mapper.put('A', 0);
        mapper.put('C', 1);
        mapper.put('G', 2);
        mapper.put('T', 3);

        int[] countArray = new int[4];
        for (int i = 0; i < p - 1; i++) {
            char target = dna.charAt(i);

            countArray[mapper.get(target)] += 1;
        }

        int ans = 0;
        for (int i = p - 1; i < s; i++) {
            if (i >= p) {
                countArray[mapper.get(dna.charAt(i - p))] -= 1;
            }
            countArray[mapper.get(dna.charAt(i))] += 1;

            boolean flag = true;
            for (int j = 0; j < 4; j++) {
                if (countArray[j] < arr[j]) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ans += 1;
            }
        }

        System.out.println(ans);
    }
}
