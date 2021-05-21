package com.graduation.ssm.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface getUser {
    HashMap<String,Object> getUserList(HashMap<String,Object> param);
}


