package de.workshops.bookdemo.book;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final ObjectMapper mapper;   
    private final ResourceLoader resourceLoader;
    private final JdbcTemplate jdbcTemplate;

    private List<Book> books;
    

    @PostConstruct
    public void init() throws IOException {
        final var resource = resourceLoader.getResource("classpath:books.json");
        this.books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    public List<Book> findAllBooks() {
        String sql = "SELECT * FROM books";      
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book findBySearchRequest(BookSearchRequest request) {
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

    public Book save(Book book) {
        this.books.add(book);
        return book;
    }



}
