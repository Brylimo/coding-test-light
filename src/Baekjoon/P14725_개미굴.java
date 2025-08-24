package Baekjoon;
import java.util.*;
import java.io.*;
import java.util.Map.*;
public class P14725_개미굴 {
    static Node root = new Node();
    static StringBuilder sb = new StringBuilder();

    static class Node {
        TreeMap<String, Node> children;

        public Node() {
            this.children = new TreeMap<>();
        }
    }

    static void traverse(Node cur, int lineCnt) {

        for (Entry<String, Node> entry : cur.children.entrySet()) {
            for (int i = 0; i < lineCnt; i++) {
                sb.append("-");
            }
            sb.append(entry.getKey()).append("\n");

            traverse(entry.getValue(), lineCnt + 2); // node 이동
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            // insert
            Node cur = root;
            for (int j = 0; j < k; j++) {
                String str = st.nextToken();

                if (cur != null) {
                    cur.children.putIfAbsent(str, new Node());

                    cur = cur.children.get(str);
                }
            }
        }

        traverse(root, 0);

        System.out.println(sb);
    }
}
