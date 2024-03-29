import java.util.Scanner;

public class 중복없는순열_DFS {
    static int N;    // 총 개수
    static int M;    // 뽑을 개수
    static int A[];  // 주어진 숫자배열
    static int res[]; // 결과배열
    static boolean check[];  // 방문배열

    public static void BackTracking(int v) {    // dfs

        if (v == M) {   // 뽑을 개수와 같으면
            for (int i : res) // 배열 res 를 i로 순회하면서
                System.out.print(i);    // 출력
            System.out.println();   // 개행문자 출력
        } else {    // 뽑을 개수보다 작으면
            for (int i = 0; i < N; i++) {   // 총 개수만큼
                if (check[i] == false) { // 방문하지 않았으면
                    check[i] = true;    // 방문배열 갱신
                    res[v] = A[i];   // 결과배열에 저장
                    BackTracking(v + 1);  // 다음 index 로 dfs 수행
                    check[i] = false;   // dfs(재귀함수) 리턴 후 방문배열 초기화
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);    // 입력

        N = sc.nextInt();   // 크기
        M = sc.nextInt();   // 뽑을 개수
        A = new int[N];     // 입력배열
        res = new int[M];   // 결과배열
        check = new boolean[N]; // 총 개수만큼

        for (int i = 0; i < N; i++) // 크기만큼
            A[i] = sc.nextInt();    // 저장

        BackTracking(0); // 배열 A index 0번부터 dfs 수행
    }
}
