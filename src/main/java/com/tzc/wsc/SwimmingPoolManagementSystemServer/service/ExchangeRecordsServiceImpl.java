package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.ExchangeRecords;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.ExchangeRecordsRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.UserRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.ExchangeRecordsTableItem;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExchangeRecordsServiceImpl implements ExchangeRecordsService{

    @Autowired
    private ExchangeRecordsRepository exchangeRecordsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PageVo getExchangeRecords(String username, int page, int pageSize) throws Exception {
        PageVo pageVo = null;
        Page<Map<String,Object>> pageResult = null;
        List<Map<String,Object>> tempList = null;
        List<ExchangeRecordsTableItem> resultData = new ArrayList<>();
        List<Integer> userIds = userRepository.getUserIdByUsername(username);
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        if(username.equals("")){
            pageResult = exchangeRecordsRepository.getAllExchangeRecords(pageRequest);
        }else if(userIds != null && userIds.size()>0){
            int userId = userIds.get(0);
            pageResult = exchangeRecordsRepository.getExchangeRecordsByUserId(userId,pageRequest);
        }
        if(pageResult.getContent().size()>0) {
            tempList = pageResult.getContent();
            for (Map<String,Object> item : tempList) {
                ExchangeRecordsTableItem exchangeRecordsTableItem = ExchangeRecordsTableItem.builder()
                        .id(((int) item.get("id")))
                        .username(((String)item.get("username")))
                        .commodityName(((String)item.get("name")))
                        .time(((Date)item.get("time")))
                        .address(((String) item.get("address")))
                        .build();
                resultData.add(exchangeRecordsTableItem);
            }
        }
        pageVo = PageVo.builder()
                .data(resultData)
                .totalPage(pageResult.getTotalPages())
                .build();
        return pageVo;
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
