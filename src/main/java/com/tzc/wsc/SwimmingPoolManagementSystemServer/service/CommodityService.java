package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommodityService {

    void addCommodity(Commodity commodity) throws Exception;

    Page<List<Commodity>> getAllCommodities(int page, int pageSize);

    void deleteCommodityById(int id);

}
