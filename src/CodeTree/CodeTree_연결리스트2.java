package CodeTree;
import java.util.Scanner;

// https://www.codetree.ai/ko/trails/complete/curated-cards/intro-linked-list2/description
public class CodeTree_연결리스트2 {
    static class Node {
        int value;
        Node prev;
        Node nxt;

        public Node(int value) {
            this.value = value;
            this.prev = null;
            this.nxt = null;
        }
    }

    static Node[] nodes;

    static void connect(Node s, Node e) { // 노드 두 개를 연결하는 함수
        if (null != s) {
            s.nxt = e;
        }
        if (null != e) {
            e.prev = s;
        }
    }

    // connect 함수를 이용해 왼쪽 연결하고 오른쪽 연결하는 방식을 사용함
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n개의 단일 노드
        int q = sc.nextInt(); // 총 연산 횟수

        nodes = new Node[n + 1]; // 총 n개의 노드들을 배열로 관리

        // 노드를 모두 생성
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < q; i++) { // q개의 연산이 주어짐
            int op = sc.nextInt();

            if (op == 1) { // x번 노드를 그 노드가 속해 있던 연결 리스트에서 뽑아서 단일 노드가 되게 함
                int x = sc.nextInt();

                Node cur = nodes[x];
                connect(cur.prev, cur.nxt);

                cur.prev = cur.nxt = null;
            } else if (op == 2) { // 단일 노드인 y번 노드를 x번 노드 앞에 삽입
                int x = sc.nextInt();
                int y = sc.nextInt();

                Node nodeX = nodes[x];
                Node nodeY = nodes[y];

                connect(nodeX.prev, nodeY);
                connect(nodeY, nodeX);
            } else if (op == 3) { // 단일 노드인 y번 노드를 x번 노드 뒤에 삽입
                int x = sc.nextInt();
                int y = sc.nextInt();

                Node nodeX = nodes[x];
                Node nodeY = nodes[y];

                connect(nodeY, nodeX.nxt);
                connect(nodeX, nodeY);
            } else if (op == 4) { // x번 노드의 이전 노드와 다음 노드의 번호를 출력
                int x = sc.nextInt();

                if (null != nodes[x].prev) {
                    Node prevNode = nodes[x].prev;
                    sb.append(prevNode.value);
                } else {
                    sb.append(0);
                }

                sb.append(" ");

                if (null != nodes[x].nxt) {
                    Node nextNode = nodes[x].nxt;
                    sb.append(nextNode.value);
                } else {
                    sb.append(0);
                }

                sb.append("\n");
            }
        }

        // 연산 모두 한 후, 1번부터 N번까지 각 노드의 다음 노드의 번호를 차례대로 한 줄에 출력한다.
        for (int i = 1; i <= n; i++) {
            if (nodes[i].nxt != null) {
                sb.append(nodes[i].nxt.value).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb);
    }
}
