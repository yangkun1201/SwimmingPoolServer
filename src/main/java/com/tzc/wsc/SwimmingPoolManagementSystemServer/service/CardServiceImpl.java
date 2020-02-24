package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CardType;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public boolean addCard(int type, String desc) {
        CardType cardType = CardType.builder()
                .type(type)
                .description(desc)
                .build();
        cardRepository.save(cardType);
        return true;
    }

    @Override
    public boolean deleteCard(int id) {
        cardRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CardType> getAllCardTypes() {
        return cardRepository.getCardTypes();
    }
}
