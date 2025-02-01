package com.library.DAO;

import com.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM BOOKS", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM BOOKS WHERE ID = ?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class)).stream().findFirst().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO BOOKS (NAME, AUTHOR, YEAR) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(Book book, int id){
        jdbcTemplate.update("UPDATE PEOPLE SET NAME = ?, AUTHOR = ?, YEAR = ?, PERSONID = ? WHERE ID = ?",
                book.getName(), book.getAuthor(), book.getYear(), book.getPersonId(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM BOOKS WHERE ID = ?", id);
    }
}
