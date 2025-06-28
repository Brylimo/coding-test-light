package CodeTree;
import java.util.Scanner;

// https://www.codetree.ai/ko/trails/complete/curated-cards/intro-linked-list1/description
public class CodeTree_연결리스트1 {
    static class Node { // 노드 생성
        String value;
        Node prev;
        Node nxt;

        public Node() {}

        public Node(String value) {
            this.value = value;
            this.prev = null;
            this.nxt = null;
        }
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        String sInit = sc.nextLine(); // 문자열
        int n = sc.nextInt(); // 총 연산 횟수

        Node cur = new Node(sInit);

        for (int i = 0; i < n; i++) {
            int opt = sc.nextInt();

            if (opt == 1) { // 단일 노드 생성하고, cur 앞에 삽입
                String val = sc.next();

                Node newNode = new Node(val);
                newNode.prev = cur.prev;
                newNode.nxt = cur;

                // newNode가 연결됨 -> newNode 기준으로 생각하자!!
                if (null != newNode.prev)
                    newNode.prev.nxt = newNode;
                if (null != newNode.nxt)
                    newNode.nxt.prev = newNode;

            } else if (opt == 2) { // 단일 노드 생성하고, cur 뒤에 삽입
                String val = sc.next();

                Node newNode = new Node(val);
                newNode.prev = cur;
                newNode.nxt = cur.nxt;

                // newNode가 연결됨 -> newNode 기준으로 생각하자!!
                if (null != newNode.prev)
                    newNode.prev.nxt = newNode;
                if (null != newNode.nxt)
                    newNode.nxt.prev = newNode;

            } else if (opt == 3) { // 노드 cur 이전 노드가 존재한다면, cur를 그 이전 노드로 변경
                if (null != cur.prev) {
                    cur = cur.prev;
                }
            } else if (opt == 4) { // 노드 cur 다음 노드가 존재한다면, cur를 그 다음 노드로 변경
                if (null != cur.nxt) {
                    cur = cur.nxt;
                }
            }

            if (null != cur.prev) {
                sb.append(cur.prev.value);
            } else {
                sb.append("(Null)");
            }

            sb.append(" ");

            if (null != cur) {
                sb.append(cur.value);
            } else {
                sb.append("(Null)");
            }

            sb.append(" ");

            if (null != cur.nxt) {
                sb.append(cur.nxt.value);
            } else {
                sb.append("(Null)");
            }

            sb.append("\n");

        }

        System.out.println(sb);
    }
}
