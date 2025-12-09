package org.abb_tech.lesson3;

public class Car {
    public void changeGear(int gear) {
        if (gear == 1) {
            System.out.println("going with 1");
        }
        if (gear == 2) {
            System.out.println("going with 2");
        }

        if (gear == 3 || gear == 4 || gear == 5) {
            throw new IllegalArgumentException("gear is 3 or 4 is not Allowed");
        }
    }
}

