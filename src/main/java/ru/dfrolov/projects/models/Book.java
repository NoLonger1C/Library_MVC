package ru.dfrolov.projects.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Не должно быть пустым")
    @Size(min = 2,max = 100,message = "Должно быть от 2 до 100 символов")
    private String name;
    @NotEmpty(message = "Не должно быть пустым")
    @Size(min = 2,max = 100,message = "Должно быть от 2 до 100 символов")
    private String author;
    @Min(value = 0, message = "Должно быть больше 0")
    private int createYear;

    public Book() {
    }

    public Book(String name, String author, int createYear) {
        this.name = name;
        this.author = author;
        this.createYear = createYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCreateYear() {
        return createYear;
    }

    public void setCreateYear(int createYear) {
        this.createYear = createYear;
    }
}
