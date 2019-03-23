package test_ex.calculator;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CalculatorTest4 {
    private ICalc calc;
    private NumberProvader numberProvader;

    @Before
    public void setUp() {
        numberProvader = Mockito.mock(NumberProvader.class);
        calc = new Calculator(numberProvader);
    }

    @After
    public void tearDown() {
        calc = null;
    }

    @Test
    public void test_sum() {
        int result = calc.add(2, 2);
        Assert.assertEquals(4, result);
    }

    @Test
    public void test_sum1() {
        int result = calc.add(5, 10);
        Assert.assertEquals(15, result);
    }

    @Test
    public void test_sub() {
        Assert.assertThat(calc.sub(5, 12), Is.is(-7));
    }

    @Test
    public void test_mul() {
        Assert.assertThat(calc.mul(2, 3), Is.is(6));
    }

    @Test(timeout = 100L)
    public void test_div() {
        Assert.assertThat(calc.div(6, 3), Is.is(2));
    }

    @Test
    public void test_div1() {
        Assert.assertThat(calc.div(0, 3), Is.is(0));
    }

    @Test(expected = ArithmeticException.class)
    public void test_div2() {
        Assert.assertThat(calc.div(3, 0), Is.is(0));
    }

    @Test
    public void testMock() {
        Mockito.when(numberProvader.getNumber())
                .thenReturn(2, 6);
        int result = calc.sumFromProvider();
        Assert.assertEquals(8, result);
    }
}