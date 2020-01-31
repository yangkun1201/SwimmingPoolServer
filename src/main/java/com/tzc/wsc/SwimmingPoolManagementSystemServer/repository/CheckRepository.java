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

    @Query(value = "select * from checkin t where phone = :phone and vercode = :vercode order by t.check_time desc limit :page,:pageSize",nativeQuery = true)
    public List<CheckItem> getCheckInOutRecords(@Param("phone") String phone,@Param("vercode") String vercode, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "select * from checkin t order by t.check_time desc limit :page,:pageSize",nativeQuery = true)
    public List<CheckItem> getCheckInOutRecords(@Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "select * from checkin t where phone = :phone order by t.check_time desc limit :page,:pageSize",nativeQuery = true)
    public List<CheckItem> getCheckInOutRecords(@Param("phone") String phone, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "select * from checkin t where vercode = :vercode order by t.check_time desc limit :page,:pageSize",nativeQuery = true)
    public List<CheckItem> getCheckInOutRecords(@Param("page") int page, @Param("pageSize") int pageSize,@Param("vercode") String vercode);

}
