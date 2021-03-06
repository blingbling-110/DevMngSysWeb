package com.qzj.devmngsys.repository;

import com.qzj.devmngsys.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Repository
public class CommonDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取所有借用单编号
     *
     * @return 借用单编号列表
     */
    public List<String> getAllBrwId() {
        return jdbcTemplate.queryForList("select id from tb_brw", String.class);
    }

    /**
     * 获取所有归还单编号
     *
     * @return 归还单编号列表
     */
    public List<String> getAllRtnId() {
        return jdbcTemplate.queryForList("select id from tb_rtn", String.class);
    }

    /**
     * 读取设备信息
     *
     * @param item 欲读取的设备
     * @return 该设备的设备信息公共类对象
     */
    public TbDevInfo getDevInfo(Item item) {
        String where = "name='" + item.getName() + "'";//	获取item对象的name属性
        if (item.getId() != null)
            where = "id='" + item.getId() + "'";//	获取item对象的id属性
        String sql = "select * from tb_devinfo where " + where;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TbDevInfo.class));
    }

    /**
     * 读取用户信息
     *
     * @param item 欲读取的用户
     * @return 该用户的人员信息公共类对象
     */
    public TbUserInfo getUserInfo(Item item) {
        String where = "name='" + item.getName() + "'";
        if (item.getJobNum() != null)
            where = "id=" + item.getJobNum();
        String sql = "select * from tb_userinfo where " + where;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TbUserInfo.class));
    }

    /**
     * 在事务中增加借用信息（保持数据的完整性）
     *
     * @param brw 欲插入数据库的借用公共类对象
     * @return 数据插入成功与否
     */
    @Transactional
    public boolean insertTbBrw(TbBrw brw) {
        //	增加借用表记录
        jdbcTemplate.execute("insert into tb_brw values('" + brw.getId()
                + "','" + brw.getDevId() + "'," + brw.getBrwerId()
                + ",'" + brw.getDate() + "','" + brw.getRemark() + "')");
        //	更改设备信息
        Item item = new Item();
        item.setId(brw.getDevId());//	获取借用设备编号
        TbDevInfo devInfo = getDevInfo(item);
        if (devInfo.getId() != null && !devInfo.getId().isEmpty())
            jdbcTemplate.execute("update tb_devinfo set status='"
                    + "工号：" + brw.getBrwerId()
                    + "',remark='" + brw.getRemark()
                    + "' where id='" + devInfo.getId() + "'");
        else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
            return false;
        }
        return true;
    }

    /**
     * 在事务中增加归还信息（保持数据的完整性）
     *
     * @param rtn 欲插入数据库的归还公共类对象
     * @return 数据插入成功与否
     */
    @Transactional
    public boolean insertTbRtn(TbRtn rtn) {
        //	增加借用表记录
        jdbcTemplate.execute("insert into tb_rtn values('" + rtn.getId()
                + "','" + rtn.getDevId() + "'," + rtn.getRtnerId()
                + ",'" + rtn.getDate() + "','" + rtn.getRemark() + "')");
        //	更改设备信息
        Item item = new Item();
        item.setId(rtn.getDevId());//	获取借用设备编号
        TbDevInfo devInfo = getDevInfo(item);
        if (devInfo.getId() != null && !devInfo.getId().isEmpty())
            jdbcTemplate.execute("update tb_devinfo set status='"
                    + "库存中" + "',remark='" + rtn.getRemark()
                    + "' where id='" + devInfo.getId() + "'");
        else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
            return false;
        }
        return true;
    }

    /**
     * 在事务中发送借用请求
     *
     * @param devId   欲借用的设备编号
     * @param reqerId 请求人的工号
     */
    @Transactional
    public void sendReq(String devId, String reqerId) {
        if (devId == null)
            devId = "";
        if (reqerId == null)
            reqerId = "";
        jdbcTemplate.execute("update tb_devinfo set req='"
                + reqerId + "' where id='" + devId + "'");
    }

    /**
     * 根据用户姓名搜索工号
     *
     * @param userName 用户姓名
     * @return 包含返回工号的List集合
     */
    public List<String> searchId(String userName) {
        if (userName == null)
            userName = "";
        String sql = "select id from tb_userinfo where name like '%" + userName + "%'";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    /**
     * 搜索设备信息
     *
     * @param id     欲搜索的设备编号
     * @param name   欲搜索的设备名称
     * @param status 欲搜索的设备状态
     * @param des    欲搜索的设备描述
     * @param remark 欲搜索的备注
     * @return 包含返回的设备信息的List集合
     */
    public List<TbDevInfo> searchDevInfo(String id, String name, String status, String des, String remark) {
        if (id == null)
            id = "";
        if (name == null)
            name = "";
        if (status == null)
            status = "";
        if (des == null)
            des = "";
        if (remark == null)
            remark = "";
        String sql = "select * from tb_devinfo where id like '%" + id + "%' and name like '%" + name + "%' and "
                + "status like '%" + status + "%' and des like '%" + des + "%' and remark like '%" + remark + "%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDevInfo.class));
    }

    /**
     * 搜索人员信息
     *
     * @param id        欲搜索的工号
     * @param name      欲搜索的姓名
     * @param username  欲搜索的用户名
     * @param pos       欲搜索的职位
     * @param dep       欲搜索的部门
     * @param email     欲搜索的邮箱
     * @param tel       欲搜索的电话
     * @param remark    欲搜索的备注
     * @param isAdmin   欲搜索的管理员权限值
     * @return  包含返回的人员信息的List集合
     */
    public List<TbUserInfo> searchUserInfo(String id, String name, String username, String pos, String dep,
                                          String email, String tel, String remark, boolean isAdmin) {
        if (id == null)
            id = "'%'";
        if (name == null)
            name = "";
        if (username == null)
            username = "";
        if (pos == null)
            pos = "";
        if (dep == null)
            dep = "";
        if (email == null)
            email = "";
        if (tel == null)
            tel = "";
        if (remark == null)
            remark = "";
        String sql = "select * from tb_userinfo where id like " + id + " and name like '%" + name + "%' and "
                + "userid like '%" + username + "%' and pos like '%" + pos + "%' and dep like '%" + dep
                + "%' and email like '%" + email + "%' and tel like '%" + tel + "%' and remark like '%"
                + remark + "%' and isadmin like " + isAdmin;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbUserInfo.class));
    }

    /**
     * 搜索借用单信息
     *
     * @param id        欲搜索的借用单编号
     * @param devId     欲搜索的借用设备编号
     * @param brwerId   欲搜索的借用人工号
     * @param date      欲搜索的借用日期
     * @param remark    欲搜索的备注
     * @return          包含返回的借用单信息的List集合
     */
    public List<TbBrw> searchBrwInfo(String id, String devId, String brwerId, String date, String remark) {
        if (id == null) {
            id = "";
        }
        if (devId == null) {
            devId = "";
        }
        if (brwerId == null)
            brwerId = "'%'";
        if (date == null)
            date = "";
        if (remark == null)
            remark = "";
        String sql = "select * from tb_brw where id like '%" + id + "%' and devid like '%" + devId + "%' and "
                + "brwerid like " + brwerId + " and date like '%" + date + "%' and  remark like '%" + remark
                + "%' order by id desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbBrw.class));
    }

    /**
     * 搜索归还单信息
     *
     * @param id        欲搜索的归还单编号
     * @param devId     欲搜索的归还设备编号
     * @param rtnerId   欲搜索的归还人工号
     * @param date      欲搜索的归还日期
     * @param remark    欲搜索的备注
     * @return          包含返回的归还单信息的List集合
     */
    public List<TbRtn> searchRtnInfo(String id, String devId, String rtnerId, String date, String remark) {
        if (id == null) {
            id = "";
        }
        if (devId == null) {
            devId = "";
        }
        if (rtnerId == null)
            rtnerId = "'%'";
        if (date == null)
            date = "";
        if (remark == null)
            remark = "";
        String sql = "select * from tb_rtn where id like '%" + id + "%' and devid like '%" + devId + "%' and "
                + "rtnerid like " + rtnerId + " and date like '%" + date + "%' and  remark like '%" + remark
                + "%' order by id desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbRtn.class));
    }
}
