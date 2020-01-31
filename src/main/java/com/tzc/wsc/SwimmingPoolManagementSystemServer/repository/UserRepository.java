package com.tzc.wsc.SwimmingPoolManagementSystemServer.repository;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    public User getUserByUsername(String username);

    public User getUserByPhone(String phone);

    @Query(value = "select * from user where username = :username and phone = :phone and type = :type limit :page,:pageSize",nativeQuery = true)
    public List<User> getUsersByUsernameAndPhoneAndType(String username,String phone,int type,int page,int pageSize);

    @Query(value = "select * from user where username = :username and type = :type limit :page,:pageSize",nativeQuery = true)
    public List<User> getUsersByUsernameAndType(String username,int type,int page,int pageSize);

    @Query(value = "select * from user where phone = :phone and type = :type limit :page,:pageSize",nativeQuery = true)
    public List<User> getUsersByPhoneAndType(String phone,int type,int page,int pageSize);

    @Query(value = "select * from user where type = :type limit :page,:pageSize",nativeQuery = true)
    public List<User> getUsersByType(int type,int page,int pageSize);

}
