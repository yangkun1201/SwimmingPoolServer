package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.ExchangeRecords;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRecordsRepository extends CrudRepository<ExchangeRecords,Integer> {

    @Query(value = "select e.id,u.username,c.name,e.time from exchange_records e,user u,commodity c where e.user_id = u.id and e.commodity_id = c.id and e.user_id = ?1 order by e.time desc limit ?2,?3",nativeQuery = true)
    public List<Object[]> getExchangeRecordsByUserId(int userId,
                                                     int offset,
                                                     int rowCount);

    @Query(value = "select e.id,u.username,c.name,e.time from exchange_records e,user u,commodity c where e.user_id = u.id and e.commodity_id = c.id order by e.time desc limit ?1,?2",nativeQuery = true)
    public List<Object[]> getAllExchangeRecords(int offset, int rowCount);

}
