package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.TbUserInfo;
import com.qzj.devmngsys.repository.CommonDao;
import com.qzj.devmngsys.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private CommonDao commonDao;

    @Autowired
    private UserDao userDao;

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
        if (id != null && !id.isEmpty())
            id = id.trim();
        else
            id = "'%'";
        if (name != null && !name.isEmpty())
            name = name.trim();
        if (username != null && !username.isEmpty())
            username = username.trim();
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
        //	搜索用户信息
        return commonDao.searchUserInfo(id, name, username, pos, dep, email, tel, remark, isAdmin);
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
