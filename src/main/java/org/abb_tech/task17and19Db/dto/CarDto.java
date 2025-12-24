package org.abb_tech.task17and19Db.dto;

public class CarDto {
    private String color;
    private int speed;
    private String name;

    public CarDto(String color, int speed, String name) {
        this.name = name;
        this.color = color;
        this.speed = speed;
    }

    public CarDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private CarDto(CarBuilder builder) {
        this.color = builder.color;
        this.speed = builder.speed;
        this.name = builder.name;
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public static class CarBuilder {
        private String name;
        private String color;
        private int speed;

        public CarBuilder() {
        }

        public CarBuilder color(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder name(String name){
            this.name = name;
            return this;
        }

        public CarBuilder speed(int speed) {
            this.speed = speed;
            return this;
        }

        public CarDto build() {
            return new CarDto(this);
        }

    }

}
