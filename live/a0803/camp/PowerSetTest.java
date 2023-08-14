package a0803.camp;

import java.util.*;
import java.io.*;

public class PowerSetTest {
	
	static int N, TARGET, input[];
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); TARGET = Integer.parseInt(st.nextToken());
		input = new int[N];
		isSelected = new boolean[N];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) input[i] = Integer.parseInt(st.nextToken());
		
		generateSubset(0);
		System.out.println();
		generateSubset2(0, 0, 0);
		
	}
	
	//모든 부분 집합
	private static void generateSubset(int cnt) {
		if(cnt==N) {
			for(int i=0;i<N;i++) {
				if(isSelected[i])
					System.out.print(input[i] + "\t");
			}
			System.out.println();
			return;
		}
		
		isSelected[cnt] = true;
		generateSubset(cnt+1);
		
		isSelected[cnt] = false;
		generateSubset(cnt+1);
	}
	
	
	//원소의 합이 타겟 넘버가 되는 조합 찾기
	private static void generateSubsetTarget(int cnt) {
		if(cnt==N) {
			int temp=0;
			for(int i=0;i<N;i++) {
				if(isSelected[i])
					temp+=input[i];
			}
			
			if(temp==TARGET) {
				for(int i=0;i<N;i++) {
					if(isSelected[i])
						System.out.print(input[i] + "\t");
				}
				System.out.println();
			}
			return;
		}
		
		isSelected[cnt] = true;
		generateSubsetTarget(cnt+1);
		
		isSelected[cnt] = false;
		generateSubsetTarget(cnt+1);
	}
	
	//직전까지 고려된 원소 개수, 직전까지 선택된 원소들의 합, 직전까지의 원소 선택 개수
	private static void generateSubset2(int cnt, int sum, int choice) {
		if(cnt==N) {	
			if(choice>0 && sum==TARGET) {
				for(int i=0;i<N;i++) {
					if(isSelected[i])
						System.out.print(input[i] + "\t");
				}
				System.out.println();
			}
			return;
		}
		
		isSelected[cnt] = true;
		generateSubset2(cnt+1, sum+input[cnt], choice + 1);
		
		isSelected[cnt] = false;
		generateSubset2(cnt+1, sum, choice);
	}

}
