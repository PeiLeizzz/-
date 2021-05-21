package com.graduation.ssm.service;
import org.springframework.stereotype.Service;

@Service(value = "testImpl")
public class testImpl implements test {
    public String sayHi() {
        System.out.println("测试成功");
        return null;
    }
}


