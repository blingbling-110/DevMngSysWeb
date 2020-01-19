package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.Item;
import com.qzj.devmngsys.entities.TbDevInfo;
import com.qzj.devmngsys.entities.TbUserInfo;
import com.qzj.devmngsys.repository.CommonDao;
import com.qzj.devmngsys.repository.DeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private CommonDao commonDao;

    @Autowired
    private DeviceDao deviceDao;

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
    public List<List<Object>> searchDevInfo(String id, String name, String status, String des, String remark) {
        List<List<Object>> devInfo = new ArrayList<>();
        if (id != null && !id.isEmpty())
            id = id.trim();
        if (name != null && !name.isEmpty())
            name = name.trim();
        if (status != null && !status.isEmpty())
            status = status.trim();
        if (des != null && !des.isEmpty())
            des = des.trim();
        if (remark != null && !remark.isEmpty())
            remark = remark.trim();
        List<String> selBrwerId = new ArrayList<>();
        //	若输入为空，则显示所有设备
        if (status == null || status.isEmpty()) {
            selBrwerId.add(status);
        } else {
            //	若输入借用人姓名则先搜索借用人工号
            selBrwerId = commonDao.searchId(status);
            //	若未搜索到借用人工号，则说明输入的是设备状态或者借用人工号
            if (selBrwerId.size() == 0) {
                selBrwerId.add(status);
            }
        }
        for(String eachStatus: selBrwerId) {
            //	搜索设备信息
            List<TbDevInfo> selDevInfo = commonDao.searchDevInfo(id, name, eachStatus, des, remark);
            //	处理每个设备信息对应的用户信息
            for (TbDevInfo tbDevInfo : selDevInfo) {
                List<Object> devAndUser = new ArrayList<>();
                devAndUser.add(tbDevInfo);
                TbUserInfo userInfo = new TbUserInfo();
                //	判断是否借出
                if (!tbDevInfo.getStatus().equals("库存中")) {
                    Item userItem = new Item(null, null,
                            Integer.parseInt(tbDevInfo.getStatus().substring(3)));
                    userInfo = commonDao.getUserInfo(userItem);//	获取指定人员信息
                }
                devAndUser.add(userInfo);
                devInfo.add(devAndUser);
            }
        }
        return devInfo;
    }

    public void sendReq(String devId, String reqerId) {
        deviceDao.sendReq(devId, reqerId);
    }

    public void delete(String devId) {
        deviceDao.delete(devId);
    }

    public void update(String devId, String devName, String devDes, String devRem) {
        TbDevInfo devInfo = new TbDevInfo();
        devInfo.setId(devId);
        devInfo.setName(devName.trim());
        devInfo.setDes(devDes.trim());
        devInfo.setRemark(devRem.trim());
        deviceDao.update(devInfo);
    }

    public int add(String devId, String devName, String devSta, String devDes, String devRem) {
        List<TbDevInfo> allDevInfo = commonDao.searchDevInfo("", "", "", "", "");
        for(int i = 0; i < allDevInfo.size(); i++) {
            String eachId = allDevInfo.get(i).getId();
            if(devId.equals(eachId)) {
                //设备编号已存在
                return -1;
            }
        }
        TbDevInfo devInfo = new TbDevInfo();
        devInfo.setId(devId);
        devInfo.setName(devName.trim());
        devInfo.setStatus(devSta.trim());
        devInfo.setDes(devDes.trim());
        devInfo.setRemark(devRem.trim());
        deviceDao.add(devInfo);
        return 0;
    }
}
