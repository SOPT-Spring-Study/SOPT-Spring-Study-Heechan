package com.example.demo.mapper;

import com.example.demo.model.Borrowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BorrowingMapper {

    public int borrowBook(@Param("memberId") int memberId,
                          @Param("bookId") int bookId,
                          @Param("diff") int diff);

    public List<Borrowing> getAllBorrowings();

    public Borrowing getBorrowingById(@Param("id") int id);

    public List<Borrowing> getBorrowingsByMemberId(@Param("memberId") int memberId);

    public List<Borrowing> getBorrowingsByBookId(@Param("bookId") int bookId);

    public List<Borrowing> getBorrowingsByMemberIdAndBookId(@Param("memberId") int memberId, @Param("bookId") int bookId);

    public int returnBook(@Param("bookId") int bookId);

    public int extendBorrowing(@Param("bookId") int bookId, @Param("diff") int diff);

    public int deleteBorrowing(@Param("id") int id);
}
