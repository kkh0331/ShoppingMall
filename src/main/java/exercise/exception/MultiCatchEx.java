package exercise.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiCatchEx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // int 배열, 숫자 여러 개
        int[] cards = {4, 5, 6, 1, 2, 3};
        System.out.println("몇 번째 카드를 뽑으실래요?");
        try{
            int cardIdx = sc.nextInt();
            System.out.printf("뽑은 카드 번호 : %d\n", cardIdx);
            System.out.printf("뽑은 카드에 적힌 숫자 : %d\n", cards[cardIdx]);
        } catch(InputMismatchException e){
            System.out.println("숫자만 입력가능합니다.");
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("그거.. 없는 번호야..");
        }

    }
}
