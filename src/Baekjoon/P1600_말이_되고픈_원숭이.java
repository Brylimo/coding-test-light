package Baekjoon;

import java.util.*;
import java.io.*;

public class P1600_말이_되고픈_원숭이 {
	static int[] dx1 = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy1 = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
	
	static int[] dx2 = new int[] {-1, 0, 1, 0};
	static int[] dy2 = new int[] {0, 1, 0, -1};
	
	static ArrayDeque<int[]> queue = new ArrayDeque<>(); 
	
	static int k, w, h;
	static int[][] arr;
	static int[][] step;
	static boolean[][][] visited;
	
	static void bfs() {
		while (!queue.isEmpty()) {
			
			int[] current = queue.poll();
			
			if (current[0] == h - 1 && current[1] == w - 1) return;
			
			if (current[2] < k) {
				for (int i = 0; i < 8; i++) {
					int nx = current[0] + dx1[i];
					int ny = current[1] + dy1[i];
					
					if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
					
					if (!visited[nx][ny][current[2] + 1] && arr[nx][ny] == 0) {
						visited[nx][ny][current[2] + 1] = true;
						step[nx][ny] = Math.min(step[nx][ny], current[3] + 1);
						queue.offer(new int[] {nx, ny, current[2] + 1, current[3] + 1});
					}
				}
			}
		
			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx2[i];
				int ny = current[1] + dy2[i];
				
				if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
				
				if (!visited[nx][ny][current[2]] && arr[nx][ny] == 0) {
					visited[nx][ny][current[2]] = true;
					step[nx][ny] = Math.min(step[nx][ny], current[3] + 1);
					queue.offer(new int[] {nx, ny, current[2], current[3] + 1});
				}
			}
			 
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		k = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		arr = new int[h][w];
		step = new int[h][w];
		visited = new boolean[h][w][k + 1];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < h; i++) {
			Arrays.fill(step[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < k; i++) {
			visited[0][0][i] = true;
		}
		
		step[0][0] = 0;
		queue.offer(new int[] {0, 0, 0, 0}); // x, y, 몇 번 말로 움직였나, 동작의 수 
		
		bfs();
		
		if (step[h - 1][w - 1] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(step[h - 1][w - 1]);
		}
	}
}
