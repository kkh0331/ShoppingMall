package exercise.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AgeThrowCheckerEx {
    public static void main(String[] args) throws IOException {
        //숫자 입력
        //당신의 나이는 00살이시네요.반갑습니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            try {
                System.out.println("나이를 입력하세요. 범위는 0 ~ 100");
                int age = Integer.parseInt(br.readLine());
                if (age == -1)
                    break;

                if (age < 0 || age > 100)
                    throw new InputBoundErrorException("0~100 사이로 입력해주세요...");

                System.out.printf("당신의 나이는 %d살이시네요. 반갑습니다.", age);
            } catch (NumberFormatException e) {
                System.out.println("키보드 입력이 잘못 되었습니다.");
            } catch (InputBoundErrorException e) {
                System.out.println(e.getMessage());
            }
        }
        br.close();
    }
}
