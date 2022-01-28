package de.workshops.bookdemo;

import lombok.Value;

@Value
public class BookSearchRequest {
    private String author;
    private String isbn;
}
