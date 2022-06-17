import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] houses = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				houses[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < N; i++) {
			houses[i][0] += Math.min(houses[i-1][1], houses[i-1][2]);
			houses[i][1] += Math.min(houses[i-1][0], houses[i-1][2]);
			houses[i][2] += Math.min(houses[i-1][0], houses[i-1][1]);
		}
		
		System.out.println(Math.min(Math.min(houses[N-1][0], houses[N-1][1]), houses[N-1][2]));
	}

}
