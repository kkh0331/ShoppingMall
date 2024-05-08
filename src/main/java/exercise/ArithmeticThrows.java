package exercise;

public class ArithmeticThrows {
    public static void main(String[] args) {
        try{
            int result = divide(10, 0);
        } catch(ArithmeticException e){
            System.out.println("내가 대신 처리할게...");
        }
    }

    public static int divide(int dividend, int divider) throws ArithmeticException{
        int result = 0;
        result = dividend/divider;
        return result;
    }
}
