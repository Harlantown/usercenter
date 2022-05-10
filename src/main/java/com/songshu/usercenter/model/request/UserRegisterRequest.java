package com.songshu.usercenter.model.request;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-05 下午 11:35
 */
public class UserRegisterRequest {

    private String userAccount;

    private String userPassword;

    private String userCheckPassword;

    public String getUserCheckPassword() {
        return userCheckPassword;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
