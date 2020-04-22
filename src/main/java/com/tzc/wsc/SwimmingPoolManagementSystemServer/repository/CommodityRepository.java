package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CommodityRepository extends CrudRepository<Commodity,Integer> {

    @Query(value = "select * from commodity",nativeQuery = true)
    Page<List<Commodity>> getAllCommodities(Pageable pageable);

}
