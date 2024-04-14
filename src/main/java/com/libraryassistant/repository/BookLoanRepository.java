package com.libraryassistant.repository;

import com.libraryassistant.entity.Book;
import com.libraryassistant.entity.BookLoan;
import com.libraryassistant.entity.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@ComponentScan
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
//    List<BookLoan> findByUser(User user);
//    List<BookLoan> findByBook(Book book);
//    List<BookLoan> findByBorrowedDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT bl FROM BookLoan bl WHERE bl.user.id = :id ")
    List<BookLoan> findByUserId(@Param("id") Long id);

    @Query("SELECT bl.book.id FROM BookLoan bl WHERE bl.borrowedDate BETWEEN :startDate AND :endDate GROUP BY bl.book.id ORDER BY COUNT(bl.book.id) DESC LIMIT 1")
    Long findMostPopularBookId(LocalDate startDate, LocalDate endDate);

    @Query("SELECT bl.user.id FROM BookLoan bl WHERE bl.borrowedDate BETWEEN :startDate AND :endDate GROUP BY bl.user.id ORDER BY COUNT(bl.user.id) DESC LIMIT 1")
    Long findUserWithMostBooksRead(LocalDate startDate, LocalDate endDate);

    @Query("SELECT bl FROM BookLoan bl WHERE bl.book.id = :id ")
    List<BookLoan> findByBookId(Long id);

    @Query("SELECT bl FROM BookLoan bl WHERE bl.user = ?1 AND bl.book = ?2")
    List<BookLoan> findByUserAndBook(@Param("user") User user, @Param("book") Book book);

}
