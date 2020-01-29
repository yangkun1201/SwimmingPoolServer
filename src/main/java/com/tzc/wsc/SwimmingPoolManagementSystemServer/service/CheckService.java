package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.CheckInOutTableItem;

import java.util.List;

public interface CheckService {

    public boolean checkIn(String phone,String verCode) throws Exception;

    public boolean checkOut(String verCode) throws Exception;

    public boolean phoneHasRegistered(String phone) throws Exception;

    public List<CheckInOutTableItem> getCheckInOutRecords(int page, int pageSize);

}
