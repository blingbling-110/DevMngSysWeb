package com.qzj.devmngsys.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getPassword(String username) {
        String sql = "select pwd from tb_userinfo where userid=\"" + username + "\"";
        try {
            String pwd = jdbcTemplate.queryForObject(sql, String.class);
            return pwd;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
