package Baekjoon;
import java.util.*;
import java.io.*;

public class P5052_전화번호_목록 {
    static Trie root;
    static String[] list;
    static boolean ans;

    static void insert(String s) {
        Trie t = root;

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - '0';
            if (t.children[index] == null) {
                t.children[index] = new Trie();
            }

            t = t.children[index];
        }
        t.isEnd = true;
    }

    static boolean search(String s) {
        Trie t = root;

        for (int i = 0; i < s.length(); i++) {
            if (t.isEnd) {
                return true;
            }

            int index = s.charAt(i) - '0';
            t = t.children[index];
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            root = new Trie();
            int n = Integer.parseInt(br.readLine());

            list = new String[n];
            for (int i = 0; i < n; i++) {
                String s = br.readLine();

                list[i] = s;
                insert(s);
            }

            for (int i = 0; i < n; i++) {
                ans = search(list[i]);

                if (ans) break;
            }

            if (ans) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }

            ans = false;
        }
    }

    static class Trie {
        private boolean isEnd;
        private Trie[] children = new Trie[10];

        Trie() {
            for (int i = 0; i < 10; i++) {
                this.children[i] = null;
            }
            this.isEnd = false;
        }
    }
}
