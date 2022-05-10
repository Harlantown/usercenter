package com.songshu.usercenter.model.request;

import java.io.Serializable;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-05 下午 11:39
 */
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -6142141051515962522L;

    private String userAccount;

    private String userPassword;

    public String getUserAccount() {
        return userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
