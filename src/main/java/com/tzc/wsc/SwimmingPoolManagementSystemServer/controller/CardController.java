package com.tzc.wsc.SwimmingPoolManagementSystemServer.controller;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CardType;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("addCard")
    public Map<String,String> addCard(@RequestParam int type,@RequestParam String desc){
        Map<String,String> result = new HashMap<>();
        try{
            cardService.addCard(type,desc);
            result.put("status","ok");
        }catch (Exception e){
            e.printStackTrace();
            result.put("status","failure");
        }
        return result;
    }

    @GetMapping("deleteCard")
    public Map<String,String> deleteCard(@RequestParam int id){
        Map<String,String> result = new HashMap<>();
        try{
            cardService.deleteCard(id);
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

}
