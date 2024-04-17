package com.libraryassistant.repository;

import com.libraryassistant.entity.BookLoan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@ComponentScan
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

    @Query("SELECT bl.book.id FROM BookLoan bl WHERE bl.returnDate BETWEEN :startDate AND :endDate GROUP BY bl.book.id ORDER BY COUNT(bl.book.id) DESC LIMIT 1")
    Long findMostPopularBookId(LocalDate startDate, LocalDate endDate);

    @Query("SELECT bl.user.id FROM BookLoan bl WHERE bl.returnDate BETWEEN :startDate AND :endDate GROUP BY bl.user.id ORDER BY COUNT(bl.user.id) DESC LIMIT 1")
    Long findUserWithMostBooksRead(LocalDate startDate, LocalDate endDate);

    List<BookLoan> findByUserId(Long user_id);

    List<BookLoan> findByBookId(Long book_id);

    List<BookLoan> findByUserIdAndBookId(Long user_id, Long book_id);

}
