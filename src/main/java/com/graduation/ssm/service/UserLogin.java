package com.graduation.ssm.service;

import javax.servlet.http.HttpServletRequest;

public interface UserLogin {
    public String login(String userId, String password, HttpServletRequest request);
}
