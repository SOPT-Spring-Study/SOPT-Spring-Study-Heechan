package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public Book saveBook(@RequestBody Book newBook){
        return bookService.saveBook(newBook);
    }

    @GetMapping("")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book getBook(@PathVariable("bookId") int bookId){
        return bookService.getBookById(bookId);
    }

    @PutMapping("/{bookId}")
    public Book putBook(@PathVariable("bookId") int bookId,
                        @RequestBody Book puttedBook){
        return bookService.putBook(bookId, puttedBook);
    }

    @DeleteMapping("/{bookId}")
    public Book deleteBook(@PathVariable("bookId") int bookId){
        return bookService.deleteBook(bookId);
    }
}
