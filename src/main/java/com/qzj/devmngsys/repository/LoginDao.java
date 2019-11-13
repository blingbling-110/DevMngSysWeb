package com.qzj.devmngsys.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean login(String username, String password) {
        String sql = "select pwd from tb_userinfo where userid=\"" + username + "\"";
        try {
            String pwd = jdbcTemplate.queryForObject(sql, String.class);
            return password.equals(pwd);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
