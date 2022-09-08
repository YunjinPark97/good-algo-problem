import java.io.*;
import java.util.*;

public class Main {
	
	static int unused = 2, score, cnt;
	static final int MAX = 300000 * 8 + 3, ROOT = 1;
	static String word;
	static int[] isLast = new int[MAX];
	static String[] words = new String[300001];
	static boolean[] found;
	static boolean[][] visited;
	static int[][] nxt = new int[MAX][26];
	static int[][] grid;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[] scoreTable = new int[] {0, 0, 0, 1, 1, 2, 3, 5, 11};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			insert(str, i);
			words[i] = str;
		}
		br.readLine();
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			score = 0; // 30만 * 11 < 21억
			cnt = 0;
			found = new boolean[300001];
			word = "";
			grid = new int[4][4];
			for (int i = 0; i < 4; i++) {
				String s = br.readLine();
				for (int j = 0; j < 4; j++) {
					grid[i][j] = s.charAt(j) - 'A';
				}
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					visited = new boolean[4][4];
					dfs(i, j, 1, nxt[ROOT][grid[i][j]]);
				}
			}
			sb.append(score+" "+word + " " + cnt+"\n");
			if (T > 0) br.readLine();
		}
		System.out.println(sb.toString());
	}
	
	static void insert(String s, int num) {
		int cur = ROOT;
		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'A';
			if (nxt[cur][idx] == 0) {
				nxt[cur][idx] = unused++;
			}
			cur = nxt[cur][idx];
		}
		isLast[cur] = num;
	}
	
	static void dfs(int r, int c, int len, int cur) {
		if (len > 8) return;
		
		if (isLast[cur] > 0 && !found[isLast[cur]]) {
			cnt++;
			score += scoreTable[len];
			found[isLast[cur]] = true;
			if (word.length() < words[isLast[cur]].length()) {
				word = words[isLast[cur]];
			}
			else if (word.length() == words[isLast[cur]].length() && word.compareTo(words[isLast[cur]]) > 0) {
				word = words[isLast[cur]];
			}
		}
		
		visited[r][c] = true;
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i], nc = c + dc[i];
			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
			if (visited[nr][nc]) continue;
			if (nxt[cur][grid[nr][nc]] == 0) continue;
			dfs(nr, nc, len+1, nxt[cur][grid[nr][nc]]);
		}
		visited[r][c] = false;
	}
}
