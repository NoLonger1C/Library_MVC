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
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person showPerson(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void create(Person person){
        jdbcTemplate.update("INSERT INTO person (fio,birthyear) VALUES (?,?)",
                person.getFio(),person.getBirthYear());
    }

    public void updatePerson(int id,Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET fio=?,birthyear=? WHERE id=?",
                updatePerson.getFio(),updatePerson.getBirthYear(),id);
    }

    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }

    public Optional<Person> findPerson(String fio, int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE fio=? AND NOT id=?",
                new Object[]{fio,id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksOfPerson(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_person=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

}
