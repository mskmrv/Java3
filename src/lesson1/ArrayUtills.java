package lesson1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ArrayUtills {
    public static <T> void changePlace(T[] array, int position1, int position2) {
        T obj = array[position1];
        array[position1] = array[position2];
        array[position2] = obj;
    }

    public static <T> ArrayList<T> getArrayListFromArray(T[] array) {
        /*ArrayList<T> list = new ArrayList<>();
        for (T t : array) {
            list.add(t);
        }*/

        ArrayList<T> list = new ArrayList<>();
        Collections.addAll(list, array);

        return list;
    }
}
