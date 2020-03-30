package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.ExchangeRecordsTableItem;

import javax.xml.crypto.Data;
import java.util.List;

public interface ExchangeRecordsService {
    List<ExchangeRecordsTableItem> getExchangeRecords(String username,int page,int pageSize) throws Exception;

    void addExchangeRecords(int userId, int commodityId,int integral,String address) throws Exception;

}
