package Baekjoon;
import java.util.*;
import java.io.*;

public class P14865_곡선_자르기 {
    static int aCnt, bCnt;
    static ArrayDeque<Node> stack = new ArrayDeque<>();
    static ArrayList<Node> remnant = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i] = new Pair(x, y);
        }

        int before = 0;
        boolean flag = false;
        boolean zFlag = false, tFlag = false;
        boolean reverseFlag = false;

        if (arr[0].py > arr[1].py) {
            zFlag = true;

            if (arr[0].py == arr[n - 1].py) {
                remnant.add(new Node(arr[0].px, arr[n - 1].px, 0));
                tFlag = true;
            }
        }

        for (int i = 0; i < n; i++) {
            if (tFlag && i == 0) continue;

            Pair current = arr[i];

            if (!flag && current.py > 0) {
                before = current.px;
                flag = true;
            } else if (flag) { // 저장해둔 값이 있음
                if (current.py > 0) {
                    continue;
                }

                if (before != arr[i - 1].px) {
                    if (before < arr[i - 1].px) { // 정방향
                        if (reverseFlag) {
                            stack.pop();
                            aCnt++;
                        }

                        zFlag = false;
                        stack.push(new Node(before, arr[i - 1].px, 1)); // 1이 정방향
                    } else { // 역방향
                        if (!stack.isEmpty()) {
                            Node node = stack.peek();

                            if (node.x1 < arr[i - 1].px && before < node.x2) { // 범위 안에 들어감
                                bCnt++;
                                reverseFlag = true;
                                zFlag = false;
                            }
                        } else if (i == n - 1) { // 정방향으로 처리
                            stack.push(new Node(before, arr[i - 1].px, 1));
                        } else {
                            remnant.add(new Node(arr[i - 1].px, before, 0));
                            bCnt++;
                        }
                    }

                    flag = false;
                }
            }
        }

        if (flag && !tFlag) {
            if (before != arr[n - 1].px) {
                if (before < arr[n - 1].px) { // 정방향
                    if (reverseFlag) {
                        stack.pop();
                        aCnt++;
                    }

                    stack.push(new Node(before, arr[n - 1].px, 1)); // 1이 정방향
                } else { // 역방향
                    if (zFlag) {
                        stack.push(new Node(before, arr[n - 1].px, 1));
                    } else {
                        if (!stack.isEmpty()) {
                            Node node = stack.peek();

                            if (node.x1 < arr[n - 1].px && before < node.x2) { // 범위 안에 들어감
                                bCnt++;
                                reverseFlag = true;
                            }
                        }
                    }
                }
            }
        }

        if (reverseFlag) {
            stack.pop();
            aCnt++;
            reverseFlag = false;
        }

        boolean kflag = false;
        if (remnant.size() > 0) {
            kflag = true;
        }

        while (!stack.isEmpty() && kflag) {
            Node ago = stack.peek();

            ArrayList<Node> app = new ArrayList<>();
            for (int i = 0; i < remnant.size(); i++) {
                Node node = remnant.get(i);

                if (ago.x1 < node.x2 && node.x1 < ago.x2) { // 범위 안에 들어감
                    reverseFlag = true;
                    app.add(node);
                }
            }

            if (reverseFlag) {
                stack.pop();
                aCnt++;

                if (app.size() > 0) {
                    for (int i = app.size() - 1; i >= 0; i--) {
                        remnant.remove(app.get(i));
                    }
                }
                reverseFlag = false;
            } else {
                stack.pop();
            }
        }

        aCnt += stack.size();
        bCnt += stack.size();

        System.out.println(aCnt + " " + bCnt);

        /*int before = 0;
        boolean tru = false;
        boolean flag = false;

        if (arr[0].py > arr[1].py) {
            tru = true;
        }

        for (int i = 0; i < n; i++) {
            Pair current = arr[i];

            // y 값이 0보다 작으면 넘어감
            //if (current.py < 0) continue;

            if (!flag && current.py > 0) { // 저장해둔 값이 없음
                before = current.px;
                flag = true;
            } else if (flag) { // 저장해둔 값이 있음
                if (current.py < 0 && before != arr[i - 1].px) {

                    if (before < arr[i - 1].px) {
                        aCnt++;
                        rcandidates.add(new Pair(before, arr[i - 1].px));
                    } else {
                        bCnt++;
                        lcandidates.add(new Pair(current.px, before));
                    }

                    // 값을 저장했으니 flag를 false로 다시 바꿈
                    flag = false;
                }

            }
        }

        if(flag) {
            if (before < arr[n - 1].px) {
                aCnt++;
                rcandidates.add(new Pair(before, arr[n - 1].px));
            } else {
                bCnt++;
                lcandidates.add(new Pair(arr[n - 1].px, before));
            }
        }*/

        //System.out.println(candidates);
        /*if (!tru) {
            System.out.println(aCnt + " " + bCnt);
        } else {
            System.out.println(bCnt + " " + Math.max(aCnt, bCnt));
        }*/
    }

    static class Pair {
        int px;
        int py;

        public Pair(int px, int py) {
            this.px = px;
            this.py = py;
        }

        @Override
        public String toString() {
            return this.px + " " + this.py;
        }
    }

    static class Node {
        int x1;
        int x2;
        int dir;

        public Node(int x1, int x2, int dir) {
            this.x1 = x1;
            this.x2 = x2;
            this.dir = dir;
        }
    }

}


