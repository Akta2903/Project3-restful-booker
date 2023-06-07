package com.restful.booker.model;

public class AuthPojo {
    private String userName ;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static AuthPojo getAuthPojo(String userName ,String password)
    {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUserName(userName);
        authPojo.setPassword(password);
        return authPojo;
    }
}
