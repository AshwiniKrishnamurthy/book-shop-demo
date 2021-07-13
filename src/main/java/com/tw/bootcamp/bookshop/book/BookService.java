package com.tw.bootcamp.bookshop.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> fetchAll(Sort order) {
        return bookRepository.findAll(order);
    }

    public List<Book> fetchAll(String order, String sortBy) {
        return order.equalsIgnoreCase("asc") ? bookRepository.findAll(Sort.by(Sort.Order.asc(sortBy))) : bookRepository.findAll(Sort.by(Sort.Order.desc(sortBy)));
    }

}
