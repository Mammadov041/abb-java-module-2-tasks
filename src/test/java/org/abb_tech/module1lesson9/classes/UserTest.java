package org.abb_tech.module1lesson9.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private User user;
    private Book book;
    private BorrowRecord record;

    @BeforeEach
    void setUp() {
        book = new Book("Java", "John", 2020, 4.5, true);
        record = new BorrowRecord(book, LocalDate.of(2024, 1, 10), null);

        user = new User("Alice", 25, new ArrayList<>());
        user.getBorrowHistory().add(record);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Alice", user.getName());
        assertEquals(25, user.getAge());
        assertEquals(1, user.getBorrowHistory().size());
    }

    @Test
    void testSetName() {
        user.setName("Bob");
        assertEquals("Bob", user.getName());
    }

    @Test
    void testSetBorrowHistory() {
        ArrayList<BorrowRecord> newHistory = new ArrayList<>();
        newHistory.add(record);

        user.setBorrowHistory(newHistory);
        assertEquals(newHistory, user.getBorrowHistory());
    }

    @Test
    void testToString() {
        String s = user.toString();
        assertTrue(s.contains("NAME:Alice"));
        assertTrue(s.contains("AGE:25"));
    }
}
