package org.abb_tech.module1lesson9.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Java", "John Doe", 2020, 4.5, true);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Java", book.getTitle());
        assertEquals("John Doe", book.getAuthor());
        assertEquals(2020, book.getYear());
        assertEquals(4.5, book.getRating());
        assertTrue(book.getIsAvailable());
    }


    public static Arguments[] testCompareToData() {
        return new Arguments[]{
                Arguments.of(
                        new Book("A", "X", 2020, 4.9, true),
                        new Book("B", "X", 2020, 4.5, true),
                        -1
                ),
                Arguments.of(
                        new Book("A", "X", 2018, 4.5, true),
                        new Book("B", "X", 2020, 4.5, true),
                        -1
                ),
                Arguments.of(
                        new Book("Alpha", "X", 2020, 4.5, true),
                        new Book("Beta", "X", 2020, 4.5, true),
                        -1
                )
        };
    }

    @ParameterizedTest
    @MethodSource("testCompareToData")
    void testCompareTo(Book b1, Book b2, int expected) {
        assertEquals(expected, Integer.signum(b1.compareTo(b2)));
    }

    @Test
    void testToStringContainsFields() {
        String s = book.toString();
        assertTrue(s.contains("TITLE:Java"));
        assertTrue(s.contains("AUTHOR:John Doe"));
        assertTrue(s.contains("YEAR:2020"));
        assertTrue(s.contains("RATING:4.500000"));
        assertTrue(s.contains("IS AVAILABLE:true"));
    }
}
