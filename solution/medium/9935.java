import java.io.*;

/*
찾는 문자열의 길이를 N이라고 했을 때,
최근 N개의 문자열을 확인 및 삭제하는 데 걸리는 시간이
O(N)이하인 자료구조를 선택해야 한다.
-> String 대신 StringBuilder를 사용한 이유.
*/
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		String target = br.readLine();
		int N = target.length();
		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i)+"");
			while (sb.length() >= N && sb.substring(sb.length()-N).equals(target)) {
				sb.setLength(sb.length()-N);
			}
		}
		if (sb.length() == 0) System.out.println("FRULA");
		else System.out.println(sb.toString());
	}
}
