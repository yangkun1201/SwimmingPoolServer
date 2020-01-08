package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;

public interface UserService {

    public void addUser(User user) throws Exception;

    public User getUserByName(String username) throws Exception;

    public String getVerCode(String phone) throws Exception;

    public User getUserByPhone(String phone) throws Exception;

}
