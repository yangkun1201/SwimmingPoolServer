package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.CheckFlag;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.Gender;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CheckItem;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.CheckRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.UserRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.CheckInCountNearWeekItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkIn(String phone,String verCode) throws Exception {
        CheckItem checkItem = CheckItem.builder()
                .phone(phone)
                .checkTime(new Date())
                .checkFlag(CheckFlag.CHECK_IN)
                .verCode(verCode)
                .build();
        List<CheckItem> items = checkRepository.getCheckItemsByPhoneOrderByCheckTimeDesc(phone);

        if(items.size()>0 && items.get(0).getCheckFlag() == CheckFlag.CHECK_IN){
            return false;
        }
        checkRepository.save(checkItem);

        User user = userRepository.getUserByPhone(phone);
        user.setIntegral(user.getIntegral() + 10);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean checkOut(String verCode) throws Exception {
        List<CheckItem>  checkInItems = checkRepository.getCheckItemsByVerCodeEqualsOrderByCheckTimeDesc(verCode);
        if(checkInItems.size() == 0){
            return false;
        }
        if(checkInItems.get(0).getCheckFlag() == CheckFlag.CHECK_OUT){
            return false;
        }
        CheckItem checkOutItem = CheckItem.builder()
                .phone(checkInItems.get(0).getPhone())
                .checkTime(new Date())
                .checkFlag(CheckFlag.CHECK_OUT)
                .verCode(verCode)
                .build();
        checkRepository.save(checkOutItem);
        return true;
    }

    @Override
    public boolean phoneHasRegistered(String phone) throws Exception {
        User user = userRepository.getUserByPhone(phone);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public List<CheckItem> getCheckInOutRecords(String phone, String vercode, int page, int pageSize) {
        page = page * pageSize;
        List<CheckItem> data = null;
        if(phone != "" && vercode != ""){
            data = checkRepository.getCheckInOutRecords(phone,vercode,page,pageSize);
        }else if(phone != "" && vercode == ""){
            data = checkRepository.getCheckInOutRecords(phone,page,pageSize);
        }else if(phone == "" && vercode != ""){
            data = checkRepository.getCheckInOutRecords(page,pageSize,vercode);
        }else{
            data = checkRepository.getCheckInOutRecords(page,pageSize);
        }
        return data;
    }

    @Override
    public int getPeopleCountToday(int gender) {
        if(gender == Gender.MALE){
            return checkRepository.getMaleCountToday();
        }else if(gender == Gender.FEMALE){
            return checkRepository.getFemaleCountToday();
        }
        return 0;
    }

    @Override
    public List<CheckInCountNearWeekItem> getCheckInCountNearWeek() {
        List<CheckInCountNearWeekItem> list = new ArrayList<>();
        List<Object[]> srcData = checkRepository.getCheckInCountNearWeek();
        if(srcData != null && srcData.size() > 0){
            for(Object[] obj:srcData){
                CheckInCountNearWeekItem cItem = CheckInCountNearWeekItem.builder()
                        .date(((String)obj[0]))
                        .count(((BigInteger) obj[1]).intValue())
                        .build();
                list.add(cItem);
            }
        }
        return list;
    }
}
