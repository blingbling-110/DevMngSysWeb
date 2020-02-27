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
        List<TbUserInfo> UserInfo = commonDao.searchUserInfo(id, name, username, pos, dep,
                email, tel, remark, isAdmin);
        return UserInfo;
    }

    public void delete(String id) {
        userDao.delete(id);
    }
}
