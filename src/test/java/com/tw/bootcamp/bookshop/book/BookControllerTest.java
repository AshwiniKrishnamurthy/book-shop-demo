package com.tw.bootcamp.bookshop.book;

import com.tw.bootcamp.bookshop.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    UserService userService;

    @Test
    void shouldListAllBooksWhenPresent() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = new Book("title", "author name", 300);
        books.add(book);
        when(bookService.fetchAll("asc","price")).thenReturn(books);

        mockMvc.perform(get("/books?order=asc&sortby=price")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
        verify(bookService, times(1)).fetchAll("asc","price");
    }

    @Test
    void shouldBeEmptyListWhenNoBooksPresent() throws Exception {
        when(bookService.fetchAll("asc","price")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/books?order=asc&sortby=price")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
        verify(bookService, times(1)).fetchAll("asc","price");
    }

    @Test
    void shouldReturnBookQuantity() throws Exception {
        mockMvc.perform(get("/bookqty")).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].quantity").value(20));
    }

    @Test
    void shouldReturnBooksInSpecifiedOrder() throws Exception {
        when(bookService.fetchAll("asc","price")).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/books?order=asc&sortby=price")).
                andExpect(status().isOk());
        verify(bookService,times(1)).fetchAll("asc","price");
    }

}