package lesson1;

import java.util.Arrays;

public class Lesson1 {
    public static void main(String[] args) {
        //1
        testFirstTask();
    }

    public static void testFirstTask(){
        String[] strArray = {"Один", "Два", "Три"};
        System.out.println(Arrays.toString(strArray));
        ArrayUtills.changePlace(strArray, 1, 2);
        System.out.println(Arrays.toString(strArray));

        System.out.println("-----");

        Integer[] intArray = {1, 2, 3};
        System.out.println(Arrays.toString(intArray));
        ArrayUtills.changePlace(intArray, 1, 2);
        System.out.println(Arrays.toString(intArray));
    }
}
