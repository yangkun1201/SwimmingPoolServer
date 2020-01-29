package com.tzc.wsc.SwimmingPoolManagementSystemServer.convertor;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.vo.CheckInOutTableItem;

import java.util.Date;

public class CheckInOutTableItemConvertor implements Convertor<CheckInOutTableItem> {
    @Override
    public CheckInOutTableItem convert(Object[] obj) {
        CheckInOutTableItem item = new CheckInOutTableItem();
        item.setPhone(((String) obj[0]));
        item.setVercode(((String) obj[1]));
        item.setCheckInTime(((Date) obj[2]));
        item.setCheckOutTime(((Date) obj[3]));
        return item;
    }
}
