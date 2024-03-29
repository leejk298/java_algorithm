import java.util.*;
import java.io.*;

/*
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
 */

public class 단지번호붙이기_백준 {
    static int N, count;    // 크기, 개수
    static char[][] map;    // 입력배열
    static boolean[][] visited; // 방문배열
    static List<Integer> list;  // 결과리스트
    static int[] dx = {-1, 1, 0, 0};    // 4방향
    static int[] dy = {0, 0, -1, 1};

    public static void init() throws IOException {  // 초기화

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));   // 입력 버퍼

        N = Integer.parseInt(bf.readLine());    // 크기

        // 초기화
        map = new char[N][N];
        visited = new boolean[N][N];
        list = new ArrayList<>();

        for(int i = 0; i < N; i++)  // 행
            map[i] = bf.readLine().toCharArray();   // 입력배열 저장
    }

    public static boolean isNotValidPos(int x, int y) { // 좌표가 유효한지
        return (x < 0 || x >= N || y < 0 || y >= N);
    }

    public static void BFS(int x, int y) {  // BFS

        Queue<int[]> queue = new LinkedList<>();    // 큐

        queue.offer(new int[] {x, y});  // 시작점 큐에 삽입
        visited[x][y] = true;   // 시작점 방문
        count++;    // 개수 카운트

        while(!queue.isEmpty()) {   // 큐가 비어있지 않으면
            int[] now = queue.poll();   // 하나 꺼내어

            int nowX = now[0], nowY = now[1];   // 현재 좌표
            for(int i = 0; i < 4; i++) {    // 4방향
                int tmpX = nowX + dx[i], tmpY = nowY + dy[i];   // 다음 좌표

                if(isNotValidPos(tmpX, tmpY) || visited[tmpX][tmpY])    // 유효한지
                    continue;

                if(map[tmpX][tmpY] == '1') {    // 1이면
                    visited[tmpX][tmpY] = true; // 방문
                    count++;    // 개수 카운트
                    queue.offer(new int[] {tmpX, tmpY});    // 큐에 삽입
                }
            }
        }
    }

    public static void findCount() {    // 개수 찾기

        for(int i = 0; i < N; i++) {    // 행
            for(int j = 0; j < N; j++) {    // 열
                if(map[i][j] == '1' && !visited[i][j]) {    // 1이고 방문한 적이 없으면
                    BFS(i, j);  // BFS
                    list.add(count);    // 개수 저장
                    count = 0;  // 개수 초기화
                }
            }
        }

        Collections.sort(list); // 오름차순 정렬

        System.out.println(list.size());    // 개수 출력

        for(int i = 0; i < list.size(); i++)    // 개수만큼
            System.out.println(list.get(i));    // 결과 출력
    }

    public static void main(String[] args) throws IOException {

        init(); // 초기화

        findCount();    // 개수 찾기
    }
}
