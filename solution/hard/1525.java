import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] locToidx = new int[][] {{2, 2}, {2, 1}, {2, 0}, {1, 2}, {1, 1}, {1, 0}, {0, 2}, {0, 1}, {0, 0}};
		int[][] idxToloc = new int[][] {{8, 7, 6}, {5, 4, 3}, {2, 1, 0}};
		int first = 0;
		int zero = 0;
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				first *= 10;
				int temp = Integer.parseInt(st.nextToken());
				first += temp;
				if (temp == 0) {
					zero = idxToloc[i][j];
				}
			}
		}
		HashMap<Integer, Integer> memo = new HashMap<>();
		memo.put(first, 0);
		Queue<Info> queue = new ArrayDeque<>();
		queue.offer(new Info(zero, first));
		while (!queue.isEmpty()) {
			Info cur = queue.poll();
			int[] idx = locToidx[cur.zeroLoc];
			int r = idx[0], c = idx[1];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
				int nxtzeroLoc = idxToloc[nr][nc];
				int num = cur.status;
				for (int j = 0; j < nxtzeroLoc; j++) {
					num /= 10;
				}
				num %= 10;
				int nxtStatus = cur.status + num * (int) Math.pow(10, cur.zeroLoc) - num * (int) Math.pow(10, nxtzeroLoc);
				if (memo.containsKey(nxtStatus)) continue;
				memo.put(nxtStatus, memo.get(cur.status) + 1);
				queue.offer(new Info(nxtzeroLoc, nxtStatus));
			}
		}
		if (!memo.containsKey(123456780)) System.out.println(-1);
		else System.out.println(memo.get(123456780));
	}
	
	static class Info {
		int zeroLoc;
		int status;
		
		public Info(int zeroLoc, int status) {
			this.zeroLoc = zeroLoc;
			this.status = status;
		}
	}
}
