package samsung;

import java.util.*;
import java.io.*;

public class bj_G3_연구소3_서울_20_지준호 {
	//바이러스 정보 - idx값, 감염된 시간
    static class Virus {
        int i, j, time;

        Virus(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }
    }

    static int N, M;
    static int[][] map; //N*N 공간 상태
    
    //상 우 좌 하
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    
    static List<Virus> viruses = new ArrayList<>(); //
    static Virus[] activated; //활성화된 감염 구역 - 초기 개수 M
    static int minTime = Integer.MAX_VALUE;
    static int notInfected = 0; //현재 감염되지 않은 공간 수(빈 공간)

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("res\\input_bj_G3_연구소3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        activated = new Virus[M];

        // 공간 상태 초기화
        map = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());

            for (int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0)
                    notInfected++;
                else if (map[i][j] == 2)
                    viruses.add(new Virus(i, j, 0));
            }
        }

        if(notInfected==0) {
            System.out.println(0);
        } else {
            comb(0, 0);
            System.out.println(minTime==Integer.MAX_VALUE ? -1 : minTime);
        }
    }

    // M개 바이러스 조합 - 중복 불가
    static void comb(int start, int cnt) {
        if (cnt==M) {
            spreadVirus(notInfected);
            return;
        }

        for (int i=start;i<viruses.size();i++) {
            activated[cnt] = viruses.get(i);
            comb(i+1, cnt+1);
        }
    }

    // BFS
    static void spreadVirus(int emptySpace) {
    	ArrayDeque<Virus> q = new ArrayDeque<>();
        boolean[][] infected = new boolean[N][N];

        for(int i=0;i<M;i++) {
            Virus virus = activated[i];
            infected[virus.i][virus.j] = true;
            q.offerLast(virus);
        }

        while(!q.isEmpty()) {
            Virus virus = q.pollFirst();

            //상 우 하 좌 탐색
            for (int i = 0; i < 4; i++) {
                int ni = virus.i + di[i];
                int nj = virus.j + dj[i];
                
                //N*N 공간 내부, 감염 구역 아님, 벽 아님
                if(0<=ni&&ni<N && 0<=nj&&nj<N && !infected[ni][nj] && map[ni][nj]!=1) {
                	if(map[ni][nj]==0) emptySpace--; //신규 공간이 빈공간인 경우 남은 빈 공간 수 감소
                	
                	if(emptySpace==0) {
                		minTime = Math.min(minTime, virus.time + 1);
                        return;
                	}
                	
                	infected[ni][nj]=true;
                	q.addLast(new Virus(ni, nj, virus.time+1));
                }
            }
        }
        
        
        
    }
}