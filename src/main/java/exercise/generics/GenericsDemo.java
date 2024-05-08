package exercise.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsDemo {
    public static void main(String[] args) {
        // 3종류의 차가 순서대로 bbang()을 할 것이다.
        List<Car> cars = Arrays.asList(new Taxi(), new Bus(), new ElecCar());
        for(Car car : cars){
            car.bbang();
        }

        CarList<Car> carList = new CarList<>();
        carList.add(new Taxi());
        carList.add(new Bus());
        carList.add(new ElecCar());

        for(int i=0; i<carList.size(); i++){
            carList.get(i).bbang();
        }
    }
}

class CarList<T> {

    ArrayList<T> arrayList = new ArrayList<>();

    void add(T data){
        arrayList.add(data);
    }

    int size(){
        return arrayList.size();
    }

    T get(int idx){
        return arrayList.get(idx);
    }

}

class Car{
    void bbang(){
        System.out.println("경적소리");
    }

}

class Taxi extends Car{
    void bbang() {
        System.out.println("뛰뛰");
    }
}

class Bus extends Car{
    void bbang() {
        System.out.println("빵빵");
    }
}

class ElecCar extends Car{
    void bbang() {
        System.out.println("지지직");
    }
}