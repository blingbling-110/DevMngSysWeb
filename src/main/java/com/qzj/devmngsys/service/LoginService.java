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
            tbUserInfo = loginDao.getUserInfo(username);
            return true;
        } else
            return false;
    }
}
