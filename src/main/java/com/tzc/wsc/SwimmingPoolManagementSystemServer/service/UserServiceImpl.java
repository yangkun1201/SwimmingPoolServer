package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.UserRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) throws Exception {
        userRepository.save(user);
    }

    @Override
    public User getUserByName(String username) throws Exception {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public String getVerCode(String phone) throws Exception {
        Long timestamp = System.currentTimeMillis();
        String verCode = timestamp.toString().substring(7,13);
        //SmsUtil.getInstances().sendSms(phone,verCode);
        return verCode;
    }

    @Override
    public User getUserByPhone(String phone) throws Exception {
        return userRepository.getUserByPhone(phone);
    }

    @Override
    public List<User> getUsers(String username,String phone,int page,int pageSize) throws Exception {
        List<User> users = null;
        page = page * pageSize;
        if(username != "" && phone != ""){
            users = userRepository.getUsersByUsernameAndPhoneAndType(username,phone,0, page, pageSize);
        }else if(username != "" && phone == ""){
            users = userRepository.getUsersByUsernameAndType(username,0, page, pageSize);
        }else if(username == "" && phone != ""){
            users = userRepository.getUsersByPhoneAndType(phone,0, page, pageSize);
        }else{
            users = userRepository.getUsersByType(0,page, pageSize);
        }
        return users;
    }
}
