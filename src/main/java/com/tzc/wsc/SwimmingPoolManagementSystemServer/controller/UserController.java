package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.HttpResponseCode;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.LoginStatus;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "addUser")
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

    @PostMapping(value = "login")
    public Map<String,Integer> login(@RequestBody Map requestBody){
        String username = ((String) requestBody.get("username"));
        String password = ((String) requestBody.get("password"));
        Map<String,Integer> loginResult = new HashMap<>();
        try {
            User user = userService.getUserByName(username);
            if(user == null){
                log.info("用户不存在");
                loginResult.put("login_status", LoginStatus.USER_NOT_EXIST);
            }else if(user.getPassword().equals(password)){
                log.info("登录成功");
                loginResult.put("login_status", LoginStatus.LOGIN_SUCCESS);
                loginResult.put("user_type",user.getType());
            }else{
                log.info("登录失败，密码错误");
                loginResult.put("login_status", LoginStatus.LOGIN_FAILURE);
            }
        } catch (Exception e) {
            log.error("登录异常",e);
        }
        return loginResult;
    }

    @GetMapping(value = "getVerCode")
    public Map<String,String> getVerCode(@RequestParam String phone){
        Map<String,String> result = new HashMap<>();
        try {
            String verCode = userService.getVerCode(phone);
            result.put("verCode",verCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "loginByVerCode")
    public Map<String,Integer> loginByVerCode(@RequestParam String phone){
        Map<String,Integer> result = new HashMap<>();
        try {
            User user = userService.getUserByPhone(phone);
            if(user == null){
                log.info("用户不存在");
                result.put("login_status", LoginStatus.USER_NOT_EXIST);
            }else{
                log.info("登录成功");
                result.put("login_status", LoginStatus.LOGIN_SUCCESS);
                result.put("user_type",user.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
