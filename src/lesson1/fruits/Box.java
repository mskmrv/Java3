package lesson1.fruits;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box<E extends Fruit> {
    private String name;
    List<E> fruitsList;

    public Box(String name) {
        this.name = name;
        this.fruitsList = new ArrayList<>();
    }

    public void addFruit(E fruit) {
        fruitsList.add(fruit);
    }

    public float getWeight() {
//        return (float) fruitsList.stream().mapToDouble(fruitsList -> fruitsList.getWeight()).sum();
        
        float sum = 0f;
        for (E fruit : fruitsList) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    public boolean compare(Box<?> box) {
//        return this.getWeight() == box.getWeight();
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    // Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
    public void relocate(Box<E> newBox){
        Iterator<E> iterator = fruitsList.iterator();
        while (iterator.hasNext()){
            E fruit = iterator.next();
            newBox.addFruit(fruit);
            iterator.remove();
        }
    }
}
