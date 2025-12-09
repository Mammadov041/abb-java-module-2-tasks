package org.abb_tech.lesson2;

public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public int manipulateNumbers(int num1, int num2, String method) {
        checkInteger(num1);
        switch (method) {
            case "add" -> num1 += num2;
            case "subtract" -> num1 -= num2;
            case "multiply" -> num1 *= num2;
            case "divide" -> num1 /= num2;
            default -> throw new IllegalArgumentException("Invalid method");
        }
        return num1;
    }

    private void checkInteger(int a) {
        if (a == 1) {
            System.out.println("a is 1");
        }
        if (a == 2) {
            System.out.println("a is 2");
        }
        if (a == 4 || a == 5 || a == 6 || a == 7 || a == 8) {
            throw new IllegalArgumentException("a is 4 or 5");
        }
    }
}
