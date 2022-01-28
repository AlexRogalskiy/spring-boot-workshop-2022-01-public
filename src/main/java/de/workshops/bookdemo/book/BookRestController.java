package de.workshops.bookdemo.book;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(BookRestController.REQUEST_URL)
@RequiredArgsConstructor
@Validated
public class BookRestController {

    public static final String REQUEST_URL = "/books";

    private final BookService bookService;


    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.loadAllBooks();
    }

    // @GetMapping("/{isbn}")
    // public Book getBookByIsbn(@PathVariable @Size(min = 13, message = "min 13 Zeichen") String isbn) throws BookException {
    //     return this.books.stream().filter(book -> hasIsbn(book, isbn)).findFirst().orElseThrow(() -> new BookException("Fehler"));
    // }

    // @GetMapping(params = "author")
    // public Book getBookByAuthor(@RequestParam String author) {
    //     return this.books.stream().filter(book -> hasAuthor(book, author)).findFirst().orElseThrow();
    // }

    @PostMapping("/search")
    public Book search(@RequestBody BookSearchRequest request) {
        return bookService.searchBook(request);
    }


}