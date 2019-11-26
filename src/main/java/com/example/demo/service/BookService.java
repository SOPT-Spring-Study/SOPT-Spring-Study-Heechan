package com.example.demo.service;

import com.example.demo.mapper.BookMapper;
import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public boolean saveBook(Book newBook) {
        return bookMapper.insertBook(newBook) != 0;
    }

    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    public Book getBookById(int bookId) {
        return bookMapper.getBookById(bookId);
    }

    public boolean putBook(int bookId, Book puttedBook) {
        return bookMapper.updateBook(bookId, puttedBook) != 0;
    }

    public boolean deleteBook(int bookId) {
        return bookMapper.deleteBook(bookId) != 0;
    }
}