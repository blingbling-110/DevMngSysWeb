package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.TbBrw;
import com.qzj.devmngsys.entities.TbUserInfo;
import com.qzj.devmngsys.repository.CommonDao;
import com.qzj.devmngsys.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private CommonDao commonDao;

    @Autowired
    private UserDao userDao;

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
        if (id != null && !id.isEmpty()) {
            id = id.trim();
        }
        if (devId != null && !devId.isEmpty()) {
            devId = devId.trim();
        }
        if (brwerId != null && !brwerId.isEmpty())
            brwerId = brwerId.trim();
        else
            brwerId = "'%'";
        if (date != null && !date.isEmpty())
            date = date.trim();
        if (remark != null && !remark.isEmpty())
            remark = remark.trim();
        //	搜索借用单信息
        return commonDao.searchBrwInfo(id, devId, brwerId, date, remark);
    }

    public void delete(String id) {
        userDao.delete(id);
    }

    public int update(String id, String name, String username, String pos, String dep, String email,
                      String tel, String remark, boolean isAdmin, String current_username) {
        List<TbUserInfo> userList = commonDao.searchUserInfo(null, null, null, null, null,
                null, null, null, false);
        userList.addAll(commonDao.searchUserInfo(null, null, null, null, null, null,
                null, null, true));
        for (TbUserInfo u: userList) {
            if (username.equals(u.getUserid()) && !username.equals(current_username))
                return -1;
        }
        TbUserInfo userInfo = new TbUserInfo();
        userInfo.setId(Integer.parseInt(id.trim()));
        userInfo.setName(name.trim());
        userInfo.setUserid(username.trim());
        userInfo.setPos(pos.trim());
        userInfo.setDep(dep.trim());
        userInfo.setEmail(email.trim());
        userInfo.setTel(tel.trim());
        userInfo.setRemark(remark.trim());
        userInfo.setIsadmin(isAdmin);
        userDao.update(userInfo);
        return 0;
    }

    public int add(String id, String name, String username, String  pwd, String pos, String dep, String email,
                   String tel, String remark, boolean isAdmin) {
        List<TbUserInfo> userList = commonDao.searchUserInfo(null, null, null, null, null,
                null, null, null, false);
        userList.addAll(commonDao.searchUserInfo(null, null, null, null, null, null,
                null, null, true));
        for (TbUserInfo u: userList) {
            if (id.equals(u.getId().toString()))
                return -1;
            if (username.equals(u.getUserid()))
                return -2;
        }
        TbUserInfo userInfo = new TbUserInfo();
        try {
            userInfo.setId(Integer.parseInt(id.trim()));
        }catch(NumberFormatException exc) {
            return -3;
        }
        userInfo.setName(name.trim());
        userInfo.setUserid(username.trim());
        userInfo.setPwd(pwd);
        userInfo.setPos(pos.trim());
        userInfo.setDep(dep.trim());
        userInfo.setEmail(email.trim());
        userInfo.setTel(tel.trim());
        userInfo.setRemark(remark.trim());
        userInfo.setIsadmin(isAdmin);
        userDao.add(userInfo);
        return 0;
    }
}
