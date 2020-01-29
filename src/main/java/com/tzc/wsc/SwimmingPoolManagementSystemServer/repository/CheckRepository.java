package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CheckItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends CrudRepository<CheckItem,Integer> {

    public List<CheckItem> getCheckItemsByPhoneOrderByCheckTimeDesc(String phone);

    public List<CheckItem>  getCheckItemsByVerCodeEqualsOrderByCheckTimeDesc(String verCode);

    @Query(value = "select a.phone,a.vercode,a.check_time check_in_time,b.check_time check_out_time from checkin a,checkin b where a.phone = b.phone and a.vercode = b.vercode and a.check_time < b.check_time limit :page,:pageSize",nativeQuery = true)
    public List<Object[]> getCheckInOutRecords(@Param("page") int page, @Param("pageSize") int pageSize);

}
