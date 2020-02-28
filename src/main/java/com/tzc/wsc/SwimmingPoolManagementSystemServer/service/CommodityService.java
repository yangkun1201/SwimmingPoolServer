package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.Commodity;

import java.util.List;

public interface CommodityService {

    void addCommodity(Commodity commodity) throws Exception;

    List<Commodity> getAllCommodities(int page, int pageSize);

    void deleteCommodityById(int id);

}
