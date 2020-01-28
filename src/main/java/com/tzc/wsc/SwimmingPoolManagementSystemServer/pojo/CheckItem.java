package com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checkin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String phone;
    @Column(name = "check_time")
    private Date checkTime;
    @Column(name = "check_flag")
    private int checkFlag;
    @Column(name = "vercode")
    private String verCode;


}
