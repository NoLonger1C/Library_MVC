package ru.dfrolov.projects.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dfrolov.projects.models.Book;
import ru.dfrolov.projects.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void createBook(Book book){
        jdbcTemplate.update("INSERT INTO Book (name,author,createyear) VALUES (?,?,?)",
                book.getName(),book.getAuthor(),book.getCreateYear());
    }

    public Book showBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void updateBook(int id, Book updateBook){
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, createyear=? WHERE id=?",
                updateBook.getName(),updateBook.getAuthor(),updateBook.getCreateYear(),id);
    }

    public void deleteBook(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
    }

    public Optional<Person> getOwner(int id){
        return jdbcTemplate.query("SELECT person.id,person.fio,person.birthyear FROM person JOIN book ON person.id = book.id_person WHERE book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void addToPerson(int id,Person person){
        jdbcTemplate.update("UPDATE Book SET id_person=? WHERE id=?",
                person.getId(),id);
    }

    public void releaseOfPerson(int id){
        jdbcTemplate.update("UPDATE Book SET id_person=? WHERE id=?",
                null,id);
    }

}
