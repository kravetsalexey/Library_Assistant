package com.libraryassistant.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private Integer count;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.count = 0;
    }
}
