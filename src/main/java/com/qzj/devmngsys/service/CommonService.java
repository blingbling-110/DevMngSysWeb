package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.Item;
import com.qzj.devmngsys.entities.TbBrw;
import com.qzj.devmngsys.entities.TbDevInfo;
import com.qzj.devmngsys.entities.TbUserInfo;
import com.qzj.devmngsys.repository.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
     * @return 借用成功与否的相关退出码
     */
    public int borrow(String devId, String brwerId, String remark) {
        devId = devId.trim();
        brwerId = brwerId.trim();
        remark = remark.trim();
        Item item = new Item();
        item.setId(devId);
        try {
            TbDevInfo tbDevInfo = commonDao.getDevInfo(item);
            if (!tbDevInfo.getStatus().equals("库存中"))
                return -2;//设备已借出，请在设备总览中进行借用请求操作
        }catch (EmptyResultDataAccessException e) {
            return -3;//设备编号不存在
        }
        Integer jobNum;
        try {
            jobNum = Integer.parseInt(brwerId);
        }catch (NumberFormatException e) {
            return -4;//工号只能为整数，且不能超过2147483647
        }
        item.setId(null);
        item.setJobNum(jobNum);
        try {
            TbUserInfo tbUserInfo = commonDao.getUserInfo(item);
        }catch (EmptyResultDataAccessException e) {
            return -5;//借用人工号不存在
        }
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
        brw.setBrwerId(jobNum);
        brw.setDate(dateStr);
        brw.setRemark(remark);
        boolean res = commonDao.insertTbBrw(brw);
        commonDao.sendReq(devId, "");
        return res? 0: -1;
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
