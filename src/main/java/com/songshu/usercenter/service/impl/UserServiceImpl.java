package com.songshu.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songshu.usercenter.model.User;
import com.songshu.usercenter.service.UserService;

import java.util.Objects;

import com.songshu.usercenter.mapper.UserMapper;
import com.songshu.usercenter.utils.constant.UserConstant;
import com.songshu.usercenter.utils.tools.StringUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Charmot
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2022-05-04 20:56:00
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    UserMapper userMapper;

    private String SALT = "4FdWUFAdg^TYPp6";


    @Override
    public long userRegistry(String account, String password, String checkPassword) {

        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            return -1;
        }

        if (account.length() < 4) {
            return -1;
        }

        if (password.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }

        if (!Objects.equals(password, checkPassword)) {
            return -1;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount", account);

        Long existsNum = userMapper.selectCount(queryWrapper);
        if (existsNum > 0) {
            return -1;
        }

        password = password + SALT;
        String finalPassword = StringUtil.md5(password);

        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(finalPassword);

        int insert = userMapper.insert(user);

        if (insert != 1) {
            return -1;
        }

        return user.getId();
    }

    @Override
    public User userLogin(String account, String password, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(account, password)) {
            return null;
        }
        if (account.length() < 4) {
            return null;
        }
        if (password.length() < 8) {
            return null;
        }

        password = password + SALT;
        String finalPassword = StringUtil.md5(password);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", account);
        queryWrapper.eq("userPassword", finalPassword);

        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("User login failed!!!");
            return null;
        }

        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUsername(user.getUsername());
        safeUser.setUserAccount(user.getUserAccount());
        safeUser.setAvatarUrl(user.getAvatarUrl());
        safeUser.setGender(user.getGender());
        safeUser.setPhone(user.getPhone());
        safeUser.setEmail(user.getEmail());
        safeUser.setUserStatus(user.getUserStatus());
        safeUser.setCreatTime(user.getCreatTime());
        safeUser.setUserPermission(user.getUserPermission());

        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, safeUser);

        return safeUser;


    }

    public User userLogin(String account, String password) {
        if (StringUtils.isAnyBlank(account, password)) {
            return null;
        }
        if (account.length() < 4) {
            return null;
        }
        if (password.length() < 8) {
            return null;
        }

        password = password + SALT;
        String finalPassword = StringUtil.md5(password);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", account);
        queryWrapper.eq("userPassword", finalPassword);

        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("User login failed!!!");
            return null;
        }

        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUsername(user.getUsername());
        safeUser.setUserAccount(user.getUserAccount());
        safeUser.setAvatarUrl(user.getAvatarUrl());
        safeUser.setGender(user.getGender());
        safeUser.setPhone(user.getPhone());
        safeUser.setEmail(user.getEmail());
        safeUser.setUserStatus(user.getUserStatus());
        safeUser.setCreatTime(user.getCreatTime());
        safeUser.setUserPermission(user.getUserPermission());

//        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, safeUser);

        return safeUser;


    }

    @Override
    public User getSafeUser(User user) {

        if(user == null){
            return null;
        }
        user.setUserPassword(null);
        return user;
    }

    @Override
    public Integer userLogout(HttpServletRequest request) {

        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return 1;
    }
}




