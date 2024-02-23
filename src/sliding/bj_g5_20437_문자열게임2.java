package sliding;

import java.io.*;
import java.lang.Math;
import java.util.Arrays;

public class bj_g5_20437_문자열게임2 {

	static final StringBuilder sb = new StringBuilder();
	static String str;
	static int[] cnts;
	static int K;
	static int ans3, ans4;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_20437_문자열게임2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			str = br.readLine();
			K = Integer.parseInt(br.readLine());
			ans3 = Integer.MAX_VALUE;
			ans4 = Integer.MIN_VALUE;
            
//          문자열 내 모든 알파벳의 수 초기화
            cnts = new int['z'-'a'+1];
            for(int i=0;i<str.length();i++)
			    ++cnts[str.charAt(i)-'a'];
            
//			시작 인덱스 순회
			for(int i=0;i<str.length();i++) {
//              전체 개수가 K개 미만인 알파벳에서 시작할 경우, 순회 불필요
                if(cnts[str.charAt(i)-'a'] < K)
                    continue;
				
//				종료 인덱스 순회
                int cnt=0;
				for(int j=i;j<str.length();j++) {
					char start = str.charAt(i);
					char end = str.charAt(j);
                    
//                  Task3에서 가장 짧은 문자열을 가지려면 결국 start==end인 경우
//                  사실상 Task4와 동일한 조건
                    if(start == end)
                        ++cnt;
					
//                  cnt==K이면, 이후 모든 start==end 조건에서 cnt>K
//                  한 번 cnt==K 조건을 충족한 경우 이후 순회는 불필요
					if(cnt == K) {
						ans3 = Math.min(ans3, j-i+1);
						ans4 = Math.max(ans4, j-i+1);
                        break;
					}
				}
			}
			
			if(ans3 == Integer.MAX_VALUE || ans4 == Integer.MIN_VALUE)
				sb.append(-1).append("\n");
			else
				sb.append(ans3 + " " + ans4 + "\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}
