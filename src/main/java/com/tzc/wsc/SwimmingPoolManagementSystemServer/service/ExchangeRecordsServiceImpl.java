package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.ExchangeRecords;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.ExchangeRecordsRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.UserRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.ExchangeRecordsTableItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ExchangeRecordsServiceImpl implements ExchangeRecordsService{

    @Autowired
    private ExchangeRecordsRepository exchangeRecordsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ExchangeRecordsTableItem> getExchangeRecords(String username, int page, int pageSize) throws Exception {
        List<Object[]> tempList = new ArrayList<>();
        List<ExchangeRecordsTableItem> resultData = new ArrayList<>();
        List<Integer> userIds = userRepository.getUserIdByUsername(username);
        page = page*pageSize;
        if(username.equals("")){
            tempList = exchangeRecordsRepository.getAllExchangeRecords(page,pageSize);
        }else if(userIds != null && userIds.size()>0){
            int userId = userIds.get(0);
            tempList = exchangeRecordsRepository.getExchangeRecordsByUserId(userId,page,pageSize);
        }
        for(Object[] item:tempList){
            ExchangeRecordsTableItem exchangeRecordsTableItem = ExchangeRecordsTableItem.builder()
                    .id(((int) item[0]))
                    .username(((String) item[1]))
                    .commodityName(((String) item[2]))
                    .time(((Date) item[3]))
                    .address(((String) item[4]))
                    .build();
            resultData.add(exchangeRecordsTableItem);
        }
        return resultData;
    }

    @Override
    public void addExchangeRecords(int userId, int commodityId, int integral, String address) throws Exception {
        User user = userRepository.findById(userId).get();
        user.setIntegral(user.getIntegral() - integral);
        userRepository.save(user);

        ExchangeRecords exchangeRecords = ExchangeRecords.builder()
                .userId(userId)
                .commodityId(commodityId)
                .time(new Date())
                .address(address)
                .build();
        exchangeRecordsRepository.save(exchangeRecords);

    }
}
