package com.qzj.devmngsys.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void sendReq(String devId, String reqerId) {
        jdbcTemplate.update("update tb_devinfo set req=? where id=?", reqerId, devId);
    }

    public void delete(String devId) {
        jdbcTemplate.update("delete from tb_devinfo where id=?", devId);
    }
}
