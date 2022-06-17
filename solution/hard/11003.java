import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		Deque<int[]> q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			while(!q.isEmpty() && q.peekLast()[0] > num) q.pollLast();
			
			q.offer(new int[] {num,i});
			if(q.peek()[1] < i -(l-1)) {
				q.poll();
			}
			bw.write(q.peek()[0]+" ");
		}
		bw.flush();
		bw.close();
	}
}