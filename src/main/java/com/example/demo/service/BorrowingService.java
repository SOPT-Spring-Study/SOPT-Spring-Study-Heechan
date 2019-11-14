package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Borrowing;
import com.example.demo.model.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BorrowingService {
    private static final int BORROW_PERIOD_DAY = 7;
    private static final int EXTENSION_DAY = 7;

    private List<Borrowing> borrowings = new ArrayList<>();
    private int autoIncrement = 1;

    private final MemberService memberService;
    private final BookService bookService;

    public BorrowingService(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    public Borrowing borrowBook(int memberId, int bookId) {
        Member member = memberService.getMember(memberId);
        Book book = bookService.getBook(bookId);

        if (book.isOut()) {
            return null;
        }

        Borrowing newBorrowing = new Borrowing();
        newBorrowing.setId(autoIncrement++);
        newBorrowing.setMember(member);
        newBorrowing.setBook(book);
        newBorrowing.setStartTime(new Date());
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DATE, BORROW_PERIOD_DAY);
        newBorrowing.setExpiryTime(expiryDate.getTime());

        borrowings.add(newBorrowing);
        book.setOut(true);

        return newBorrowing;
    }

    public List<Borrowing> searchBorrowings(int memberId, int bookId) {
        List<Borrowing> searchedData = new ArrayList<>();

        for (Borrowing borrowing : borrowings) {
            if ((memberId == 0 || memberId == borrowing.getMember().getId())
                    && (bookId == 0 || bookId == borrowing.getBook().getId())) {
                searchedData.add(borrowing);
            }
        }

        return searchedData;
    }

    public Borrowing returnBook(int bookId) {
        for (Borrowing borrowing : borrowings) {
            if (borrowing.getBook().getId() == bookId) {
                borrowing.setReturnTime(new Date());
                borrowing.getBook().setOut(false);
                return borrowing;
            }
        }

        return null;
    }

    public Borrowing extendDate(int bookId) {
        for (Borrowing borrowing : borrowings) {
            if (borrowing.getBook().getId() == bookId) {
                Calendar newExpiryDate = Calendar.getInstance();
                newExpiryDate.setTime(borrowing.getExpiryTime());
                newExpiryDate.add(Calendar.DATE, EXTENSION_DAY);
                borrowing.setExpiryTime(newExpiryDate.getTime());
                return borrowing;
            }
        }

        return null;
    }

    public Borrowing deleteBorrowing(int borrowingId) {
        for (Borrowing borrowing : borrowings) {
            if (borrowing.getId() == borrowingId) {
                borrowings.remove(borrowing);
                return borrowing;
            }
        }

        return null;
    }
}