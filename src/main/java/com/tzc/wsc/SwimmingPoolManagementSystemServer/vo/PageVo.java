package com.tzc.wsc.SwimmingPoolManagementSystemServer.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageVo {
    Object data;
    int totalPage;
}
