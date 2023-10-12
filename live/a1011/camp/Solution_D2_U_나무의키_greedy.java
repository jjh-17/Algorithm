package a1011.camp;

import java.util.*;
import java.io.*;
/*
수학적으로 큰 것을 먼저 채우면 쉬워진다. 
  1 2 3 4 5 6 7 8 9 
1       2 x 2 x 2 
2 1 2 1 x 1
7
 (6+5) => 3*3+2  => 6일 +2(짝수에 짝수)
  1 2 3 4 5 6 7 8 9 
1       2 1 2 1  
3 1 2 1  
7
(6+4) => 3*3+1  => 6일 +1(짝수에 홀수)

  1 2 3 4 5 6 7 8 9 
1     1 2 1 2   
4 1 2   
7
(6+3) => 3*3  => 6일 (짝수에 0)

   1 2  3   5   7   9 
3    2    2   2   x 1
9  1
9      1
9          1
9              1
10

1차이에 대하여 (10-3)=7에 반드시 1이 필요 
 (9개의 수 4+ 홀수일때 1) =5
 5>7/2(짝수는 2필요) 
 --> 2*5-1 
*/
public class Solution_D2_U_나무의키_greedy{
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int TC = Integer.parseInt(br.readLine());
        
        for(int tc = 1 ; tc <= TC ; tc++) {
            int N = Integer.parseInt(br.readLine());
            
            int[] heights = new int[N];
            int[] diffHeights = new int[N];
            
            int max = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, heights[i]);
            }
            
            int sum = 0; // max 높이 나무만큼 커야하는 나무 키의 합
            int one=0;   // 맥스와 차이 1
            int even=0;  // 맥스의 차와 차이가 짝수
            int odd=0;   // 맥스의 차와 차이가 홀수 -> 필수적으로 1을 사용
            for(int i = 0 ; i < N ; i++) {
            	diffHeights[i] = max - heights[i];
            	if(diffHeights[i]==1) one++;
            	if(diffHeights[i]!=1 && diffHeights[i]%2==1) {  //1의 죄소개수
            		even+=diffHeights[i]/2;
            		odd++;
            	}
            	if(diffHeights[i]!=0 && diffHeights[i]%2==0) {  //2의 최대개수
            		even+=diffHeights[i]/2;
            	}
                sum += diffHeights[i];
            }
            int ans=0;
            if(one+odd>even) {   
            	ans=(one+odd)*2-1;  // 1 3 5 7 9 홀수 위치에 있다.
            }else {                 // 1+2로 만들수 있음
                ans=(sum/3)*2+sum%3;
            }
           
            System.out.println("#" + tc + " " + ans);
            
        }
    }
}