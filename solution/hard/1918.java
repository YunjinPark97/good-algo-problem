import java.io.*;
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String ans = postfix(str);
        System.out.println(ans);
    }
 
    static String postfix(String str) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> opers = new Stack<>();
 
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            // 알파벳일 경우: 정답 문자열에 넣어준다.
            if (cur >= 'A' && cur <= 'Z') {
                sb.append(cur);
            }
            // 연산자일 경우: 우선순위에 따라 처리
            else if (cur != '(' && cur != ')') {
                while (!opers.isEmpty() && priority(opers.peek()) >= priority(cur)) {
                    sb.append(opers.pop());
                }
                opers.add(cur);
            }
            // 여는 괄호일 경우: 그냥 넣어주기
            else if (cur == '(') {
                opers.add(cur);
            }
            // 닫는 괄호일 경우: 여는 괄호를 만나기 전까지 남은 연산자 문자열에 붙이기
            else {
                while (opers.peek() != '(') {
                    sb.append(opers.pop());
                }
                opers.pop(); // 괄호 제거
            }
        }
        // 남은 연산자를 문자열 뒤에 붙이기
        while (!opers.isEmpty()) {
            sb.append(opers.pop());
        }
        return sb.toString();
    }
    
    static int priority(char c) {
        if (c == '*' || c == '/') return 2;
        else if (c == '+' || c == '-') return 1;
        else return 0;
    }
}
