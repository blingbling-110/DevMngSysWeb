package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.Item;
import com.qzj.devmngsys.entities.TbDevInfo;
import com.qzj.devmngsys.entities.TbUserInfo;
import com.qzj.devmngsys.repository.CommonDao;
import com.qzj.devmngsys.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private CommonDao commonDao;

    @Autowired
    private UserDao userDao;

    /**
     * 搜索用户信息
     *
     * @param id        欲搜索的工号
     * @param name      欲搜索的姓名
     * @param userName  欲搜索的用户名
     * @param pos       欲搜索的职位
     * @param dep       欲搜索的部门
     * @param email     欲搜索的邮箱
     * @param tel       欲搜索的电话
     * @param remark    欲搜索的备注
     * @return  包含返回的设备信息的List集合
     */
    public List<Object> searchUserInfo(String id, String name, String userName, String pos, String dep,
                                             String email, String tel, String remark) {
        if (id != null && !id.isEmpty())
            id = id.trim();
        if (name != null && !name.isEmpty())
            name = name.trim();
        if (userName != null && !userName.isEmpty())
            userName = userName.trim();
        if (pos != null && !pos.isEmpty())
            pos = pos.trim();
        if (dep != null && !dep.isEmpty())
            dep = dep.trim();
        if (email != null && !email.isEmpty())
            email = email.trim();
        if (tel != null && !tel.isEmpty())
            tel = tel.trim();
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
}
