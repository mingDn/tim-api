package com.example.service;

import com.example.domin.User;

import java.util.Map;

public interface IUserService {

    /**
     * 查询账号是否存在
     * @param account
     * @return
     */
    Boolean accountCheck(String account);

    /**
     * 注册账号
     * @param account
     * @param password
     * @param name
     * @return
     */
    Map doRegister(String account, String password, String name);

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    Map load(String account, String password);

    /**
     * 退出登录
     * @param account
     */
    Map logOut(String account);

    /**
     * 修改密码
     * @param account
     * @param password
     */
    Map updatePassword(String account, String password);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * 修改头像
     * @param account
     * @param path
     * @return
     */
    User changePicture(String account, String path);
}
