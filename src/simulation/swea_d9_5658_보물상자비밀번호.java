package simulation;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d9_5658_보물상자비밀번호 {

	static final StringBuilder sb = new StringBuilder();
	static final Set<String> set = new HashSet<>();
	static final List<Integer> state = new ArrayList<>();
	static final List<String> result = new ArrayList<>();
	static char map[];
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_5658_보물상자비밀번호.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
//			초기화
			state.clear();
			result.clear();
			set.clear();
			
//			입력	
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new char[N];
			
			String input = br.readLine();
			for(int i=0;i<N;i++) {
				map[i] = input.charAt(i);
				state.add(i);
			}
			
//			알고리즘
//			1. 총 N번 돌림
			int R = N/4;
			for(int i=0;i<N;i++) {
//				2. 4변 순회
				for(int j=0;j<4;j++) {
//					3. 변의 각 요소를 순회하며 변의 문자열 연산
					String pwd = "";
					for(int r=0;r<R;r++) pwd+=map[state.get(R*j+r)];
					
//					4. Set에 처음 넣는 비밀번호면 result에 추가
					int L = set.size();
					set.add(pwd);
					if(L<set.size()) result.add(pwd);
				}
				
//				5. 한번 Rotate
				state.add(state.remove(0));
			}
			
//			6. result 내림차순 정렬 후, K번째 요소 추출
			Collections.sort(result, Collections.reverseOrder());
			sb.append(convertTo10(result.get(K-1))).append("\n");
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
//	16진수 문자열을 10진수로 출력
	static int convertTo10(String str) {
		int answer = 0;
		int L = str.length();
		
		for(int i=0;i<L;i++) {
			char c = str.charAt(i);
			int num = c<='9' ? c-'0' : c-'A'+10;	// 곱할 수
			answer += num * (int)Math.pow(16, L-i-1);
		}
		
		return answer;
	}

}