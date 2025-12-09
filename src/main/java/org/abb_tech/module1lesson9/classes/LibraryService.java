package org.abb_tech.module1lesson9.classes;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private final List<Book> books;
    private final List<User> users;

    public LibraryService(List<Book> books,List<User> users){
        this.books = books;
        this.users = users;
    }

    public Book getMostBorrowedBook() {
        return this.users.stream()
                .flatMap(u -> u.getBorrowHistory().stream())     // Stream<BorrowRecord>
                .map(record -> record.getBook())                 // Stream<Book>
                .collect(Collectors.groupingBy(
                        Book::getTitle,
                        Collectors.counting()
                ))                                               // Map<String title, Long count>
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> findBookByTitle(entry.getKey()))   // title → Book
                .orElse(null);
    }

    public Map<String, List<Book>> getUsersCurrentBooks() {
        return users.stream()
                .flatMap(user ->
                        user.getBorrowHistory().stream()
                                .filter(record -> record.getReturnedDate() == null)
                                .map(record -> Map.entry(user.getName(), record.getBook()))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }


    private Book findBookByTitle(String title) {
        return this.books.stream()
                .filter(b -> b.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public Map<String, List<Book>> groupBooksByAuthor() {
        return this.users
                .stream()
                .flatMap(u -> u.getBorrowHistory().stream())
                .map(BorrowRecord::getBook)
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.toList()
                ));
    }

    public double calculateAverageRating() {
        double sum = books.stream()
                .reduce(
                        0.0,                                 // identity (double)
                        (total, book) -> total + book.getRating(),  // accumulator
                        Double::sum                          // combiner
                );
        return sum / books.size();
    }

    public void after2000AndIsAvailable(){
        books.stream()
                .filter((b) -> b.getIsAvailable() && b.getYear() > 2000)
                .forEach(System.out::println);
    }

    public Optional<Book> findRecommendedBookForUser(User user) {
        if (user.getBorrowHistory().isEmpty()) {
            return Optional.empty();
        }

        // Step 1: Count how many books user read from each author
        Map<String, Long> authorCount = user.getBorrowHistory().stream()
                .map(BorrowRecord::getBook)
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.counting()
                ));

        // Step 2: Find the author user reads most
        String favoriteAuthor = authorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (favoriteAuthor == null) {
            return Optional.empty();
        }

        // Step 3: Get the most rating book from author's book
        return user.getBorrowHistory().stream()
                .map(BorrowRecord::getBook)
                .filter(b -> b.getAuthor().equals(favoriteAuthor))
                .max(Comparator.comparingDouble(Book::getRating));

    }

    public Set<String> getAuthors(){
        return this.users
                .stream()
                .flatMap(user -> user.getBorrowHistory().stream())
                .map(r -> r.getBook().getAuthor())
                .collect(Collectors.toSet());
    }
    public Optional<User> findTopReaderOfMonth(int month, int year) {
        return users.stream()
                .filter(u -> u.getBorrowHistory().stream()
                        .anyMatch(r ->
                                r.getBorrowedDate().getYear() == year &&
                                        r.getBorrowedDate().getMonthValue() == month
                        )
                )
                .max(Comparator.comparingLong(u ->
                        u.getBorrowHistory().stream()
                                .filter(r ->
                                        r.getBorrowedDate().getYear() == year &&
                                                r.getBorrowedDate().getMonthValue() == month
                                )
                                .count()
                ));
    }


    public List<User> getUsers() {
        return users;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}