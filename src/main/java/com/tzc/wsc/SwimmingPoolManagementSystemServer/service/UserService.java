package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;

import java.util.List;

public interface UserService {

    public void addUser(User user) throws Exception;

    public User getUserByName(String username) throws Exception;

    public String getVerCode(String phone) throws Exception;

    public User getUserByPhone(String phone) throws Exception;

    public List<User> getUsers(String username,String phone,int page,int pageSize) throws Exception;

    public boolean changeUserInfo(int id,String phone,String username,int gender,int cardType) throws Exception;

    public boolean deleteUserById(int id) throws Exception;

    public void changeUserCardType(String phone,int cardType) throws Exception;

}
