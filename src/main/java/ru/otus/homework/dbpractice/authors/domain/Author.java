package ru.otus.homework.dbpractice.authors.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors")
    @SequenceGenerator(name = "authors", sequenceName = "authors_id_seq", initialValue = 100, allocationSize = 1)
    private long id;

    @Column(name = "full_name")
    private String fullName;

}
