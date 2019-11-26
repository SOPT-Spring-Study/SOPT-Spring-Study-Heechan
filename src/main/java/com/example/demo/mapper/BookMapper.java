package com.example.demo.mapper;

import com.example.demo.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    public List<Book> getAllBooks();

    public Book getBookById(@Param("id") int id);

    public int insertBook(@Param("book") Book book);

    public int updateBook(@Param("id") int id, @Param("book") Book book);

    public int deleteBook(@Param("id") int id);
}
