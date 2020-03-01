package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.ExchangeRecordsService;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.ExchangeRecordsTableItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class ExchangeRecordsController {

    @Autowired
    private ExchangeRecordsService exchangeRecordsService;

    @GetMapping("getExchangeRecords")
    public List<ExchangeRecordsTableItem> getExchangeRecords(@RequestParam String username,
                                                             @RequestParam int page,
                                                             @RequestParam int pageSize){
        List<ExchangeRecordsTableItem> list = null;
        try {
            list = exchangeRecordsService.getExchangeRecords(username,page,pageSize);
            log.info("获取兑换记录成功");
        } catch (Exception e) {
            log.error("获取兑换记录失败",e);
        }
        return list;
    }

}
