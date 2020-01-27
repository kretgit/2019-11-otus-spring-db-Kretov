package ru.otus.homework.dbpractice.books.domain;

import lombok.*;
import ru.otus.homework.dbpractice.authors.domain.Author;
import ru.otus.homework.dbpractice.genres.domain.Genre;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books")
    @SequenceGenerator(name = "books", sequenceName = "books_id_seq", initialValue = 100, allocationSize = 1)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
