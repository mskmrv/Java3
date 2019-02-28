package lesson1.fruits;

public class TestDrive {
    public static void main(String[] args) {
        Box<Apple> box1 = new Box<>("Коробка-1");
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());

        System.out.println("box1 weight: " + box1.getWeight());

        Box<Apple> box2 = new Box<>("Коробка-2");
        box1.relocate(box2);
        System.out.println("Пересыпали фрукты из коробки 1 в коробку 2");

        System.out.println("box1 weight: " + box1.getWeight());
        System.out.println("box2 weight: " + box2.getWeight());

    }
}
