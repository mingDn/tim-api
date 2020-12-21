package com.example.service.Impl;

import com.example.dao.IFriendsDao;
import com.example.dao.IUserDao;
import com.example.domin.User;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IFriendsDao friendsDao;

    @Override
    public Boolean accountCheck(String account) {
        User user = userDao.userSelectByAccount(account);
        if (user == null || user.equals("")) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public Map doRegister(String account, String password, String name) {
        Map<String, Object> data = new HashMap<>();
        if (userDao.userSelectByAccount(account) == null) {
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setName(name);
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            user.setBirthday(df.format(new Date()));
            if (userDao.userAdd(user)) {
                if (friendsDao.groupAdd(account, "我的好友")) {
                    data.put("info", "1");
                    data.put("message", "注册成功");
                }
            } else {
                data.put("info", "0");
                data.put("message", "注册失败，请稍后再试");
            }
        } else {
            data.put("info", "0");
            data.put("message", "该账号已存在");
        }
        return data;
    }

    @Transactional
    @Override
    public Map load(String account, String password) {
        Map<String, Object> data = new HashMap<>();
        User user = userDao.userSelect(account, password);
        if (userDao.userSelectByAccount(account) == null) {
            data.put("info", "0");
            data.put("message", "该账号不存在");
        } else if (user == null) {
            data.put("info", "0");
            data.put("message", "密码错误");
        } else if (user.getState() == 1) {
            data.put("info", "0");
            data.put("message", "该账号已登录");
        } else {
            if (userDao.updateState(account, 1)) {
                user.setState(1);
                data.put("info", "1");
                data.put("user", userDao.userSelectByAccount(account));
            } else {
                data.put("info", "0");
                data.put("message", "登陆失败，请稍后再试");
            }
        }
        return data;
    }

    @Transactional
    @Override
    public Map logOut(String account) {
        Map<String, Object> data = new HashMap<>();
        if (userDao.updateState(account, 0)) {
            data.put("info", "1");
            data.put("user", new User());
        } else {
            data.put("info", "0");
            data.put("message", "退出失败，请稍后再试");
        }
        return data;
    }

    @Transactional
    @Override
    public Map updatePassword(String account, String password) {
        Map<String, Object> data = new HashMap<>();
        int state = userDao.userSelectByAccount(account).getState();
        if (userDao.updatePassword(account, password)) {
            data.put("info", 1);
            if (state == 0) {
                data.put("message", "修改成功");
            } else if (state == 1 && userDao.updateState(account, 0)) {
                data.put("message", "修改成功，请重新登录");
            }
        } else {
            data.put("info", "0");
            data.put("message", "修改失败，请稍后再试");
        }
        return data;
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String str = df.format(new Date());
        String[] dateS = str.split("/");
        String[] birthdayS = user.getBirthday().split("/");
        int[] date = new int[3];
        int[] birthday = new int[3];
        for (int i = 0; i < 3; i++) {
            date[i] = Integer.parseInt(dateS[i]);
            birthday[i] = Integer.parseInt(birthdayS[i]);
        }
        int age = date[0] - birthday[0];
        if (date[1] < date[1] && age > 0) {
            age++;
        } else if (date[1] == date[1] && age > 0) {
            if (date[2] <= date[2]) {
                age++;
            }
        }
        user.setAge(age);
        if (userDao.updateUser(user)) {
            User userNew = userDao.userSelectByAccount(user.getAccount());
            return userNew;
        }
        return null;
    }

    @Transactional
    @Override
    public User changePicture(String account, String path) {
        if (userDao.updatePath(account, path)) {
            User user = userDao.userSelectByAccount(account);
            return user;
        }
        return null;
    }
}
