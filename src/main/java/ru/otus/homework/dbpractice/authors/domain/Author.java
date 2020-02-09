package ru.otus.homework.dbpractice.authors.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    private String id;

    private String fullName;

}
