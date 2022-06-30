import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String str = br.readLine();
            int N = str.length();
            int ans = 0;
            int move = N-1; // 왼쪽으로 쭉 가는 경우
            for (int i = 0; i < N; i++) {
                if (str.charAt(i) > 'A') {
                    int x = str.charAt(i) - 'A';
                    ans += Math.min(26-x, x);
                }
                
                int idx = i+1;
                while (idx < N && str.charAt(idx) == 'A') {
                    idx++;
                }
                // 왼쪽으로 갔다가 오른쪽을 가는 경우
                // 오른쪽으로만 쭉 가는 경우도 포함하고 있음(idx==N인 경우)
                move = Math.min(move, 2*(N-idx) + i); 
                // 오른쪽으로 갔다가 왼쪽으로 가는 경우    
                // 왼쪽으로만 쭉 가는 경우도 포함하고 있음(i == 0인 경우)
                move = Math.min(move, 2*i + (N-idx));
            }
            System.out.println(ans + move);
        }
    }
}