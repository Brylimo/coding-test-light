package Baekjoon;
import java.util.*;
import java.io.*;

public class P2564_경비원 {
    static int[] dirs = new int[] {1, 4, 2, 3};
    static ArrayList<int[]> list = new ArrayList<>();

    static int convertDirIdx(int dir) {
        if (dir == 1) {
            return 0;
        } else if (dir == 2) {
            return 2;
        } else if (dir == 3) {
            return 3;
        } else if (dir == 4) {
            return 1;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int storeCnt = Integer.parseInt(br.readLine());
        for (int i = 1; i <= storeCnt; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = Integer.parseInt(st.nextToken());
            int loc = Integer.parseInt(st.nextToken());

            list.add(new int[]{ dir, loc });
        }

        st = new StringTokenizer(br.readLine());
        int sDirIdx = convertDirIdx(Integer.parseInt(st.nextToken()));
        int sy = Integer.parseInt(st.nextToken());

        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            int tDirIdx = convertDirIdx(list.get(i)[0]);
            int ty = list.get(i)[1];

            int minDist = Integer.MAX_VALUE;
            int sum = 0;
            // 시계방향으로 확인
            for (int j = 0; j < 4; j++) { // 4 방향
                int dirIdx = (sDirIdx + j) % 4;

                if (dirIdx == tDirIdx) { // 같은 위치
                    if (j == 0) { // 같은 면에 있는 경우
                        if (dirIdx == 2 || dirIdx == 3) { // 남, 서
                            if (ty <= sy) {
                                sum += sy - ty;
                                minDist = Math.min(sum, minDist);
                                break;
                            }
                        } else { // 북, 동
                            if (ty >= sy) {
                                sum += ty - sy;
                                minDist = Math.min(sum, minDist);
                                break;
                            }
                        }
                    } else { // 다른 면에 있는 경우
                        if (dirIdx == 0 || dirIdx == 1) { // 북, 동
                            sum += ty;
                            minDist = Math.min(sum, minDist);
                            break;
                        } else { // 남, 서
                            if (dirIdx == 2) {
                                sum += n - ty;
                            } else {
                                sum += m - ty;
                            }
                            minDist = Math.min(sum, minDist);
                            break;
                        }
                    }
                } else { // 다른 위치
                    if (j == 0) {
                        if (dirIdx == 2 || dirIdx == 3) { // 남, 서
                            sum += sy;
                        } else {
                            if (dirIdx == 0) {
                                sum += n - sy;
                            } else {
                                sum += m - sy;
                            }
                        }
                    } else {
                        if (dirIdx == 0 || dirIdx == 2) {
                            sum += n;
                        } else {
                            sum += m;
                        }
                    }

                }
            }

            // 반시계 방향으로 확인
            sum = 0;
            for (int j = 0; j < 4; j++) { // 4 방향
                int dirIdx = (sDirIdx - j + 4) % 4;

                if (dirIdx == tDirIdx) { // 같은 위치
                    if (j == 0) { // 같은 면에 있는 경우
                        if (dirIdx == 2 || dirIdx == 3) { // 남, 서
                            if (ty >= sy) {
                                sum += ty - sy;
                                minDist = Math.min(sum, minDist);
                                break;
                            }
                        } else { // 북, 동
                            if (ty <= sy) {
                                sum += sy - ty;
                                minDist = Math.min(sum, minDist);
                                break;
                            }
                        }
                    } else { // 다른 면에 있는 경우
                        if (dirIdx == 0 || dirIdx == 1) { // 북, 동
                            if (dirIdx == 0) {
                                sum += n - ty;
                            } else {
                                sum += m - ty;
                            }
                            minDist = Math.min(sum, minDist);
                            break;
                        } else { // 남, 서
                            sum += ty;
                            minDist = Math.min(sum, minDist);
                            break;
                        }
                    }
                } else { // 다른 위치
                    if (j == 0) {
                        if (dirIdx == 2 || dirIdx == 3) { // 남, 서
                            if (dirIdx == 2) {
                                sum += n - sy;
                            } else {
                                sum += m - sy;
                            }

                        } else {
                            sum += sy;
                        }
                    } else {
                        if (dirIdx == 0 || dirIdx == 2) {
                            sum += n;
                        } else {
                            sum += m;
                        }
                    }

                }
            }

            // 모두 확인
            ans += minDist;
        }

        System.out.println(ans);
    }
}
