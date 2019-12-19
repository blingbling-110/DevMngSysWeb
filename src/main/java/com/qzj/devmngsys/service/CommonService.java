package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.TbBrw;
import com.qzj.devmngsys.repository.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommonService {
    @Autowired
    private CommonDao commonDao;

    /**
     * 借用设备处理函数，包括增加借用信息和清空借用请求
     *
     * @param devId   设备编号
     * @param brwerId 借用人工号
     * @param remark  备注
     * @return 借用成功与否
     */
    public boolean borrow(String devId, String brwerId, String remark) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMdd");
        String brwId = "brwID_" + sdf.format(date);
        String dateStr = String.format("%tc", date);
        //	获取所有借用单
        List<String> allBrw = commonDao.getAllBrwId();
        int idCount = 0;//	统计当日已有借用单数量
        //	获取现有借用单编号
        for (String id : allBrw) {
            if (id.indexOf(brwId) == 0)
                idCount++;
        }
        //	确定借用单编号
        idCount++;
        brwId = brwId + String.format("%03d", idCount);
        TbBrw brw = new TbBrw();//	封装待增加借用单的对象
        brw.setId(brwId.trim());
        brw.setDevId(devId);
        brw.setBrwerId(Integer.parseInt(brwerId));
        brw.setDate(dateStr);
        brw.setRemark(remark);
        boolean res = commonDao.insertTbBrw(brw);
        commonDao.sendReq(devId, "");
        return res;
    }

    /**
     * 拒绝设备请求
     *
     * @param devId 设备编号
     */
    public void reject(String devId) {
        commonDao.sendReq(devId, "");
    }
}
