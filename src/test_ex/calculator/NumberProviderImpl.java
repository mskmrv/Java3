package test_ex.calculator;

import java.util.Scanner;

public class NumberProviderImpl implements NumberProvader{

    @Override
    public int getNumber() {
        try (Scanner scanner = new Scanner(System.in)){
            return scanner.nextInt();
        }
    }
}
