package de.workshops.bookdemo.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class BookControllerMockIntegrationTest {
    
    @MockBean
    private BookService bookService;

    @Autowired
    private  BookRestController bookRestController;


    @Test
    void testgetAllBooks() {
        Book book = Book.builder()
            .author("Autor")
            .description("Desc")
            .isbn("1234567890")
            .title("Titel")
            .build();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        
        Mockito.when(bookService.loadAllBooks()).thenReturn(bookList);
        
        assertEquals(1, bookRestController.getAllBooks().size());
    }
}
