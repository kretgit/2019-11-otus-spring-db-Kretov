package ru.otus.homework.dbpractice.books.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.homework.dbpractice.authors.domain.Author;
import ru.otus.homework.dbpractice.genres.domain.Genre;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "book")
public class Book {

    @Id
    private String id;

    private String name;

    private String description;

    private Genre genre;

    private Author author;
}
