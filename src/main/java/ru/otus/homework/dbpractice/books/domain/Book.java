package ru.otus.homework.dbpractice.books.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private String authorId;

    private String genreId;
}
