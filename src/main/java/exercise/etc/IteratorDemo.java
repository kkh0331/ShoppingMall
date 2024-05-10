package exercise.etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> sportStars = new ArrayList<>();

        sportStars.add("김연아");
        sportStars.add("박태환");
        sportStars.add("손흥민");
        sportStars.add("이강인");
        sportStars.add("운동선수");

        for(int i=0; i<sportStars.size(); i++){
            System.out.println(sportStars.get(i));
        }
        System.out.println("=====================");

        Iterator<String> sportStarIterator = sportStars.iterator();
        while(sportStarIterator.hasNext()) {
            System.out.println(sportStarIterator.next());
        }
        System.out.println("=====================");

        for(String sportStar : sportStars){
            System.out.println(sportStar);
        }
        System.out.println("=====================");

        Map<Integer, String> fruits = new HashMap<>();

        fruits.put(1, "딸기");
        fruits.put(2, "포도");
        fruits.put(3, "바나나");
        fruits.put(4, "수박");
        fruits.put(5, "멜론");

        Iterator<String> fruitIterator = fruits.values().iterator();
        while(fruitIterator.hasNext()){
            System.out.println(fruitIterator.next());
        }
        System.out.println("=====================");

        for(String fruit : fruits.values()){
            System.out.println(fruit);
        }
    }
}
