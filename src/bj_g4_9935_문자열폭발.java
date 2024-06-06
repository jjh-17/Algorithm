
import java.util.*;
import java.io.*;

public class bj_g4_9935_문자열폭발 {

	static String S, T;
	static final StringBuilder ans = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_9935_문자열폭발.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

//		입력
		S = br.readLine();
		T = br.readLine();
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans.length()>0 ? ans : "FRULA");
		br.close();
	}
	
	static void solution() {
		final ArrayDeque<Character> stack = new ArrayDeque<>();
		final int lenS = S.length();
		final int lenT = T.length();
		final char lastT = T.charAt(lenT-1);
		
//		S 순회
		for(int i=0;i<lenS;i++) {
			char c = S.charAt(i);
			
//			스택에 문자 추가
			stack.offerLast(c);
			
			if(c==lastT && i-lenT+1>=0) {
				boolean matches = true;
				
//				스택의 상위 lenT-1 개와 현재 문자를 비교
                Iterator<Character> iterator = stack.descendingIterator();
                for (int j=lenT-1;j>=0;j--) {
                    if (!iterator.hasNext() || iterator.next()!=T.charAt(j)) {
                        matches = false;
                        break;
                    }
                }
                
                // 매칭되면 스택에서 lenT 개 요소 제거
                if (matches) {
                    for (int j=0;j<lenT;j++)
                        stack.pollLast();
                }
			}
		}
		
		Iterator<Character> iterator = stack.iterator();
		while(iterator.hasNext())
			ans.append(iterator.next());
	}

}
