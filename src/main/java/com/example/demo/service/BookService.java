package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private int autoIncrement = 1;

    public Book saveBook(Book newBook){
        newBook.setId(autoIncrement++);
        books.add(newBook);

        return newBook;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBook(int bookId) {
        for(Book book : books){
            if(book.getId() == bookId){
                return book;
            }
        }

        return null;
    }

    public Book updateBook(int bookId, Book updatedBook) {
        for(Book book : books){
            if(book.getId() == bookId){
                book.setAuthor(updatedBook.getAuthor());
                book.setName(updatedBook.getName());
                return book;
            }
        }

        return null;
    }

    public Book deleteBook(int bookId) {
        for(Book book : books){
            if(book.getId() == bookId){
                books.remove(book);
                return book;
            }
        }

        return null;
    }
}