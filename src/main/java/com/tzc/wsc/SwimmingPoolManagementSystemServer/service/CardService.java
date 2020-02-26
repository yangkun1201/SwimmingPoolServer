package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CardType;

import java.util.List;

public interface CardService {

    boolean addCard(int type,String desc,String picUrl);

    boolean deleteCard(int type);

    List<CardType> getAllCardTypes();

}
