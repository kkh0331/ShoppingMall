package exercise;

public class ArithmeticEx {
    public static void main(String[] args) {
        int result = divide(10, 0);
    }

    public static int divide(int dividend, int divider){
        int result = 0;
        try{
            result = dividend/divider;
        } catch (ArithmeticException e){
            System.out.println("0으로 나눌 수 없다.");
        }
        return result;
    }
}

//v1
//public class ArithmeticEx {
//    public static void main(String[] args) {
//        int result = 0;
//
//        try{
//            result = 10/0;
//        } catch(ArithmeticException e){
//            System.out.println("0으로 나눌 수 없습니다..");
//        }
//    }
//}
