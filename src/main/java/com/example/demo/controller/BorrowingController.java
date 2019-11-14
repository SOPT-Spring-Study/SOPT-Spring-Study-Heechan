package com.example.demo.controller;

import com.example.demo.model.Borrowing;
import com.example.demo.service.BorrowingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/members/{memberId}/books/{bookId}")
    public Borrowing borrowBook(@PathVariable("memberId") int memberId,
                                @PathVariable("bookId") int bookId) {
        return borrowingService.borrowBook(memberId, bookId);
    }

    @GetMapping("")
    public List<Borrowing> getBorrowing(@RequestParam(value = "memberId", required = false, defaultValue = "0") int memberId,
                                        @RequestParam(value = "bookId", required = false, defaultValue = "0") int bookId) {
        return borrowingService.searchBorrowings(memberId, bookId);
    }

    @PatchMapping("/return/books/{bookId}")
    public Borrowing returnBook(@PathVariable("bookId") int bookId) {
        return borrowingService.returnBook(bookId);
    }

    @PatchMapping("/extension/books/{bookId}")
    public Borrowing extendDate(@PathVariable("bookId") int bookId) {
        return borrowingService.extendDate(bookId);
    }

    @DeleteMapping("/{borrowingId}")
    public Borrowing deleteBorrowing(@PathVariable("borrowingId") int borrowingId) {
        return borrowingService.deleteBorrowing(borrowingId);
    }
}
