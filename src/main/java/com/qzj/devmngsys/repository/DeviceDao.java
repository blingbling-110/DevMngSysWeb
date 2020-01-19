package com.qzj.devmngsys.repository;

import com.qzj.devmngsys.entities.TbDevInfo;
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

    public void update(TbDevInfo devInfo) {
        jdbcTemplate.update("update tb_devinfo set name=?, des=?, remark=? where id=?",
                devInfo.getName(), devInfo.getDes(), devInfo.getRemark(), devInfo.getId());
    }

    public void add(TbDevInfo devInfo) {
        jdbcTemplate.update("insert into tb_devinfo values(?, ?, ?, ?, ?, '')",
                devInfo.getId(), devInfo.getName(), devInfo.getStatus(), devInfo.getDes(), devInfo.getRemark());
    }
}
