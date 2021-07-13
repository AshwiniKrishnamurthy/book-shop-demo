package com.tw.bootcamp.bookshop.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    List<Book> list(@RequestParam("order") String order, @RequestParam("sortby") String sortby) {
        return bookService.fetchAll(order,sortby);
    }

    @RequestMapping("/bookqty")
    public List<Book> booksWithQuantity() {
        return Arrays.asList(new Book(1L,"abc","def",10,20));
    }
}
