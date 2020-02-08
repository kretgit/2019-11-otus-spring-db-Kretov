package ru.otus.homework.dbpractice.comments.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.homework.dbpractice.books.domain.Book;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "comment")
public class Comment {

    @Id
    private String id;

    private Book book;

    private String commentText;
}
