package com.songshu.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songshu.usercenter.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Charmot
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2022-05-04 20:56:00
 */
public interface UserService extends IService<User> {

    /**
     *
     * @param account
     * @param password
     * @param checkPassword
     * @return
     */
    long userRegistry(String account, String password, String checkPassword);

    /**
     *
     * @param account
     * @param password
     * @return
     */
    User userLogin(String account, String password, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    User getSafeUser(User user);

}
