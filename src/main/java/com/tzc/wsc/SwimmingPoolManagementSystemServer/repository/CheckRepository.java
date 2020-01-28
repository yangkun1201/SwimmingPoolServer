package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CheckItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends CrudRepository<CheckItem,Integer> {

    public List<CheckItem> getCheckItemsByPhoneOrderByCheckTimeDesc(String phone);

    public List<CheckItem>  getCheckItemsByVerCodeEqualsOrderByCheckTimeDesc(String verCode);

}
