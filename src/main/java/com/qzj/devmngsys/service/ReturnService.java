package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.*;
import com.qzj.devmngsys.repository.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReturnService {
    @Autowired
    private CommonDao commonDao;

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
        if (id != null && !id.isEmpty()) {
            id = id.trim();
        }
        if (devId != null && !devId.isEmpty()) {
            devId = devId.trim();
        }
        if (rtnerId != null && !rtnerId.isEmpty())
            rtnerId = rtnerId.trim();
        else
            rtnerId = "'%'";
        if (date != null && !date.isEmpty())
            date = date.trim();
        if (remark != null && !remark.isEmpty())
            remark = remark.trim();
        //	搜索借用单信息
        return commonDao.searchRtnInfo(id, devId, rtnerId, date, remark);
    }

    /**
     * 归还设备处理函数
     *
     * @param devId   设备编号
     * @param rtnerId 归还人工号
     * @param remark  备注
     * @return 归还成功与否的相关退出码
     */
    public int returnDev(String devId, String rtnerId, String remark) {
        devId = devId.trim();
        rtnerId = rtnerId.trim();
        remark = remark.trim();
        Item item = new Item();
        item.setId(devId);
        try {
            TbDevInfo tbDevInfo = commonDao.getDevInfo(item);
            if (tbDevInfo.getStatus().equals("库存中"))
                return -2;//设备已归还
        }catch (EmptyResultDataAccessException e) {
            return -3;//设备编号不存在
        }
        Integer jobNum;
        try {
            jobNum = Integer.parseInt(rtnerId);
        }catch (NumberFormatException e) {
            return -4;//工号只能为整数，且不能超过2147483647
        }
        item.setId(null);
        item.setJobNum(jobNum);
        try {
            TbUserInfo tbUserInfo = commonDao.getUserInfo(item);
        }catch (EmptyResultDataAccessException e) {
            return -5;//归还人工号不存在
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMdd");
        String rtnId = "rtnID_" + sdf.format(date);
        String dateStr = String.format("%tc", date);
        //	获取所有归还单
        List<String> allRtn = commonDao.getAllRtnId();
        int idCount = 0;//	统计当日已有归还单数量
        //	获取现有归还单编号
        for (String id : allRtn) {
            if (id.indexOf(rtnId) == 0)
                idCount++;
        }
        //	确定归还单编号
        idCount++;
        rtnId = rtnId + String.format("%03d", idCount);
        TbRtn rtn = new TbRtn();//	封装待增加归还单的对象
        rtn.setId(rtnId.trim());
        rtn.setDevId(devId);
        rtn.setRtnerId(jobNum);
        rtn.setDate(dateStr);
        rtn.setRemark(remark);
        boolean res = commonDao.insertTbRtn(rtn);
        commonDao.sendReq(devId, "");
        return res? 0: -1;
    }
}
