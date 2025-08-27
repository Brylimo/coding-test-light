package Baekjoon;
import java.util.*;
import java.io.*;

public class P27652_AB {
    static Node aRoot, bRoot;

    static class Node {
        HashMap<Character, Node> children;
        int value;

        public Node() {
            children = new HashMap<>();
            value = 1;
        }
    }

    static void add(String kind, char[] arr) {

        Node current = null;
        if (kind.equals("A")) {
            current = aRoot;

            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];

                if (!current.children.containsKey(c)) {
                    current.children.put(c, new Node());
                } else {
                    current.children.get(c).value += 1;
                }

                current = current.children.get(c);
            }
        } else {
            current = bRoot;

            for (int i = arr.length - 1; i >= 0; i--) {
                char c = arr[i];

                if (!current.children.containsKey(c)) {
                    current.children.put(c, new Node());
                } else {
                    current.children.get(c).value += 1;
                }

                current = current.children.get(c);
            }
        }
    }

    static void remove(String kind, char[] arr) {

        Node current = null;
        if (kind.equals("A")) {
            current = aRoot;

            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];

                current.children.get(c).value -= 1;
                current = current.children.get(c);
            }
        } else {
            current = bRoot;

            for (int i = arr.length - 1; i >= 0; i--) {
                char c = arr[i];

                current.children.get(c).value -= 1;
                current = current.children.get(c);
            }
        }

    }

    /*static int findA(char[] arr, int s, int e) {
        Node current = aRoot;

        int[] pref = new int[arr.length + 1];
        for (int i = s; i < e; i++) {
            char c = arr[i];

            current = current.children.get(c);
            pref[i] = (current == null || current.value <= 0) ? 0 : current.value;;

            if (current == null || current.value <= 0) return 0;
        }

        return current.value;
    }

    static int findB(char[] arr, int s, int e) {
        Node current = bRoot;

        for (int i = e - 1; i >= s; i--) {
            char c = arr[i];

            current = current.children.get(c);

            if (current == null || current.value <= 0) return 0;
        }

        return current.value;
    }*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        aRoot = new Node();
        bRoot = new Node();

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            String op = st.nextToken();

            if (op.equals("add")) {
                String kind = st.nextToken();
                String s = st.nextToken();

                add(kind, s.toCharArray());
            } else if (op.equals("delete")) {
                String kind = st.nextToken();
                String s = st.nextToken();

                remove(kind, s.toCharArray());
            } else if (op.equals("find")) {
                String s = st.nextToken();
                char[] arr = s.toCharArray();

                int n = arr.length;

                int[] pref = new int[n + 1];
                Node curA = aRoot;
                for (int j = 0; j < n; j++) {
                    if (curA != null) curA = curA.children.get(arr[j]);
                    pref[j + 1] = (curA == null) ? 0 : curA.value;
                    // curA가 null이 되면 이후도 계속 0이므로 그대로 둬도 됨
                }

                int[] suf = new int[n + 1];
                Node curB = bRoot;
                for (int j = n - 1; j >= 0; j--) {
                    if (curB != null) curB = curB.children.get(arr[j]);
                    suf[j] = (curB == null) ? 0 : curB.value;
                }

                // 3) 합산
                long sum = 0;
                for (int j = 1; j < n; j++) {
                    sum += (long) pref[j] * suf[j];
                }

                sb.append(sum).append("\n");
            }

        }

        System.out.println(sb);
    }
}
