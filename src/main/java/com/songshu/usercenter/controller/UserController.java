package com.songshu.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.songshu.usercenter.common.constant.BaseResponse;
import com.songshu.usercenter.common.constant.ErrorCode;
import com.songshu.usercenter.common.exception.BusinessException;
import com.songshu.usercenter.common.utils.ResultUtils;
import com.songshu.usercenter.model.User;
import com.songshu.usercenter.model.request.UserLoginRequest;
import com.songshu.usercenter.model.request.UserRegisterRequest;
import com.songshu.usercenter.service.UserService;
import com.songshu.usercenter.common.constant.StringConstant;
import org.apache.commons.lang3.StringUtils;
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

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest loginRequest,
                                        HttpServletRequest request){
        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User user = userService.userLogin(userAccount, userPassword, request);

        if(user == null){
            throw new BusinessException(ErrorCode.NULL_ERROR, "用户名或密码错误！");
        }

        User safeUser = userService.getSafeUser(user);

        return ResultUtils.success(safeUser);

    }

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest registerRequest){

        String userAccount = registerRequest.getUserAccount();
        String userPassword = registerRequest.getUserPassword();
        String userCheckPassword = registerRequest.getUserCheckPassword();

        if(StringUtils.isAnyBlank(userAccount, userPassword, userCheckPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if(!userPassword.equals(userCheckPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入密码不一致！");
        }
        long id = userService.userRegistry(userAccount, userPassword, userCheckPassword);
        return ResultUtils.success(id);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> logoutUser(HttpServletRequest request){

        if(request == null){
            return null;
        }
        userService.userLogout(request);
        return ResultUtils.success(1);

    }

    @PostMapping("/delete")
    public Integer deleteUser(@RequestBody long id, HttpServletRequest request){
        if(!isAdmin(request)){
            return null;
        }

        if(id <= 0){
            return null;
        }

        boolean ok = userService.removeById(id);
        return 1;
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(StringConstant.USER_LOGIN_STATE);
        User curUser = (User) userObj;

        if(curUser == null){
            return null;
        }

        Long id = curUser.getId();
        User user = userService.getById(id);
        User safeUser = userService.getSafeUser(user);
        return ResultUtils.success(safeUser);
    }


    @GetMapping("/search")
    public BaseResponse<List<User>> serachUsersInfo(String userName, HttpServletRequest request){
        if(!isAdmin(request)){
            return null;
        }
        QueryWrapper queryWrapper = new QueryWrapper();

        if(StringUtils.isNotBlank(userName)){
            queryWrapper.like("userName",userName);
        }
        List<User> list = userService.list(queryWrapper);
        list.stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList());

        return ResultUtils.success(list);
    }

    @GetMapping("/testRole")
    public String testRole(){
        String win = "你赢了";

        return win;
    }

    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(StringConstant.USER_LOGIN_STATE);
        User user = (User) userObj;

        if(user == null){
            return false;
        }

        if(user.getUserPermission() != 0){
            return false;
        }

        return true;
    }


}
