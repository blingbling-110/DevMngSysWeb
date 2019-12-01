package com.qzj.devmngsys.repository;

import com.qzj.devmngsys.entities.TbUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名查询密码
     * @param username 用户名
     * @return 密码
     */
    public String getPassword(String username) {
        String sql = "select pwd from tb_userinfo where userid=?";
        try {
            String pwd = jdbcTemplate.queryForObject(sql, String.class, username);
            return pwd;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    public TbUserInfo getUserInfo(String username) {
        String sql = "select * from tb_userinfo where userid=?";
        try {
            TbUserInfo tbUserInfo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TbUserInfo.class), username);
            return tbUserInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
