package exercise.generics;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class MyStackDemo {
    public static void main(String[] args) {
        MyStack<String> stackStr = new MyStack<>();

        System.out.println(stackStr.isEmpty());

        stackStr.push("a");
        stackStr.push("b");
        stackStr.push("c");

        System.out.println(stackStr.pop());
        System.out.println(stackStr.peek());

        System.out.println(stackStr.isEmpty());

        stackStr.printElements();
    }
}

class MyStack<T>{

    List<T> stack = new ArrayList<>();

    boolean isEmpty(){
        return stack.isEmpty();
    }

    void push(T data){
        stack.add(data);
    }

    T pop(){
        if(isEmpty()) throw new EmptyStackException();
        return stack.remove(stack.size()-1);
    }

    T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return stack.get(stack.size()-1);
    }

    void printElements(){
        if(isEmpty()){
            System.out.println("저장된 데이터가 없습니다.");
            return;
        }
        System.out.println("=====Top=====");
        for(int i=stack.size()-1; i>=0; i--){
            System.out.println(stack.get(i));
        }
        System.out.println("=============");
    }

}