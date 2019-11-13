package com.qzj.devmngsys.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql = "select pwd from tb_userinfo where userid=\"username\"";

    public boolean login(String username, String password) {
        sql = sql.replace("username", username);
        String pwd = jdbcTemplate.queryForObject(sql, String.class);
        if (pwd != null && !pwd.isEmpty())
            return password.equals(pwd);
        else
            return false;
    }
}
