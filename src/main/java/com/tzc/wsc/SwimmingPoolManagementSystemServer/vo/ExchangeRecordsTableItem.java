package com.tzc.wsc.SwimmingPoolManagementSystemServer.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRecordsTableItem {

    private int id;
    private String username;
    private String commodityName;
    private Date time;
}
