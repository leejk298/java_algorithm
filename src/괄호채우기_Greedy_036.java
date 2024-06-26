import java.util.*;

public class 괄호채우기_Greedy_036 {

    static int mySum(String str) {  // 개수 구하기

        String tmp[] = str.split("[+]"); // [+] 하는 이유: split() 에서 +를 잘 못읽어서 []를 붙여줌

        int sum = 0;    // 개수
        for (int i = 0; i < tmp.length; i++)    // 배열 크기 만큼
            sum = sum + Integer.parseInt(tmp[i]);    // 문자열을 정수로 고쳐서 더해줌

        return sum; // 개수 리턴
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);    // 입력

        String tmp = sc.nextLine();    // 한 줄 스트링 입력받아
        String str[] = tmp.split("-");    // - 를 기준으로 문자배열 생성

        int res = 0;    // 결과값
        for (int i = 0; i < str.length; i++) {    // 배열 크기 만큼
            int sum = mySum(str[i]);    // mySum 함수 호출

            if (i == 0)    // 배열 처음
                res = res + sum;    // 더해주고
            else        // 그 다음부턴
                res = res - sum;    // 빼줌
        }

        System.out.println(res);    // 결과값 출력
    }
}
