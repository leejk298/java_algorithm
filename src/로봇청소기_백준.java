import java.util.*;

/**
 11 10
 7 4 0
 1 1 1 1 1 1 1 1 1 1
 1 0 0 0 0 0 0 0 0 1
 1 0 0 0 1 1 1 1 0 1
 1 0 0 1 1 0 0 0 0 1
 1 0 1 1 0 0 0 0 0 1
 1 0 0 0 0 0 0 0 0 1
 1 0 0 0 0 0 0 1 0 1
 1 0 0 0 0 0 1 1 0 1
 1 0 0 0 0 0 1 1 0 1
 1 0 0 0 0 0 0 0 0 1
 1 1 1 1 1 1 1 1 1 1
 */

public class 로봇청소기_백준 {
    static int N, M, r, c, dir; // 크기, 방향
    static int[][] map; // 입력배열
    static int answer;  // 결과값
    static int[] dx = {-1, 0, 1, 0};    // 4방향
    static int[] dy = {0, 1, 0, -1};

    public static void init() { // 초기화

        Scanner sc = new Scanner(System.in);    // 입력

        N = sc.nextInt();   // 크기
        M = sc.nextInt();
        r = sc.nextInt();   // 좌표
        c = sc.nextInt();
        dir = sc.nextInt(); // 방향

        answer = 1;     // 시작점 포함
        map = new int[N][M];

        for(int i = 0; i < N; i++)  // 행
            for(int j = 0; j < M; j++)  // 열
                map[i][j] = sc.nextInt();   // 저장
    }

    public static boolean isValidPos(int x, int y) {    // 유효한 좌표
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    public static void DFS(int x, int y, int dir) {     // DFS

        map[x][y] = -1; // 청소

        for(int i = 0; i < 4; i++) {    // 4방향
            dir--;  // 반시계
            if(dir == -1)
                dir = 3;

            int tmpX = x + dx[dir]; // 다음 좌표
            int tmpY = y + dy[dir];

            if(!isValidPos(tmpX, tmpY)) // 유효한 지
                continue;

            if(map[tmpX][tmpY] == 0) {  // 청소하지 않았으면
                answer++;   // 숫자 세기
                DFS(tmpX, tmpY, dir);   // DFS 수행

                return;
            }
        }

        // 4방향 다 청소가 되어있으면
        int tmpDir = (dir + 2) % 4;   // 후진
        int tmpX = x + dx[tmpDir];    // 다음 좌표
        int tmpY = y + dy[tmpDir];

        // 유효하고 벽이 아니면
        if(isValidPos(tmpX, tmpY) && map[tmpX][tmpY] != 1)
            DFS(tmpX, tmpY, dir);   // 후진
    }

    public static void main(String[] args) {

        init(); // 초기화

        DFS(r, c, dir); // DFS

        System.out.println(answer); // 결과 출력
    }
}
