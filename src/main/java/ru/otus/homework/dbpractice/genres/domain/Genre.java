package ru.otus.homework.dbpractice.genres.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genre {
    private String id;

    private String name;

    private String desc;
}
