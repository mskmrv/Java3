package lesson1.fruits;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box<T extends Fruit> {
    private String name;
    List<T> fruitsList;

    public Box(String name) {
        this.name = name;
        this.fruitsList = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        fruitsList.add(fruit);
    }

    public float getWeight() {
        float sum = 0f;
        for (T fruit : fruitsList) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    // Нужно реализовать метод интерфейса Comparable?
    public boolean compare(Box<T> box) {
        return this.getWeight() == box.getWeight();
    }

    // Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
    public void relocate(Box<T> newBox){
        Iterator<T> iterator = fruitsList.iterator();
        while (iterator.hasNext()){
            T fruit = iterator.next();
            newBox.addFruit(fruit);
            iterator.remove();
        }
    }
}
