package a1011.camp;

import java.util.Scanner;

public class Solution_D4_8382_방향전환2 {
	static int T;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		T=scann.nextInt();
		for (int i = 1; i <= T; i++) {
			int x1=scann.nextInt();
			int y1=scann.nextInt();
			int x2=scann.nextInt();
			int y2=scann.nextInt();
			int X=Math.abs(x2-x1);
			int Y=Math.abs(y2-y1);
			int Kun=(X+Y)/2;  //군 (Kun,Kun)
			int val=2*Kun+Math.abs(Kun-X)+Math.abs(Kun-Y);
			System.out.println("#"+i+" "+val);
		}
	}
}
