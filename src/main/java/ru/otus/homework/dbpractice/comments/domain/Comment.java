package ru.otus.homework.dbpractice.comments.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework.dbpractice.books.domain.Book;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genres")
    @SequenceGenerator(name = "genres", sequenceName = "genres_id_seq", initialValue = 100, allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "comment_text")
    private String commentText;
}
