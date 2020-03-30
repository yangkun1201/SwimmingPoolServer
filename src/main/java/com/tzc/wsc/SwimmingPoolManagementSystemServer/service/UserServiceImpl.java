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

    @Autowired
    private SmsUtil smsUtil;

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
        //smsUtil.sendSms(phone,verCode);
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

    @Override
    public boolean changeUserInfo(int id, String phone, String username, int gender,int cardType) throws Exception {
        User user0 = userRepository.getUserByPhone(phone);
        if(user0 != null && user0.getId() != id){
            return false;
        }
        User user = userRepository.findById(id).get();
        user.setPhone(phone);
        user.setUsername(username);
        user.setGender(gender);
        user.setCardType(cardType);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUserById(int id) throws Exception {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public void changeUserCardType(String phone, int cardType) throws Exception {
        User user = userRepository.getUserByPhone(phone);
        if(user != null){
            user.setCardType(cardType);
            userRepository.save(user);
        }
    }

    @Override
    public int getIntegralByUserId(int userId) throws Exception {
        User user = userRepository.findById(userId).get();
        return user.getIntegral();
    }
}
