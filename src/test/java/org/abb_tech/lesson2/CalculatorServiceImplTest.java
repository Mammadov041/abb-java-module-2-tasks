package org.abb_tech.lesson2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceImplTest {

    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    @Test
    void testManipulateNumbers_Add() {
        int actual = calculatorService.manipulateNumbers(1, 2, "add");
        int expected = 3;
        assertEquals(expected, actual, "Add method failed");
    }

    @Test
    void testManipulateNumbers_Subtract() {
        int actual = calculatorService.manipulateNumbers(3, 2, "subtract");
        int expected = 1;
        assertEquals(expected, actual, "Subtract method failed");
    }


    public static Arguments[] testManipulateNumbers_Success() {
        return new Arguments[]{
                Arguments.of(1, 2, "add", 3),
                Arguments.of(3, 1, "subtract", 2),
                Arguments.of(1, 1, "multiply", 1),
                Arguments.of(10, 2, "divide", 5)
        };
    }


    @ParameterizedTest
    @MethodSource("testManipulateNumbers_Success")
    void testManipulateNumbers_Success(int a, int b, String method, int expected) {
        int actual = calculatorService.manipulateNumbers(a, b, method);
        assertEquals(expected, actual, method + "failed");
    }


    public static Arguments[] testManipulationThrows() {
        return new Arguments[]{
                Arguments.of(4, "add"),
                Arguments.of(5, "subtract")};
    }


    @ParameterizedTest
    @MethodSource("testManipulationThrows")
    void testManipulationThrows(int a, String method) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.manipulateNumbers(a, 5, method));
        assertEquals("a is 4 or 5", exception.getMessage());
    }

}


/*
  Has class named Car has method changeGear(int gear);

  if (gear == 1) {
   sout ("going with 1")
  }
  if (gear == 2) {
   sout ("going with 2")
  }

  if (gear == 3 || gear == 4 || gear == 5) {
   throw IllegalArgumentException("gear is 3 or 4 is not Allowed");
  }


 */