package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.PageVo;

public interface ExchangeRecordsService {
    PageVo getExchangeRecords(String username, int page, int pageSize) throws Exception;

    void addExchangeRecords(int userId, int commodityId,int integral,String address) throws Exception;

}
