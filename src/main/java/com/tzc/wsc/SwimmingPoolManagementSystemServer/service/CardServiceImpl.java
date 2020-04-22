package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CardType;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public boolean addCard(int type,String name,String desc,int price,String picUrl) {
        CardType cardType = CardType.builder()
                .type(type)
                .name(name)
                .description(desc)
                .price(price)
                .picUrl(picUrl)
                .build();
        cardRepository.save(cardType);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteCard(int type) {
        cardRepository.deleteCardTypeByType(type);
        return true;
    }

    @Override
    public List<CardType> getAllCardTypes() {
        return cardRepository.getCardTypes();
    }
}
