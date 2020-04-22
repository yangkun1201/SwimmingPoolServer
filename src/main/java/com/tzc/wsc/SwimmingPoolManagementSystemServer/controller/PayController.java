package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.google.zxing.WriterException;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.util.AliPayUtil;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.util.QrCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
@CrossOrigin
@Slf4j
public class PayController {

    @RequestMapping("pay")
    public String pay(@RequestParam String commodityName,@RequestParam double amount,@RequestParam long tradeNo){
        String url = AliPayUtil.pay(commodityName,amount,tradeNo);
        return url;
    }

    @RequestMapping("getPayStatus")
    public boolean getPayStatus(@RequestParam long tradeNo){
        return AliPayUtil.checkPayStatus(tradeNo);
    }

}
