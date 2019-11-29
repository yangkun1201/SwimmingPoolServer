package com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String username;
    @Column(length = 20)
    private String phone;
    @Column(length = 100)
    private String password;
    @Column
    private int gender;
    @Column
    private int type;
}
