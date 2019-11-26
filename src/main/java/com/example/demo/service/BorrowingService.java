package com.example.demo.service;

import com.example.demo.mapper.BorrowingMapper;
import com.example.demo.model.Book;
import com.example.demo.model.Borrowing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BorrowingService {
    private static final int BORROW_PERIOD_DAY = 7;
    private static final int EXTENSION_DAY = 7;

    private final BorrowingMapper borrowingMapper;

    private final MemberService memberService;
    private final BookService bookService;

    public BorrowingService(BorrowingMapper borrowingMapper, MemberService memberService, BookService bookService) {
        this.borrowingMapper = borrowingMapper;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @Transactional
    public boolean borrowBook(int memberId, int bookId) {
        Book book = bookService.getBookById(bookId);

        if (book.isOut()) {
            return false;
        }

        if (borrowingMapper.borrowBook(memberId, bookId, BORROW_PERIOD_DAY) != 0) {
            book.setOut(true);
            bookService.putBook(book.getId(), book);
            return true;
        } else {
            return false;
        }
    }

    public List<Borrowing> searchBorrowings(int memberId, int bookId) {
        List<Borrowing> borrowings;

        if (memberId == 0 && bookId == 0) {
            borrowings = borrowingMapper.getAllBorrowings();
        } else if (memberId == 1 && bookId == 0) {
            borrowings = borrowingMapper.getBorrowingsByMemberId(memberId);
        } else if (memberId == 0 && bookId == 1) {
            borrowings = borrowingMapper.getBorrowingsByMemberId(memberId);
        } else {
            borrowings = borrowingMapper.getBorrowingsByMemberIdAndBookId(memberId, bookId);
        }

        return borrowings;
    }

    @Transactional
    public boolean returnBook(int bookId) {
        Book book = bookService.getBookById(bookId);

        if (!book.isOut()) {
            return false;
        }

        if (borrowingMapper.returnBook(bookId) != 0) {
            book.setOut(false);
            bookService.putBook(book.getId(), book);
            return true;
        } else {
            return false;
        }
    }

    public boolean extendDate(int bookId) {
        Book book = bookService.getBookById(bookId);

        if (!book.isOut()) {
            return false;
        }

        return borrowingMapper.extendBorrowing(bookId, EXTENSION_DAY) != 0;
    }

    public boolean deleteBorrowing(int borrowingId) {
        return borrowingMapper.deleteBorrowing(borrowingId) != 0;
    }
}