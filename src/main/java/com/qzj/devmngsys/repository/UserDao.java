package com.qzj.devmngsys.repository;

import com.qzj.devmngsys.entities.TbDevInfo;
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

    public void update(TbDevInfo devInfo) {
        jdbcTemplate.update("update tb_devinfo set name=?, des=?, remark=? where id=?",
                devInfo.getName(), devInfo.getDes(), devInfo.getRemark(), devInfo.getId());
    }

    public void add(TbDevInfo devInfo) {
        jdbcTemplate.update("insert into tb_devinfo values(?, ?, ?, ?, ?, '')",
                devInfo.getId(), devInfo.getName(), devInfo.getStatus(), devInfo.getDes(), devInfo.getRemark());
    }
}
