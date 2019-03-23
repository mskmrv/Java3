package test_ex.calculator;

public class Calculator implements ICalc {
    NumberProvader numberProvader;

    public Calculator(NumberProvader numberProvader) {
        this.numberProvader = numberProvader;
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }

    @Override
    public int sumFromProvider() {
        int a = numberProvader.getNumber();
        int b = numberProvader.getNumber();
        return add(a, b);
    }
}
