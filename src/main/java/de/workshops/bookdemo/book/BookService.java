package de.workshops.bookdemo.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final BookJpaRepository bookJpaRepository;

	public List<Book> loadAllBooks() {
		//return bookRepository.findAllBooks();
		List<Book> result = new ArrayList<>();
        bookJpaRepository.findAll().forEach(result::add);
        return result;
	}

    public Book searchBook(BookSearchRequest request) {
        return bookRepository.findBySearchRequest(request);
    }

    public Book createBook(Book book) {
        //return bookRepository.save(book);
        return bookJpaRepository.save(book);
    }
}
