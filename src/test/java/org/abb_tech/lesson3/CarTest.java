package org.abb_tech.lesson3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
    }

    @Test
    void changeGear_1() {
        Assertions.assertDoesNotThrow(() -> car.changeGear(1));
    }

    @Test
    void changeGear_2() {
        Assertions.assertDoesNotThrow(() -> car.changeGear(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    void changeGear_Throws(int gear) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> car.changeGear(gear));
    }

}
