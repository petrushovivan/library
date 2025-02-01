package com.library.model;

public class Book {
    private String name;
    private String author;
    private int year;
    private int id;
    private Integer personId;
    private String UserName;

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }
}
