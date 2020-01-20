package ru.otus.homework.dbpractice.books.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Book {
    private String id;

    private String name;

    private String desc;

    private String genreId;

    private String authorId;
}
