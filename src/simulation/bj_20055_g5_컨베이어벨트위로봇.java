package simulation;

import java.util.*;
import java.io.*;

public class bj_20055_g5_컨베이어벨트위로봇 {

    static final StringBuilder sb = new StringBuilder();
    static final List<Integer> belt = new ArrayList<>();
    static final List<Integer> robots = new ArrayList<>();
    static int N, K, total0, step;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res\\input_bj_20055_g5_컨베이어벨트위로봇.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

//      입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++) belt.add(Integer.parseInt(st.nextToken()));

//      알고리즘
        total0 = 0; step = 0;
        solution();

//      출력
        sb.append(step);
        System.out.println(sb.toString());
        br.close();
    }
    
//  알고리즘
    static void solution(){
        while(true) {
            ++step;

//          1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
//              ==> 내리는 위치에 있는 로봇은 내린다.
            belt.add(0, belt.remove(2*N-1));
            robots.replaceAll(Integer -> (Integer + 1) % (2 * N));
            int res = robots.indexOf(N-1);
            if(res!=-1) robots.remove(res);

//          2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동
//              ==> 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상
            for(int i=0;i<robots.size();i++) {
                int ni = (robots.get(i) + 1) % (2 * N);
                if(!robots.contains(ni) && belt.get(ni)>=1){
                    int ndur = belt.get(ni)-1;
                    if(ndur==0) ++total0;
                    belt.set(ni, ndur); robots.set(i, ni);
                    if(ni==N-1) robots.remove(i--);
                }
            }

//          3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if(!robots.contains(0) && belt.get(0)>0){
                belt.set(0, belt.get(0)-1);
                robots.add(0);
                if(belt.get(0)==0) ++total0;
            }

//          4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
            if(total0>=K) break;
        }
    }

}
