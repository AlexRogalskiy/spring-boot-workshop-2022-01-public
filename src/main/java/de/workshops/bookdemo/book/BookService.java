package de.workshops.bookdemo.book;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;

	public List<Book> loadAllBooks() {
		return bookRepository.findAllBooks();
	}

    public Book searchBook(BookSearchRequest request) {
        return bookRepository.findBySearchRequest(request);
    }
}
