package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CardType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<CardType,Integer> {

    @Query(value = "from CardType t order by t.id")
    List<CardType> getCardTypes();

    @Query(value = "delete from card_type t where t.type=:type",nativeQuery = true)
    @Modifying
    void deleteCardTypeByType(@Param("type") int type);


}
