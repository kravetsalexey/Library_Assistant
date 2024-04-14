package com.libraryassistant.repository;

import com.libraryassistant.entity.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@ComponentScan
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.title = ?3, b.author = ?2, b.count = ?4 WHERE b.id = ?1")
    void updateBook(Long id, String title, String author,Integer count);
}
