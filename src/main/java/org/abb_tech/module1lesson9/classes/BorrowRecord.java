package org.abb_tech.module1lesson9.classes;

import java.time.LocalDate;

public class BorrowRecord {
    private Book book;
    private LocalDate borrowedDate;
    private LocalDate returnedDate; // optional, if not returned -> null

    public BorrowRecord(Book book,LocalDate borrowedDate,LocalDate returnedDate){
        setBook(book);
        setBorrowedDate(borrowedDate);
        setReturnedDate(returnedDate);
    }

    @Override
    public String toString() {
        return String.format("===== BORROW RECORD =====\n===== BOOK TITLE:%s =====\n===== BORROWED DATE:%s =====\n===== RETURNED DATE:%s =====\n",
                this.book.getTitle(),this.borrowedDate,this.returnedDate);
    }

    // setters
    public void setBook(Book book){
        this.book = book;
    }

    public void setBorrowedDate(LocalDate borrowedDate){
        this.borrowedDate = borrowedDate;
    }

    public void setReturnedDate(LocalDate returnedDate){
        this.returnedDate = returnedDate;
    }

    // getters
    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }
}