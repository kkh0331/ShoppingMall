package exercise.exception;

import java.util.Scanner;

public class NumberFormatEx {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("점수를 입력하세요. 만약에 종료를 원하신다면 q를 입력해 세요주");
            String input = sc.nextLine();

            if(input.equals("q"))
                break;

            try{
                // inputMismatchException : 입력을 받을 때, 잘못된 입력을 받을 때 에러가 발생한다.

                int score = Integer.parseInt(input);

                if(score >= 60){
                    System.out.println("합격입니다.");
                } else {
                    System.out.println("다음에 보자~");
                }
            } catch(NumberFormatException e){
                // 잘못된 문자열을 숫자로 형변활할 때 발생하는 예외 클래스
                System.out.println("NumberFormatException");
            }
        }
        System.out.println("프로그램 종료");
        sc.close();
    }

}