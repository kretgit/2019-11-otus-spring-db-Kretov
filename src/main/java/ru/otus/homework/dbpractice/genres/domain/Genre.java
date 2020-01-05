package ru.otus.homework.dbpractice.genres.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Genre {
    private String id;

    private String name;

    private String desc;
}
