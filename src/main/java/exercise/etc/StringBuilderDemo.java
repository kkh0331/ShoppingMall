package exercise.etc;

import java.util.Scanner;

public class StringBuilderDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        String str1 = sc.next();
        String str2 = sc.next();

        System.out.println(str1+str2);

        sb.append(str1).append(str2);
        System.out.println(sb.toString());
    }
}
