package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.HttpResponseCode;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.LoginStatus;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        }
        catch (DataIntegrityViolationException e){
            log.warn("用户已注册");
            return HttpResponseCode.USER_EXIST;
        }
        catch (Exception e){
            log.error("用户注册失败",e);
            return HttpResponseCode.FAILURE;
        }
        return HttpResponseCode.OK;
    }

    @PostMapping(value = "login")
    public Map<String,Object> login(@RequestBody Map requestBody){
        String username = ((String) requestBody.get("username"));
        String password = ((String) requestBody.get("password"));
        Map<String,Object> loginResult = new HashMap<>();
        try {
            User user = userService.getUserByName(username);
            if(user == null){
                log.info("用户不存在");
                loginResult.put("login_status", LoginStatus.USER_NOT_EXIST);
            }else if(user.getPassword().equals(password)){
                log.info("登录成功");
                loginResult.put("login_status", LoginStatus.LOGIN_SUCCESS);
                loginResult.put("user_type",user.getType());
                loginResult.put("user_info",user);
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
    public Map<String,Object> loginByVerCode(@RequestParam String phone){
        Map<String,Object> result = new HashMap<>();
        try {
            User user = userService.getUserByPhone(phone);
            if(user == null){
                log.info("用户不存在");
                result.put("login_status", LoginStatus.USER_NOT_EXIST);
            }else{
                log.info("登录成功");
                result.put("login_status", LoginStatus.LOGIN_SUCCESS);
                result.put("user_type",user.getType());
                result.put("user_info",user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "getUsers")
    public List<User> getUsers(@RequestParam String username,@RequestParam String phone,@RequestParam int page,@RequestParam int pageSize){
        List<User> users = null;
        try {
            users = userService.getUsers(username,phone, page, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @GetMapping(value = "changeUserInfo")
    public Map<String,String> changeUserInfo(@RequestParam int id,
                                             @RequestParam String phone,
                                             @RequestParam String username,
                                             @RequestParam int gender,
                                             @RequestParam int cardType){
        Map<String,String> result = new HashMap<>();
        try {
            if(userService.changeUserInfo(id,phone,username,gender,cardType)){
                result.put("status","ok");
                log.info("用户信息修改成功");
            }else{
                result.put("status","failure");
                result.put("desc","手机号码已注册");
                log.warn("手机号码已注册");
            }
        } catch (Exception e) {
            log.error("用户信息修改失败",e);
        }
        return result;
    }

    @GetMapping(value = "deleteUser")
    public Map<String,String> deleteUser(@RequestParam int id){
        Map<String,String> result = new HashMap<>();
        try {
            userService.deleteUserById(id);
            result.put("status","ok");
            log.info("用户删除成功");
        } catch (Exception e) {
            result.put("status","failure");
            log.error("用户删除失败",e);
        }
        return result;
    }

    @GetMapping("changeUserCardType")
    public Map<String,String> changeUserCardType(@RequestParam String phone,@RequestParam int cardType){
        Map<String,String> result = new HashMap<>();
        try {
            userService.changeUserCardType(phone, cardType);
            log.info("票卡类型更新成功,用户:{},票卡类型:{}",phone,cardType);
            result.put("status","ok");
        } catch (Exception e) {
            log.error("票卡类型更新失败",e);
            result.put("status","failure");
        }
        return result;
    }

    @GetMapping("getIntegralByUserId")
    public Map<String,Object> getIntegralByUserId(@RequestParam int userId){
        Map<String,Object> result = new HashMap<>();
        try {
            int integral = userService.getIntegralByUserId(userId);
            log.info("查询积分成功");
            result.put("status","ok");
            result.put("integral",integral);
        } catch (Exception e) {
            log.error("查询积分失败",e);
            result.put("status","failure");
        }
        return result;
    }

}
