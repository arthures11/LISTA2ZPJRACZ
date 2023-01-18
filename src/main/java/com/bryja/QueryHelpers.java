package com.bryja;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class QueryHelpers{
    JdbcTemplate jdbcTemplate;
    public List<User> getUserList() {

        List<User> usrs = jdbcTemplate.query(
                String.format("SELECT * FROM users"),
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password")));

        return usrs;
    }

    public List<Post> getPostList() {

        List<Post> posty = jdbcTemplate.query(
                String.format("SELECT * FROM post"),
                (rs, rowNum) -> new Post(rs.getInt("postID"), rs.getString("pytanie"),
                        rs.getString("post_author"), rs.getString("post_data"), rs.getInt("liczba_odpowiedzi")));

        return posty;
    }

    public List<Odpowiedzi> getodpsList() {

        List<Odpowiedzi> odpsyt = jdbcTemplate.query(
                String.format("SELECT * FROM odpowiedzi"),
                (rs, rowNum) -> new Odpowiedzi(rs.getInt("id"), rs.getString("odp_author"),
                        rs.getString("tresc"), rs.getString("odp_data"),rs.getInt("ocena"),  rs.getInt("ilosc_ocen"), rs.getInt("suma_ocen"), rs.getInt("postID")));

        return odpsyt;
    }

    @Autowired
    public QueryHelpers() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:h2:~/test");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setPassword("");
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
    }
}
