package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.TbUserInfo;
import com.qzj.devmngsys.repository.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public TbUserInfo tbUserInfo;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 成功与否
     */
    public boolean login(String username, String password) {
        String pwd = loginDao.getPassword(username);
        if (password.equals(pwd)) {
            tbUserInfo = loginDao.searchFromUsername(username);
            return true;
        } else
            return false;
    }

    /**
     * 修改密码
     *
     * @param pwd_ori   原密码
     * @param pwd_new   新密码
     * @param pwd_cfm   确认密码
     * @param username  用户信息
     * @return          成功与否的错误码
     */
    public int changePwd(String pwd_ori, String pwd_new, String pwd_cfm, String username) {
        if (pwd_new.equals(pwd_cfm)) {
            if (pwd_ori.equals(loginDao.getPassword(username))) {
                loginDao.changePwd(pwd_ori, pwd_new, username);
                return 0;
            } else
                return -2;//原密码输入错误
        } else
            return -1;//确认密码与新密码不一致
    }
}
