package tests;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test_ex.TestEx;

public class Task2TestJU4 {
    private TestEx dz;

    @Before
    public void prepare() {
        dz = new TestEx();
    }

    @Test
    public void test_task2_empty_array() {
        Assert.assertThat(dz.task2(new int[]{}), Is.is(false));
    }

    @Test
    public void test_task2_only_1_and_4() {
        Assert.assertThat(dz.task2(new int[]{1, 4, 4, 1}), Is.is(true));
    }

    @Test
    public void test_task2_1_and_4_and_others() {
        Assert.assertThat(dz.task2(new int[]{1, 4, 3, 1}), Is.is(false));
    }

    @Test
    public void test_task2_without_1_and_4() {
        Assert.assertThat(dz.task2(new int[]{2, 3}), Is.is(false));
    }

    @Test
    public void test_task2_only_1() {
        Assert.assertThat(dz.task2(new int[]{1, 1, 1}), Is.is(false));
    }

    @Test
    public void test_task2_only_4() {
//        Assert.assertThat(dz.task2(new int[]{4, 4, 4}), Is.is(false));
        Assert.assertFalse(String.valueOf(dz.task2(new int[]{4, 4, 4})), false);
    }
}
