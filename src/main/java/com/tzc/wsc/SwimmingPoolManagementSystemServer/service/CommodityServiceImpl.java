package com.tzc.wsc.SwimmingPoolManagementSystemServer.service;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.Commodity;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public void addCommodity(Commodity commodity) throws Exception {
        commodityRepository.save(commodity);
    }

    @Override
    public Page<List<Commodity>> getAllCommodities(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        return commodityRepository.getAllCommodities(pageRequest);
    }

    @Override
    public void deleteCommodityById(int id) {
        commodityRepository.deleteById(id);
    }
}
