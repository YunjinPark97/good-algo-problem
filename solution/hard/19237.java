import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };
	static int[][][] priorities; 
	static int[][] sharks, smells;
	static boolean[] isDead;
	static int[] rows, cols, dirs;
	static int alive, N, M, k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		priorities = new int[M+1][5][4];
		sharks = new int[N][N];
		smells = new int[N][N];
		isDead = new boolean[M+1];
		rows = new int[M+1];
		cols = new int[M+1];
		dirs = new int[M+1];
		alive = M;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sharks[i][j] = Integer.parseInt(st.nextToken());
				if (sharks[i][j] > 0) {
					rows[sharks[i][j]] = i;
					cols[sharks[i][j]] = j;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			dirs[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					priorities[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int t = 0;
		while (t <= 1000) {
			t++;
			move();
			smellDesc();
			if (alive == 1) break;
		}
		System.out.println((t==1001)? -1: t);
	}
	
	// 이동 및 이전 자리에 냄새 뿌리기, 겹치는 상어 삭제
	static void move() {
		// 현재 이동 후 냄새를 저장할 배열
		int[][] tempSmells = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tempSmells[i][j] = smells[i][j];
			}
		}
        
		// 현재 이동한 상어를 저장할 배열
		int[][] tempSharks = new int[N][N];
		
		for (int i = 1; i <= M; i++) {
			if (isDead[i]) continue;
			chooseDir(i); // 우선순위 방향 찾기
			int r = rows[i], c = cols[i];
			int nr = r + dr[dirs[i]], nc = c + dc[dirs[i]];
			tempSmells[r][c] = 1000*k + i; // 냄새 뿌리기
			// 상어가 있는 경우
			if (tempSharks[nr][nc] > 0) {
				// 잡아먹을 수 있는 경우
				if (tempSharks[nr][nc] > i) {
					isDead[tempSharks[nr][nc]] = true;
					alive--;
					tempSharks[nr][nc] = i;
				} else {
					isDead[i] = true;
					alive--;
				}
            // 상어가 없는 경우
			} else {
				tempSharks[nr][nc] = i;
			}
            // 위치 갱신
			rows[i] = nr;
			cols[i] = nc;
		}
        // 동시에 모든 상어가 움직인 후, 냄새 및 상어 위치 갱신
		smells = tempSmells;
		sharks = tempSharks;
	}
	
	static void chooseDir(int i) {
		int tempVal = Integer.MAX_VALUE, tempDir = 5;
		int r = rows[i], c = cols[i];
		for (int k = 0; k < 4; k++) {
			int nr = r + dr[priorities[i][dirs[i]][k]], nc = c + dc[priorities[i][dirs[i]][k]];
			if (!isIn(nr, nc)) continue;
			int tempSmell = smells[nr][nc] % 1000;
			if (tempVal > 0 && tempSmell == 0) {
				tempVal = tempSmell;
				tempDir = priorities[i][dirs[i]][k];
			} else if (tempVal == Integer.MAX_VALUE && tempSmell == i) {
				tempVal = tempSmell;
				tempDir = priorities[i][dirs[i]][k];
			}
		}
		dirs[i] = tempDir;
	}
	
	// 냄새 감소시키기
	static void smellDesc() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (smells[i][j] > 0) {
					if (smells[i][j] > 2000) smells[i][j] -= 1000;
					else smells[i][j] = 0;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return !(r < 0 || r >= N || c < 0 || c >= N);
	}
}
