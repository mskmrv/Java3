package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Lesson1 {
    public static void main(String[] args) {
        //1
        testFirstTask();
        //2
        testSecondTask();
    }

    public static void testFirstTask(){
        String[] strArray = getStringArray();
        System.out.println(Arrays.toString(strArray));
        ArrayUtills.changePlace(strArray, 1, 2);
        System.out.println(Arrays.toString(strArray));

        System.out.println("-----");

        Integer[] intArray = getIntArray();
        System.out.println(Arrays.toString(intArray));
        ArrayUtills.changePlace(intArray, 1, 2);
        System.out.println(Arrays.toString(intArray));
    }

    public static void testSecondTask(){
        String[] strArray = getStringArray();
        System.out.println(Arrays.toString(strArray));
        ArrayList<String> stringList = ArrayUtills.getArrayListFromArray(strArray);
        System.out.println(stringList);

        System.out.println("-----");

        Integer[] intArray = getIntArray();
        System.out.println(Arrays.toString(intArray));
        ArrayList<Integer> intList = ArrayUtills.getArrayListFromArray(intArray);
        System.out.println(intList);
    }

    public static String[] getStringArray(){
        String[] strArray = {"Один", "Два", "Три"};
        return strArray;
    }

    public static Integer[] getIntArray(){
        Integer[] intArray = {1, 2, 3};
        return intArray;
    }
}
