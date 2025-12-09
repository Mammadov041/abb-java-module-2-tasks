package org.abb_tech.lesson2;

import java.util.Scanner;

public class Calculator {
    static void main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int num1 = input.nextInt();
        System.out.println("Enter another number: ");
        int num2 = input.nextInt();

        System.out.println("Insert a method:");
        String method = input.next();

        CalculatorService calculator = new CalculatorServiceImpl();
        System.out.println("Result: " + calculator.manipulateNumbers(num1, num2, method));

    }
}
