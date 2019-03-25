package lesson7.classes_for_tests;

import lesson7.test_framework.my_annotations.AfterSuite;
import lesson7.test_framework.my_annotations.BeforeSuite;
import lesson7.test_framework.my_annotations.Test;
import test_ex.TestEx;

public class TestExJU4TestCase {
    private TestEx dz;

    @BeforeSuite
    public void prepare() {
        dz = new TestEx();
        System.out.println("Выполняется метод @BeforeSuite prepare()");
    }

    @AfterSuite
    public void finish() {
        dz = null;
        System.out.println("Выполняется метод @AfterSuite finish()");
    }

    /*@BeforeSuite // Получим RuntimeException
    public void prepare2() {
        dz = new TestEx();
    }*/

    @Test
    public void test_task1_where_4_is_not_last() {
//        int[] data = {1, 2, 3, 4, 5, 6, 7};
//        Assert.assertArrayEquals(new int[]{5, 6, 7,}, dz.task1(data));
        System.out.println("Что-то выполняется в тестовом методе test_task1_where_4_is_not_last()");
    }

    @Test
    public void test_task1_with_some_4() {
//        int[] data = {1, 4, 3, 4, 5, 6, 4};
//        Assert.assertArrayEquals(new int[]{}, dz.task1(data));
        System.out.println("Что-то выполняется в тестовом методе test_task1_with_some_4()");
    }
}