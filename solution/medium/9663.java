import java.io.*;

public class Main {
	static int N, ans;
	static boolean[] col, diagonal1, diagonal2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new boolean[N];
		diagonal1 = new boolean[2*N+1];
		diagonal2 = new boolean[2*N+1];
		dfs(0);
		System.out.println(ans);
	}
	
    
	static void dfs(int r) {
		if (r >= N) {
			ans++;
			return;
		}
		
		for (int c = 0; c < N; c++) {
			if (col[c] || diagonal1[r+c] || diagonal2[N-1-c+r]) continue;
			col[c] = true;
			diagonal1[r+c] = true;
			diagonal2[N-1-c+r] = true;
			dfs(r+1);
			col[c] = false;
			diagonal1[r+c] = false;
			diagonal2[N-1-c+r] = false;
		}
	}
}
