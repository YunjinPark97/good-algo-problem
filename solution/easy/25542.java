import java.io.*;
import java.util.*;

/*
첫 번째 단어와 최대 1개가 차이나는 단어를 만들어 브루트포스로 검사
*/
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		String[] stores = new String[N];
		for (int i = 0; i < N; i++) {
			stores[i] = br.readLine();
		}
		
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < 26; j++) {
				char c = (char) (j + 'A');
				int similar = 0;
				for (int k = 1; k < N; k++) {
					int cnt = 0;
					for (int l = 0; l < L; l++) {
						if (l == i) {
							if (c == stores[k].charAt(l)) cnt++; 
						} else {
							if (stores[0].charAt(l) == stores[k].charAt(l)) cnt++;
						}
					}
					if (cnt >= L-1) similar++;
				}
				if (similar == N-1) {
					String ans = stores[0].substring(0, i) + c + stores[0].substring(i+1);
					System.out.println(ans);
					return;
				}
			}
		}
		
		System.out.println("CALL FRIEND");	
	}
}
