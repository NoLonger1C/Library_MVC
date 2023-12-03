package ru.dfrolov.projects.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class Person {
    private int id;
    @NotEmpty(message = "Не должно быть пустым")
    @Size(min = 2,max = 100,message = "Должно быть от 2 до 100 символов")
    private String fio;
    @Min(value = 1, message = "Должно быть больше 0")
    private int birthYear;

    public Person() {
    }

    public Person(String fio, int birthYear) {
        this.fio = fio;
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
