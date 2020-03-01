package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.ExchangeRecordsTableItem;

import java.util.List;

public interface ExchangeRecordsService {
    List<ExchangeRecordsTableItem> getExchangeRecords(String username,int page,int pageSize) throws Exception;
}
