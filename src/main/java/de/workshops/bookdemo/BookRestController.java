package de.workshops.bookdemo;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.ResourceLoader;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Validated
public class BookRestController {

    private final ObjectMapper mapper;   
    
    private final ResourceLoader resourceLoader;

    private List<Book> books;
    
    
    @PostConstruct
    public void init() throws IOException {
        final var resource = resourceLoader.getResource("classpath:books.json");
        this.books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return this.books;
    }

    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable @Size(min = 13, message = "min 13 Zeichen") String isbn) throws BookException {
        return this.books.stream().filter(book -> hasIsbn(book, isbn)).findFirst().orElseThrow(() -> new BookException("Fehler"));
    }

    @GetMapping(params = "author")
    public Book getBookByAuthor(@RequestParam String author) {
        return this.books.stream().filter(book -> hasAuthor(book, author)).findFirst().orElseThrow();
    }

    @PostMapping("/search")
    public Book searchBook(@RequestBody BookSearchRequest request) {
        return this.books.stream().filter(book -> search(book, request)).findFirst().orElseThrow();
    }

    private boolean search(Book book, BookSearchRequest request) {
        return hasIsbn(book, request.getIsbn()) && hasAuthor(book, request.getAuthor());
    }

    private boolean hasIsbn(Book book, String isbn) {
        return book.getIsbn().equals(isbn);
    }
    
    private boolean hasAuthor(Book book, String author) {
        return book.getAuthor().contains(author);
    }

}