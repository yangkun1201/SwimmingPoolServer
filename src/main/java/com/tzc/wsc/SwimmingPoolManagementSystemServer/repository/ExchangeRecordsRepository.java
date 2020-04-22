package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.ExchangeRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExchangeRecordsRepository extends JpaRepository<ExchangeRecords,Integer> {

    @Query(value = "select e.id,u.username,c.name,e.time,e.address from exchange_records e,user u,commodity c where e.user_id = u.id and e.commodity_id = c.id and e.user_id = ?1 order by e.time desc",
            countQuery = "select count(*) from exchange_records e,user u,commodity c where e.user_id = u.id and e.commodity_id = c.id and e.user_id = ?1 order by e.time desc",
            nativeQuery = true)
    public Page<Map<String,Object>> getExchangeRecordsByUserId(int userId, Pageable pageable);

    @Query(value = "select e.id,u.username,c.name,e.time,e.address from exchange_records e,user u,commodity c where e.user_id = u.id and e.commodity_id = c.id order by e.time desc",
            countQuery = "select count(*) from exchange_records e,user u,commodity c where e.user_id = u.id and e.commodity_id = c.id order by e.time desc",
            nativeQuery = true)
    public Page<Map<String,Object>> getAllExchangeRecords(Pageable pageable);

}
