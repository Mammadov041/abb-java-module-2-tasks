package org.abb_tech.module1lesson9.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BorrowRecordTest {

    private BorrowRecord record;
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Java", "John Doe", 2020, 4.5, true);
        record = new BorrowRecord(book, LocalDate.of(2024, 1, 10), null);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(book, record.getBook());
        assertEquals(LocalDate.of(2024, 1, 10), record.getBorrowedDate());
        assertNull(record.getReturnedDate());
    }

    @Test
    void testSetterMethods() {
        Book b2 = new Book("C#", "Mike", 2019, 3.9, false);
        record.setBook(b2);
        record.setBorrowedDate(LocalDate.of(2024, 2, 1));
        record.setReturnedDate(LocalDate.of(2024, 2, 20));

        assertEquals(b2, record.getBook());
        assertEquals(LocalDate.of(2024, 2, 1), record.getBorrowedDate());
        assertEquals(LocalDate.of(2024, 2, 20), record.getReturnedDate());
    }

    @Test
    void testToString() {
        String s = record.toString();
        assertTrue(s.contains("BOOK TITLE:Java"));
        assertTrue(s.contains("BORROWED DATE:2024-01-10"));
    }
}
