package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.CheckFlag;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.convertor.CheckInOutTableItemConvertor;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CheckItem;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.CheckRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.UserRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.CheckInOutTableItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckService {

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
    public List<CheckInOutTableItem> getCheckInOutRecords(int page, int pageSize) {
        List<CheckInOutTableItem> result = new ArrayList<>();
        List<Object[]> data = checkRepository.getCheckInOutRecords(page,pageSize);
        for(Object[] obj:data){
            CheckInOutTableItemConvertor convertor = new CheckInOutTableItemConvertor();
            CheckInOutTableItem item = convertor.convert(obj);
            result.add(item);
        }
        return result;
    }
}
