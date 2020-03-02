package com.qzj.devmngsys.repository;

import com.qzj.devmngsys.entities.TbUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void delete(String id) {
        jdbcTemplate.update("delete from tb_userinfo where id=?", id);
    }

    public void update(TbUserInfo userInfo) {
        jdbcTemplate.update("update tb_userinfo set name=?, userid=?, pos=?, dep=?, email=?, tel=?, " +
                        "remark=?, isadmin=? where id=?", userInfo.getName(), userInfo.getUserid(),
                userInfo.getPos(), userInfo.getDep(), userInfo.getEmail(), userInfo.getTel(), userInfo.getRemark(),
                userInfo.isIsadmin(), userInfo.getId());
    }

    public void add(TbUserInfo userInfo) {
        jdbcTemplate.update("insert into tb_userinfo values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                userInfo.getId(), userInfo.getName(), userInfo.getUserid(), userInfo.getPwd(), userInfo.getPos(),
                userInfo.getDep(), userInfo.getEmail(), userInfo.getTel(), userInfo.getRemark(), userInfo.isIsadmin());
    }
}
