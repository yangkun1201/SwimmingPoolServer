package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.HttpResponseCode;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/addUser")
    public String addUser(@RequestBody User user){
        try {
            userService.addUser(user);
            log.info("用户注册成功:{}",user);
        }catch (Exception e){
            log.error("用户注册失败",e);
            return HttpResponseCode.FAILURE;
        }
        return HttpResponseCode.OK;
    }

}
