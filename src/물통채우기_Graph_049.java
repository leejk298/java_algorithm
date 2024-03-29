import java.util.*;
import java.io.*;

public class 물통채우기_Graph_049 {
    static int[] sender = {0, 0, 1, 1, 2, 2}; // 6가지 이동 케이스를 표현하기 위한 배열
    static int[] receiver = {1, 2, 0, 2, 0, 1}; // 0->1, 0->2, 1->0, 1->2, 2->0, 2->1
    static boolean visited[][]; // 방문배열
    static boolean res[]; /// 결과배열
    static int now[]; // 물통

    static class AB {   // 내부클래스
        int A, B;   // 매개변수

        public AB(int A, int B) {   // 파라미터 생성자
            this.A = A;
            this.B = B;
        }
    }

    public static void BFS() { // BFS

        Queue<AB> queue = new LinkedList<>();   // 큐

        queue.add(new AB(0, 0));    // 큐에 시작점 삽입
        visited[0][0] = true;   // 방문
        res[now[2]] = true; // 결과배열 갱신

        while (!queue.isEmpty()) {  // 큐가 비어있지 않으면
            AB p = queue.poll();    // 하나 꺼내어

            int A = p.A, B = p.B, C = now[2] - A - B; // C는 전체 물의 양에서 A와 B를 뺀 것
            for (int k = 0; k < 6; k++) {   // 6방향
                int next[] = {A, B, C}; // 다음 물의 양

                next[receiver[k]] += next[sender[k]]; // 갱신
                next[sender[k]] = 0;    // 초기화

                if (next[receiver[k]] > now[receiver[k]]) { // 물이 넘칠 때
                    next[sender[k]] = next[receiver[k]] - now[receiver[k]]; // 초과한 만큼 다시 이전 물통에 넣어줌
                    next[receiver[k]] = now[receiver[k]]; // 대상 물통은 최대로 채워줌
                }

                if (!visited[next[0]][next[1]]) { // A, B 물의 양을 이용해 방문배열 체크
                    visited[next[0]][next[1]] = true;
                    queue.add(new AB(next[0], next[1]));

                    if (next[0] == 0) // A 물의 양이 0일 때 C 물의 양을 결과 배열에 저장
                        res[next[2]] = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // 입력

        now = new int[3]; // 물통 3개 A B C
        now[0] = sc.nextInt(); // A
        now[1] = sc.nextInt(); // B
        now[2] = sc.nextInt(); // C
        visited = new boolean[201][201];
        res = new boolean[201];

        BFS(); // BFS

        for (int i = 0; i < res.length; i++) // 결과배열 크기만큼
            if (res[i]) // 전체 순회
                System.out.print(i + " "); // 출력
    }
}
