package exercise;

public class ArrayIndexOutOfBoundEx {

    public static void main(String[] args){
        try{
            int[] numbers = {1,2,3};
            System.out.println(numbers[3]);
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("배열 인덱스 모자라..");
            System.out.println(e);
        }
    }

}
