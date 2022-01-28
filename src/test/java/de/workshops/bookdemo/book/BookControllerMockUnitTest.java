package de.workshops.bookdemo.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookControllerMockUnitTest {
    
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookRestController bookRestController;


    @Test
    void test() {
        // prepare
        Book book = Book.builder()
            .author("Autor")
            .description("Desc")
            .isbn("1234567890")
            .title("Titel")
            .build();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);        
        Mockito.when(bookService.loadAllBooks()).thenReturn(bookList);
        
        // exceute
        List<Book> result = bookRestController.getAllBooks();

        // test
        assertEquals(1, result.size());
    }
}
