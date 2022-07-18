import java.io.*;
import java.util.*;

public class Main {

	static int ans = 100;
	static boolean[][] paper = new boolean[10][10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = 0;
		for (int i = 0; i < 10; i++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					paper[i][j] = true;
					total++;
				}
			}
		}
		backTracking(0, 0, 0, new int[] {0, 5, 5, 5, 5, 5}, total);
		System.out.println((ans == 100)? -1: ans);
	}
    
    // r == c == 0부터 시작하여, r == c == 9까지 탐색
	static void backTracking(int cnt, int r, int c, int[] papers, int left) {
        // 남은 1의 수가 0개이면 처리 완료
		if (left == 0) {
			ans = Math.min(ans, cnt);
			return;
		}
        // Note: 범위 벗어나는지 확인은 left == 0 체크 이후에 해야함.
		if (r >= 10) return;
        
        // 못 붙이는 위치라면 다음 자리로 넘어가기
		if (!paper[r][c]) {
			if (c < 9) backTracking(cnt, r, c+1, papers, left);
			else backTracking(cnt, r+1, 0, papers, left);
		}
        
        // l: 색종이 크기
		for (int l = 1; l <= 5; l++) {
            // 범위를 벗어나거나, 못 붙일 경우 -> 더 큰 색종이도 못 붙이니 바로 break
			if (r+l > 10 || c+l > 10 || !canPost(r, c, l)) break;
			if (papers[l] > 0) {
				post(r, c, l);
				papers[l]--;
				if (c < 9) backTracking(cnt+1, r, c+1, papers, left - l*l);
				else backTracking(cnt+1, r+1, 0, papers, left - l*l);
				post(r, c, l);
				papers[l]++;
			}
		}
	}
	
    // r, c를 왼쪽 위 점으로 하여, 크기가 l인 색종이를 붙일 수 있는지 확인하는 method
	static boolean canPost(int r, int c, int l) {
		for (int i = r; i < r + l; i++) {
			for (int j = c; j < c + l; j++) {
				if (!paper[i][j]) return false;
			}
		}
		return true;
	}
	
    // r, c를 왼쪽 위 점으로 하여, 크기가 l인 색종이를 붙이거나 떼는 method
	static void post(int r, int c, int l) {
		for (int i = r; i < r + l; i++) {
			for (int j = c; j < c + l; j++) {
				paper[i][j] ^= true;
			}
		}
	}
}
