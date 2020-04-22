package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    public User getUserByUsername(String username);

    public User getUserByPhone(String phone);

    @Query(value = "select * from user where username = :username and phone = :phone and type = :type",nativeQuery = true)
    public Page<List<User>> getUsersByUsernameAndPhoneAndType(String username, String phone, int type, Pageable pageable);

    @Query(value = "select * from user where username = :username and type = :type",nativeQuery = true)
    public Page<List<User>> getUsersByUsernameAndType(String username,int type,Pageable pageable);

    @Query(value = "select * from user where phone = :phone and type = :type",nativeQuery = true)
    public Page<List<User>> getUsersByPhoneAndType(String phone,int type,Pageable pageable);

    @Query(value = "select * from user where type = :type",nativeQuery = true)
    public Page<List<User>> getUsersByType(int type,Pageable pageable);

    @Query(value = "select id from user u where u.username = :username",nativeQuery = true)
    public List<Integer> getUserIdByUsername(String username);

    @Query(value = "select card_type from user where phone = :phone",nativeQuery = true)
    public int getCardTypeByPhone(String phone);

}
