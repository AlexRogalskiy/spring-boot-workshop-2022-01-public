package de.workshops.bookdemo.book;

import lombok.Value;

@Value
public class BookSearchRequest {
    private String author;
    private String isbn;
}
