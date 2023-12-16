package simulation;

import java.util.*;
import java.io.*;

public class swea_1210_Ladder1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_1210_Ladder1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		String[][] Ladder = new String[100][];
		
		int T = 10;
		for(int t=1;t<=10;t++) {
			String testNum = br.readLine();		
			//사다리 정보 저장
			for(int i=0;i<100;i++)
				Ladder[i] = br.readLine().split(" ");
			
			//도착지의 가로, 세로 idx
			int row = 99; //현재 가로줄
			int col = Arrays.asList(Ladder[99]).indexOf("2"); //현재 세로줄
			
			//도착지에서 출발지로 거슬러 올라감
			while(row>0) {
				if(col-1>=0 && Ladder[row][col-1].equals("1")) {
					while(col-1>=0 && Ladder[row][col-1].equals("1"))
						col--;
				} else if(col+1<100 && Ladder[row][col+1].equals("1")) {
					while(col+1<100 && Ladder[row][col+1].equals("1"))
						col++;
				}
				row--;
			}
			sb.append("#").append(testNum).append(" ").append(col).append("\n");
		}
		System.out.println(sb);
	}
}
