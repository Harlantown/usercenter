package com.songshu.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.songshu.usercenter.model.User;
import com.songshu.usercenter.model.request.UserLoginRequest;
import com.songshu.usercenter.model.request.UserRegisterRequest;
import com.songshu.usercenter.service.UserService;
import com.songshu.usercenter.utils.constant.UserConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-05 下午 10:57
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest registerRequest){

        String userAccount = registerRequest.getUserAccount();
        String userPassword = registerRequest.getUserPassword();
        String userCheckPassword = registerRequest.getUserCheckPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword, userCheckPassword)){
            return null;
        }
        return userService.userRegistry(userAccount,
                userPassword,
                userCheckPassword);

    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest loginRequest,
                          HttpServletRequest request){
        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);

        User safeUser = userService.getSafeUser(user);
        return safeUser;

    }

    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User curUser = (User) userObj;

        if(curUser == null){
            return null;
        }

        Long id = curUser.getId();
        User user = userService.getById(id);
        User safeUser = userService.getSafeUser(user);
        return safeUser;
    }


    @GetMapping("/search")
    public List<User> serachUsersInfo(String userName, HttpServletRequest request){
        if(!isAdmin(request)){
            return null;
        }
        QueryWrapper queryWrapper = new QueryWrapper();

        if(StringUtils.isNotBlank(userName)){
            queryWrapper.like("userName",userName);
        }
        List<User> list = userService.list(queryWrapper);
//        return list;
        return list.stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList());

    }


    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request){
        if(!isAdmin(request)){
            return false;
        }

        if(id <= 0){
            return false;
        }

        return userService.removeById(id);
    }

    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;

        if(user == null){
            return false;
        }

        if(user.getUserPermission() != 0){
            return false;
        }

        return true;
    }

    @PostMapping("/logout")
    public Integer logoutUser(HttpServletRequest request){

        if(request == null){
            return 0;
        }
        userService.userLogout(request);
        return 1;

    }
}
