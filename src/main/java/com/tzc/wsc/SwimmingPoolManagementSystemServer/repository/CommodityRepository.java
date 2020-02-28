package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.Commodity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends CrudRepository<Commodity,Integer> {

    @Query(value = "select * from commodity limit :offset,:rowCount ",nativeQuery = true)
    List<Commodity> getAllCommodities(int offset, int rowCount);

}
