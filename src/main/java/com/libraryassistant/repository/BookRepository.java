package com.libraryassistant.repository;

import com.libraryassistant.entity.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@ComponentScan
public interface BookRepository extends JpaRepository<Book, Long> {

}
