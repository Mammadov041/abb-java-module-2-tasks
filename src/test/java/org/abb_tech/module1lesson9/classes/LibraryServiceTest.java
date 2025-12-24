package org.abb_tech.module1lesson9.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService libraryService;
    private User user1;
    private User user2;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        // Create books
        book1 = new Book("Java", "John", 2020, 4.7, true);
        book2 = new Book("Python", "John", 2019, 4.9, true);
        book3 = new Book("C++", "Alex", 2021, 4.2, true);

        // Create users with borrow history
        user1 = new User("Alice", 25, new ArrayList<>());
        user2 = new User("Bob", 30, new ArrayList<>());

        user1.getBorrowHistory().add(new BorrowRecord(book1, LocalDate.of(2024, 1, 10), null));
        user1.getBorrowHistory().add(new BorrowRecord(book2, LocalDate.of(2024, 1, 15), LocalDate.of(2024, 2, 1)));

        user2.getBorrowHistory().add(new BorrowRecord(book1, LocalDate.of(2024, 1, 12), LocalDate.of(2024, 1, 20)));
        user2.getBorrowHistory().add(new BorrowRecord(book3, LocalDate.of(2024, 2, 10), null));

        // Initialize LibraryService
        libraryService = new LibraryService(Arrays.asList(book1, book2, book3), Arrays.asList(user1, user2));
    }

    @Test
    void testGetMostBorrowedBook() {
        Book mostBorrowed = libraryService.getMostBorrowedBook();
        assertEquals("Java", mostBorrowed.getTitle(), "Most borrowed book should be Java");
    }

    @Test
    void testGetUsersCurrentBooks() {
        Map<String, List<Book>> currentBooks = libraryService.getUsersCurrentBooks();

        assertEquals(1, currentBooks.get("Alice").size());
        assertEquals("Java", currentBooks.get("Alice").get(0).getTitle());

        assertEquals(1, currentBooks.get("Bob").size());
        assertEquals("C++", currentBooks.get("Bob").get(0).getTitle());
    }

    @Test
    void testGroupBooksByAuthor() {
        Map<String, List<Book>> grouped = libraryService.groupBooksByAuthor();
        assertEquals(3, grouped.get("John").size());
    }

    @Test
    void testCalculateAverageRating() {
        double avg = libraryService.calculateAverageRating();
        double expected = (4.7 + 4.9 + 4.2) / 3;
        assertEquals(expected, avg, 0.0001);
    }

    @Test
    void testFindRecommendedBookForUser() {
        Optional<Book> recommended = libraryService.findRecommendedBookForUser(user1);

        assertTrue(recommended.isPresent());
        assertEquals("Python", recommended.get().getTitle(), "Recommended book should be highest rated by favorite author");
    }

    @Test
    void testGetAuthors() {
        Set<String> authors = libraryService.getAuthors();

        assertEquals(Set.of("John", "Alex"), authors);
    }

    @Test
    void testFindTopReaderOfMonth() {
        Optional<User> topReader = libraryService.findTopReaderOfMonth(1, 2024);

        assertTrue(topReader.isPresent());
        assertEquals("Alice", topReader.get().getName(), "Top reader of Jan 2024 should be Alice");
    }

    @Test
    void testAfter2000AndIsAvailable_printsCorrectly() {
        // Just ensure it runs without error; manual visual check needed for println
        libraryService.after2000AndIsAvailable();
    }
}
