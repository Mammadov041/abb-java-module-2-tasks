package org.abb_tech.module1lesson9.classes;

import java.util.Comparator;

public class Book implements Comparable<Book>{
    private String title;
    private String author;
    private int year;
    private double rating; // 0.0 - 5.0
    private boolean isAvailable;

    private static final Comparator<Book> comparator =
            Comparator.comparing(Book::getRating, Comparator.reverseOrder())
                    .thenComparing(Book::getYear)
                    .thenComparing(Book::getTitle);

    public Book(String title,String author,int year,double rating,boolean isAvailable){
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setRating(rating);
        setIsAvailable(isAvailable);
    }

    @Override
    public String toString() {
        return String.format("===== BOOK INFO =====\n===== TITLE:%s =====\n===== AUTHOR:%s =====\n===== YEAR:%d =====\n===== RATING:%f =====\n===== IS AVAILABLE:%s ===== \n\n",
                this.title,this.author,this.year,this.rating,this.isAvailable);
    }

    @Override
    public int compareTo(Book other) {
        return  comparator.compare(this,other);
    }

    // setters
    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    // getters
    public double getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }
}