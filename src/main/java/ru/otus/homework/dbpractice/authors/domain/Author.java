package ru.otus.homework.dbpractice.authors.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Author {
    private String id;

    private String fullName;

}
