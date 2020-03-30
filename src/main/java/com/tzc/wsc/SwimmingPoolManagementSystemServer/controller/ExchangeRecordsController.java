package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.ExchangeRecordsService;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.ExchangeRecordsTableItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("addExchangeRecord")
    public Map<String,Object> addExchangeRecords(@RequestParam int userId,
                                                 @RequestParam int commodityId,
                                                 @RequestParam int integral,
                                                 @RequestParam String address){
        Map<String, Object> result = new HashMap<>();
        try {
            exchangeRecordsService.addExchangeRecords(userId,commodityId,integral,address);
            log.info("新增商品兑换记录成功");
            result.put("status","ok");
        } catch (Exception e) {
            log.error("新增商品兑换记录失败",e);
            result.put("status","failure");
        }
        return result;
    }

}
