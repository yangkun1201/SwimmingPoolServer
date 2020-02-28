package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.Commodity;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @Value("${commodity.pic.path}")
    private String commodityPicPath;

    @Value("${commodity.pic.realative.path}")
    private String commodityPicRelativePath;

    @PostMapping("uploadCommodityPic")
    public Map<String,String> uploadCommodityPic(@RequestParam MultipartFile pic, @RequestParam String picName){
        Map<String,String> result = new HashMap<>();
        picName = String.format("%d_%s",System.currentTimeMillis(),picName);
        String picUrl = commodityPicRelativePath+picName;
        File saveFile = new File(commodityPicPath,picName);
        try {
            pic.transferTo(saveFile);
            log.info("商品图片上传成功,路径为：{}",picUrl);
            result.put("status","ok");
            result.put("picUrl",picUrl);
        } catch (IOException e) {
            result.put("status","failure");
            log.info("商品图图片上传失败",e);
        }
        return result;
    }

    @GetMapping("addCommodity")
    public Map<String,String> addCommodity(@RequestParam String name,
                                           @RequestParam int price,
                                           @RequestParam String picUrl){
        Map<String,String> result = new HashMap<>();
        Commodity commodity = Commodity.builder()
                .name(name)
                .price(price)
                .picUrl(picUrl)
                .build();
        try {
            commodityService.addCommodity(commodity);
            log.info("商品{}保存成功",name);
            result.put("status","ok");
        } catch (Exception e) {
            log.error("商品{}保存失败",e);
            result.put("status","failure");
        }
        return result;
    }

    @GetMapping("getAllCommodities")
    public List<Commodity> getAllCommodities(int page, int pageSize){
        List<Commodity> list = null;
        try {
            list = commodityService.getAllCommodities(page,pageSize);
            log.info("商品信息查询成功");
        } catch (Exception e) {
            log.error("商品信息查询失败",e);
        }
        return list;
    }

    @GetMapping("deleteCommodityById")
    public Map<String,String> deleteCommodityById(@RequestParam int id){
        Map<String,String> result = new HashMap<>();
        try {
            commodityService.deleteCommodityById(id);
            result.put("status","ok");
            log.info("商品{}删除成功",id);
        } catch (Exception e) {
            result.put("status","failure");
            log.error("商品{}删除失败",id,e);
        }
        return result;
    }

}
