package pl.spring.demo.service;

import pl.spring.demo.to.BookTo;

import java.util.List;

import javax.persistence.EntityNotFoundException;

public interface BookService {

    List<BookTo> findAllBooks();
    List<BookTo> findBooksByTitle(String title);
    List<BookTo> findBooksByAuthor(String author);
    BookTo getOne(Long id) throws EntityNotFoundException;

    BookTo saveBook(BookTo book);
    void deleteBook(BookTo book) throws IllegalArgumentException;
}
