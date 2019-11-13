package com.qzj.devmngsys.service;

import com.qzj.devmngsys.repository.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public boolean login(String username, String password) {
        if (loginDao.login(username, password))
            return true;
        else
            return false;
    }
}
