package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.CheckItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends JpaRepository<CheckItem,Integer> {

    public List<CheckItem> getCheckItemsByPhoneOrderByCheckTimeDesc(String phone);

    public List<CheckItem>  getCheckItemsByVerCodeEqualsOrderByCheckTimeDesc(String verCode);

    @Query(value = "select * from checkin t where phone = :phone and vercode = :vercode order by t.check_time desc",nativeQuery = true)
    public Page<List<CheckItem>> getCheckInOutRecords(@Param("phone") String phone, @Param("vercode") String vercode, Pageable pageable);

    @Query(value = "select * from checkin t order by t.check_time desc",nativeQuery = true)
    public Page<List<CheckItem>> getCheckInOutRecords(Pageable pageable);

    @Query(value = "select * from checkin t where phone = :phone order by t.check_time desc",nativeQuery = true)
    public Page<List<CheckItem>>getCheckInOutRecords(@Param("phone") String phone, Pageable pageable);

    @Query(value = "select * from checkin t where vercode = :vercode order by t.check_time desc",nativeQuery = true)
    public Page<List<CheckItem>> getCheckInOutRecords(Pageable pageable,@Param("vercode") String vercode);

    @Query(value = "select count(c.phone) from checkin c,user u where c.check_flag = 0 and u.gender = 0 and c.phone = u.phone and c.check_time > curdate()", nativeQuery = true)
    public int getMaleCountToday();

    @Query(value = "select count(c.phone) from checkin c,user u where c.check_flag = 0 and u.gender = 1 and c.phone = u.phone and c.check_time > curdate()", nativeQuery = true)
    public int getFemaleCountToday();

    @Query(value = "select\n" +
            "date_format(c.check_time,'%Y-%m-%d') as date,\n" +
            "count(c.phone) as num\n" +
            "from checkin c\n" +
            "where c.check_flag = 0\n" +
            "and c.check_time > date_sub(curdate(),interval 1 week)\n" +
            "group by date_format(c.check_time,'%Y-%m-%d')\n" +
            "order by date_format(c.check_time,'%Y-%m-%d')",nativeQuery = true)
    public List<Object[]> getCheckInCountNearWeek();

}
