import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int mp, mf, ms, mv;
	static int N, ans, count;
	static int[][] foods;
	static int[] ansIdx, choice;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = Integer.MAX_VALUE;
		ansIdx = new int[N];
		choice = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		foods = new int[N][5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				foods[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, new int[] {0, 0, 0, 0, 0}, 0);
		if (ans == Integer.MAX_VALUE) sb.append(-1);
		else {
			sb.append(ans+"\n");
			for (int i = 0; i < count; i++) {
				sb.append(ansIdx[i]+1+" ");
			}
		}
		System.out.println(sb.toString());
	}

	
	static void comb(int start, int[] info, int cnt) {
		if (satisfy(info)) {
			if (info[4] < ans) {
				count = cnt;
				ans = info[4];
				for (int i = 0; i < cnt; i++) {
					ansIdx[i] = choice[i];
				}
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			choice[cnt] = i;
			for (int j = 0; j < 5; j++) {
				info[j] += foods[i][j];
			}
			comb(i+1, info, cnt+1);
			for (int j = 0; j < 5; j++) {
				info[j] -= foods[i][j];
			}
		}
	}
	
	
	static boolean satisfy(int[] info) {
		return (info[0] >= mp && info[1] >= mf && info[2] >= ms && info[3] >= mv);
	}
}
