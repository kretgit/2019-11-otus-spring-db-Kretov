package ru.otus.homework.dbpractice.authors.domain;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "author")
public class Author {

    @Id
    private String id;

    private String fullName;

}
