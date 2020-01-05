package ru.otus.homework.dbpractice.utils;


import org.springframework.jdbc.core.JdbcOperations;

public class IdGenerator {

    public enum SequenceType {
        AUTHOR("authors_id_seq", "A"),
        BOOK("books_id_seq", "B"),
        GENRE("genres_id_seq", "G");

        private final String sequenceName;

        private final String prefix;

        SequenceType(String sequenceName, String prefix) {
            this.sequenceName = sequenceName;
            this.prefix = prefix;
        }
    }

    public static String getNextId(JdbcOperations operations, SequenceType type) {
        return operations.queryForObject("select '" + type.prefix + "' || nextval('" + type.sequenceName + "')", String.class);
    }
}
