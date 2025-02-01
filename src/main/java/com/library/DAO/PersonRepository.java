package com.library.DAO;

import com.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM PEOPLE;", new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setFullName(rs.getString("fullname"));
                person.setYearOfBirth(rs.getInt("yearofbirth"));
                return person;
            }
        });
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO PEOPLE (FULLNAME, YEAROFBIRTH) VALUES (?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE PEOPLE SET FULLNAME = ?, YEAROFBIRTH = ? WHERE ID = ?",
                person.getFullName(), person.getYearOfBirth(), id);
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM PEOPLE WHERE ID = ?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM PEOPLE WHERE ID = ?", id);
    }
}
