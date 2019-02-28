package lesson1;

public class ArrayUtills {
    public static <T> void changePlace(T[] array, int position1, int position2) {
        T obj = array[position1];
        array[position1] = array[position2];
        array[position2] = obj;
    }
}
