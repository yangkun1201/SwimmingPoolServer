package com.tzc.wsc.SwimmingPoolManagementSystemServer.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInCountNearWeekItem {
    private String date;
    private int count;
}
