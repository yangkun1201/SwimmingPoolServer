package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

public interface CheckService {

    public boolean checkIn(String phone,String verCode) throws Exception;

    public boolean checkOut(String verCode) throws Exception;

}
