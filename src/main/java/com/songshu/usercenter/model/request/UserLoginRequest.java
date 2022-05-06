package com.songshu.usercenter.model.request;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-05 下午 11:39
 */
public class UserLoginRequest {

    private String userAccount;

    private String userPassword;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
