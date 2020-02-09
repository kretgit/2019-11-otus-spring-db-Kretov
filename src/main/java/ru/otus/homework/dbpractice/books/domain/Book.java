package ru.otus.homework.dbpractice.books.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private String id;

    private String name;

    private String desc;

    private String genreId;

    private String authorId;
}
