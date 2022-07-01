import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int N = str.length();
		StringBuilder sb = new StringBuilder();
		
		boolean isCPP = true;
		// 대문자가 있으면 Java
		for (int i = 0; i < N; i++) {
			if (str.charAt(i) != '_' && (str.charAt(i) > 'z' || str.charAt(i) < 'a')) {
				isCPP = false;
			}
		}
		
		if (isCPP) {
			for (int i = 0; i < N; i++) {
				if (str.charAt(i) == '_' && (i ==0 || i == N-1)) {
					System.out.println("Error!");
					return;
				}
				if (str.charAt(i) == '_' && (str.charAt(i-1) == '_' || str.charAt(i+1) == '_')) {
					System.out.println("Error!");
					return;
				}
			}
			for (int i = 0; i < N; i++) {
				if (str.charAt(i) != '_') {
					sb.append(str.charAt(i)+"");
				} else {
					i++;
					if (i == N) continue;
					sb.append((char)(str.charAt(i)-32)+"");
				}
			}
		} else {
			for (int i = 0; i < N; i++) {
				if (i==0 && str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
					System.out.println("Error!");
					return;
				}
				if (str.charAt(i) == '_') {
					System.out.println("Error!");
					return;
				}
			}
			
			for (int i = 0; i < N; i++) {
				if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
					sb.append(str.charAt(i)+"");
				} else {
					sb.append("_"+(char)(str.charAt(i)+32));
				}
			}
		}
		System.out.println(sb.toString());
	}

}
