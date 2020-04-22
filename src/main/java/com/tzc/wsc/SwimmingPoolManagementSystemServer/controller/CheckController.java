package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.constant.Gender;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CheckItem;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.CheckService;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.CheckInCountNearWeekItem;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.CheckInOutTableItem;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
public class CheckController {

    @Autowired
    private CheckService checkService;

    @GetMapping("checkIn")
    public Map<String,String> checkIn(@RequestParam String phone, @RequestParam String verCode){
        Map<String,String> result = new HashMap<>();
        try {
            if(checkService.checkIn(phone,verCode)){
                result.put("check_status","ok");
                log.info("入馆成功");
            }else{
                result.put("check_status","failure");
                result.put("desc","重复入馆");
                log.info("重复入馆");
            }
        } catch (Exception e) {
            log.error("入馆失败",e);
        }
        return result;
    }

    @GetMapping("checkOut")
    public Map<String,String> checkOut(@RequestParam String verCode){
        Map<String,String> result = new HashMap<>();
        try {
            if(checkService.checkOut(verCode)){
                result.put("check_status","ok");
                log.info("出馆成功");
            }else{
                result.put("check_status","failure");
                result.put("desc","无入馆记录");
                log.info("无入馆记录");
            }
        } catch (Exception e) {
            log.error("出馆失败",e);
        }
        return result;
    }

    @GetMapping("phoneHasRegistered")
    public Map<String,String> phoneHasRegistered(@RequestParam String phone){
        Map<String,String> result = new HashMap<>();
        try {
            if(checkService.phoneHasRegistered(phone)){
                result.put("status","ok");
            }else{
                result.put("status","failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("getCheckInOutRecords")
    public PageVo getCheckInOutRecords(@RequestParam String phone, @RequestParam String vercode, @RequestParam int page, @RequestParam int pageSize){
        Page<List<CheckItem>> result = checkService.getCheckInOutRecords(phone,vercode,page,pageSize);
        PageVo pageVo = PageVo.builder()
                .data(result.getContent())
                .totalPage(result.getTotalPages())
                .build();
        return pageVo;
    }

    @GetMapping("getPeopleCountToday")
    public List<Map<String,Object>> getPeopleCountToday(){
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> item0 = new HashMap<>();
        Map<String,Object> item1 = new HashMap<>();
        item0.put("性别","男");
        item0.put("数量",checkService.getPeopleCountToday(Gender.MALE));
        item1.put("性别","女");
        item1.put("数量",checkService.getPeopleCountToday(Gender.FEMALE));
        result.add(item0);
        result.add(item1);
        return result;
    }

    @GetMapping("getCheckInCountNearWeek")
    public List<CheckInCountNearWeekItem> getCheckInCountNearWeek(){
        return checkService.getCheckInCountNearWeek();
    }

}
