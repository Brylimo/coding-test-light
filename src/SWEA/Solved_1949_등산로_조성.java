package SWEA;

import java.util.*;
import java.io.*;

public class Solved_1949_등산로_조성 {
	
	static int n, k, ans;
    static int[][] grid;
    static ArrayList<Integer> candidates = new ArrayList<>();
    
    static void calculate(int x, int y) {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        
        for (int i = 0; i < 4; i++) {
        	int nx = x + dx[i];
        	int ny = y + dy[i];
        	
        	if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
        	
        	if (grid[x][y] > grid[nx][ny]) { // 진행 가능
        		candidates.add(grid[nx][ny]);
        		calculate(nx, ny);
        		candidates.remove(candidates.size() - 1);
        	} else {
            	ans = Math.max(ans, candidates.size());
            	continue;
        	}
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            grid = new int[n][n];

            int max = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, grid[i][j]);
                }
            }

            // 가장 높은 위치 저장
            ArrayList<int[]> maxList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == max) {
                        maxList.add(new int[]{i, j});
                    }
                }
            }

            // 아무런 변화가 없이 진행
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    for (int c = 0; c < maxList.size(); c++) {
                    	
                    	candidates.add(grid[maxList.get(c)[0]][maxList.get(c)[1]]);
                    	calculate(maxList.get(c)[0], maxList.get(c)[1]);
                    	candidates.remove(candidates.size() - 1);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                	int org = grid[i][j]; // 원래 값을 저장해둠
                    for (int z = org - 1; z >= org - k; z--) {
                        grid[i][j] = z; // 높이를 줄임

	                    for (int c = 0; c < maxList.size(); c++) {
	                    	
	                    	candidates.add(grid[maxList.get(c)[0]][maxList.get(c)[1]]);
	                    	calculate(maxList.get(c)[0], maxList.get(c)[1]);
	                    	candidates.remove(candidates.size() - 1);
	                    }
                    }

                    grid[i][j] = org;
                }
            }

            System.out.printf("#%d %d\n", t, ans);

            ans = 0;
        }
    }
}
