package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.POST)
    public String deleteBook(RedirectAttributes redirectAttributes, @PathVariable Long bookId) {
        try {
            BookTo book = bookService.getOne(bookId);
            bookService.deleteBook(book);
            redirectAttributes.addFlashAttribute("bookTitle", book.getTitle());
            return "redirect:/books/deleted";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute(
                "warning",
                "The book could not be deleted, because it was not found."
            );
        }

        return "redirect:/books";
    }

    @RequestMapping(value = "/books/deleted", method = RequestMethod.GET)
    public String bookDeleted() {
        return "bookDeleted";
    }
}
