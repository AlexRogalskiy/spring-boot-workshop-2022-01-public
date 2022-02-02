package de.workshops.bookdemo.book;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends CrudRepository<Book, Long> {
    
    public Book findByIsbn(String isbn);

    @Query(value = "SELECT * from books", nativeQuery = true)
    public List<Book> specialNameXY();
}
