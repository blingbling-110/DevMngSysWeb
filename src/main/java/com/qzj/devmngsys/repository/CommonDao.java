package com.qzj.devmngsys.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getAllBrwId(){
        return jdbcTemplate.queryForList("select id from tb_brw", String.class);
    }
}
