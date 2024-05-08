package exercise.generics;

import java.util.ArrayList;
import java.util.List;

public class ArrayAndListDiff {
    public static void main(String[] args) {
        List<Object> arrayList = new ArrayList<>();
        arrayList.add("gg");
        arrayList.add(3);
        arrayList.add(true);

        System.out.println(arrayList);

        Object[] objects = new Object[3];
        objects[0] = "sksks";
        objects[1] = 2;
        objects[2] = true;

        for(Object object : objects){
            System.out.print(object+" ");
        }


    }
}
