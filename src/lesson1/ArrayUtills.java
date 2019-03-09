package lesson1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayUtills {
    public static <T> void changePlace(T[] array, int position1, int position2) {
        if (array == null) {
            throw new IllegalArgumentException("Array is required");
        }
        checkIndex(array, position1);
        checkIndex(array, position2);

        swap(array, position1, position2);
    }

    private static <T> void swap(T[] array, int position1, int position2) {
        T temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
    }

    private static <T> void checkIndex(T[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("Invalid Index");
        }
    }

    public static <T> List<T> getArrayListFromArray(T[] array) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, array);

        return list;
    }
}
