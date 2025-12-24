package org.abb_tech.task17and19Db.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Car {

    private String name;
    private String color;
    private int speed;
    private int id;

    public Car(int id, String name, String color, int speed) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.speed = speed;
    }

    public Car(String name, String color, int speed) {
        this.name = name;
        this.color = color;
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

