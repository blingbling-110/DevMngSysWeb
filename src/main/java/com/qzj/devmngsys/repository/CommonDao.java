package com.qzj.devmngsys.repository;

import com.qzj.devmngsys.entities.Item;
import com.qzj.devmngsys.entities.TbBrw;
import com.qzj.devmngsys.entities.TbDevInfo;
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
     * 读取设备信息
     *
     * @param item 欲读取的设备
     * @return 该设备的设备信息公共类对象
     */
    private TbDevInfo getDevInfo(Item item) {
        String where = "name='" + item.getName() + "'";//	获取item对象的name属性
        if (item.getId() != null)
            where = "id='" + item.getId() + "'";//	获取item对象的id属性
        String sql = "select * from tb_devinfo where " + where;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TbDevInfo.class));
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
     * 在事务中发送借用请求
     *
     * @param devId   欲借用的设备编号
     * @param reqerId 请求人的工号
     */
    @Transactional
    public void sendReq(String devId, String reqerId) {
        jdbcTemplate.execute("update tb_devinfo set req='"
                + reqerId + "' where id='" + devId + "'");
    }
}
