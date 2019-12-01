package com.qzj.devmngsys.repository;

import com.qzj.devmngsys.entities.TbDevInfo;
import com.qzj.devmngsys.entities.TbUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据工号查询其借用的设备信息
     * @param id 查询人工号
     * @return 该查询人所借用设备信息的列表
     */
    public List<TbDevInfo> getMyDevInfo(Integer id) {
        String sql = "select * from tb_devinfo where status=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDevInfo.class), id.toString());
    }

    /**
     * 根据工号查询人员信息
     * @param id 员工工号
     * @return 员工信息
     */
    public TbUserInfo getTbUserInfo(Integer id) {
        String sql = "select * from tb_userinfo where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TbUserInfo.class), id);
    }
}
