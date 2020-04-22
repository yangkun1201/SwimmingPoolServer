package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CheckItem;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.CheckInCountNearWeekItem;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.CheckInOutTableItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CheckService {

    public boolean checkIn(String phone,String verCode) throws Exception;

    public boolean checkOut(String verCode) throws Exception;

    public boolean phoneHasRegistered(String phone) throws Exception;

    public Page<List<CheckItem>> getCheckInOutRecords(String phone, String vercode, int page, int pageSize);

    public int getPeopleCountToday(int gender);

    public List<CheckInCountNearWeekItem> getCheckInCountNearWeek();

}
