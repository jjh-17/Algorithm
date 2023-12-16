package simulation;

import java.io.*;
import java.util.*;


public class bj_17478_재귀함수가뭔가요 {

	static int limit;
	static final StringBuffer sb = new StringBuffer();
	static final String bracket = "---";
	
	public static void recursion(int cnt, String b) {
		if(cnt>limit)
			return;
		
		sb.append(b).append("\"재귀함수가 뭔가요?\"\n");
		sb.append(b).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		sb.append(b).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
		sb.append(b).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		recursion(cnt+1, b+bracket);
		sb.append(b).append("라고 답변하였지.\n");
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		limit = Integer.parseInt(br.readLine());
		
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		
		recursion(0, "");
		
		System.out.print(sb.toString());
		
	}

}
