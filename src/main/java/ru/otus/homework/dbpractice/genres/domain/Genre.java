package ru.otus.homework.dbpractice.genres.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "genre")
public class Genre {

    @Id
    private String id;

    private String name;

    private String description;
}
