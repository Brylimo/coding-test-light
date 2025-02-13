package SWEA;
import java.util.*;
import java.io.*;

public class Solved_1767_프로세서_연결하기 {
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	
	static int n, coreCnt, ans = Integer.MAX_VALUE;
	static int[][] arr;
	static ArrayList<int[]> list = new ArrayList<>();
	
	public static void calculate(int idx, int cnt) {
		if (idx == list.size()) { // 종료 조건

			int subCnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == 2) {
						subCnt++;
					}
				}
			}
			
			if (coreCnt < cnt) {
				coreCnt = cnt;
				ans = subCnt;
			} else if (coreCnt == cnt) {
				ans = Math.min(ans, subCnt);
			}
			
			return;
		}
		
		// 현재 인덱스의 좌표를 꺼냄
		int[] current = list.get(idx);
		for (int i = 0; i < 4; i++) { // 해당 방향으로 진행
			
			boolean flag = true;
			ArrayList<int[]> paths = new ArrayList<>();
			
			int sx = current[0];
			int sy = current[1];
			while (true) {
				int nx = sx + dx[i];
				int ny = sy + dy[i];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) break; // 밖을 벗어났다면 성공!
				
				if (arr[nx][ny] == 0) { // 나아갈 수 있음
					paths.add(new int[] {nx, ny});
					
					// 갱신
					sx = nx;
					sy = ny;
				} else { // 나아갈 수 없음
					flag = false;
					break;
				}
			}
			
			if (flag) { // 만약 모두 갈 수 있다면
				for (int j = 0; j < paths.size(); j++) {
					arr[paths.get(j)[0]][paths.get(j)[1]] = 2;
				}
				
    			calculate(idx + 1, cnt + 1);
			} else {
				continue;
			}
			
			
			if (flag) { // 길을 표시 했다면
				for (int j = 0; j < paths.size(); j++) {
					arr[paths.get(j)[0]][paths.get(j)[1]] = 0;
				}
			}
			
		}
		
		// 혹시 안갔을 경우에 대비
		calculate(idx + 1, cnt);

	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
        	n = Integer.parseInt(br.readLine());
        	
        	arr = new int[n][n];
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < n; j++) {
        			arr[i][j] = Integer.parseInt(st.nextToken());
        			if (arr[i][j] > 0) { // cell의 값을 모두 저장해둠
        				// 가장자리에 있는 셀들은 제외
        				if (i == 0 || j == 0 || i == n - 1 || j == n - 1) continue;
        				
        				list.add(new int[] {i, j});
        			}
        		}
        	}
        	
        	calculate(0, 0);
        	
        	System.out.printf("#%d %d\n", t, ans);
        	
        	coreCnt = 0;
        	ans = Integer.MAX_VALUE;
        	list.clear();
        }
    }
}
