package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CardType;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
public class CardController {

    @Autowired
    private CardService cardService;

    @Value("${card.pic.path}")
    private String cardPicPath;

    @Value("${card.pic.realative.path}")
    private String cardPicRelativePath;

    @GetMapping("addCard")
    public Map<String,String> addCard(@RequestParam int type,
                                      @RequestParam String name,
                                      @RequestParam String desc,
                                      @RequestParam int price,
                                      @RequestParam String picUrl){
        Map<String,String> result = new HashMap<>();
        try{
            cardService.addCard(type,name,desc,price,picUrl);
            result.put("status","ok");
        }catch (DataIntegrityViolationException e){
            result.put("status","failure");
            result.put("desc","票卡类型已存在");
            log.warn("票卡类型已存在");
        } catch (Exception e){
            e.printStackTrace();
            result.put("status","failure");
            result.put("desc","新增票卡失败");
            log.warn("新增票卡失败");
        }
        return result;
    }

    @GetMapping("deleteCard")
    public Map<String,String> deleteCard(@RequestParam int type){
        Map<String,String> result = new HashMap<>();
        try{
            cardService.deleteCard(type);
            result.put("status","ok");
        }catch (Exception e){
            e.printStackTrace();
            result.put("status","failure");
        }
        return result;
    }

    @GetMapping("getAllCardTypes")
    public List<CardType> GetAllCardTypes(){
        return cardService.getAllCardTypes();
    }

    @PostMapping("uploadCardPic")
    public Map<String,String> uploadCardPic(@RequestParam MultipartFile pic,@RequestParam String picName){
        Map<String,String> result = new HashMap<>();
        picName = String.format("%d_%s",System.currentTimeMillis(),picName);
        String picUrl = cardPicRelativePath+picName;
        File saveFile = new File(cardPicPath,picName);
        try {
            pic.transferTo(saveFile);
            log.info("票卡图片上传成功,路径为：{}",picUrl);
            result.put("status","ok");
            result.put("picUrl",picUrl);
        } catch (IOException e) {
            result.put("status","failure");
            log.info("票卡图片上传失败",e);
        }
        return result;
    }

    @GetMapping("getTypes")
    public List<Map<String,Object>> getAllCardType(){
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> m0 = new HashMap<>();
        m0.put("id","0");
        m0.put("name","单次票");
        data.add(m0);
        Map<String,Object> m1 = new HashMap<>();
        m1.put("id","1");
        m1.put("name","周卡");
        data.add(m1);
        Map<String,Object> m2 = new HashMap<>();
        m2.put("id","2");
        m2.put("name","月卡");
        data.add(m2);
        Map<String,Object> m3 = new HashMap<>();
        m3.put("id","3");
        m3.put("name","年卡");
        data.add(m3);
        return data;
    }

}
