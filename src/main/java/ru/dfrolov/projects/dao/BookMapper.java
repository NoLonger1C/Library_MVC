package ru.dfrolov.projects.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.dfrolov.projects.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setCreateYear(resultSet.getInt("createyear"));
        return book;
    }
}
