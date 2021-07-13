package com.tw.bootcamp.bookshop.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldFetchAllBooks() {
        Book book = new Book("title", "author name", 300);
        bookRepository.save(book);

        List<Book> books = bookService.fetchAll("asc","price");

        assertEquals(1, books.size());
        assertEquals("title", books.get(0).getName());
    }

    @Test
    void shouldFetchAllBooksBeSortedByPrice() {
        Book lowPrice = new Book("title", "author name", 300);
        Book highPrice = new Book("costlier", "author name", 400);
        bookRepository.save(lowPrice);
        bookRepository.save(highPrice);

        List<Book> books = bookService.fetchAll("desc","price");

        assertEquals(2, books.size());
        assertEquals("costlier", books.get(0).getName());
    }

    @Test
    void shouldFetchAllBooksSortedByPriceInAscending() {
        Book lowPrice = new Book("title", "author name", 300);
        Book highPrice = new Book("costlier", "author name", 400);
        bookRepository.save(highPrice);
        bookRepository.save(lowPrice);

        List<Book> books = bookService.fetchAll("asc","price");
        assertEquals(2, books.size());
        assertEquals(300, books.get(0).getPrice());
    }
}