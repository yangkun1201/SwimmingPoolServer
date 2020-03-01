package com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exchange_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "user_id")
    private int userId;
    @Column(columnDefinition = "commodity_id")
    private int commodityId;
    private Date time;

}
