package com.tzc.wsc.SwimmingPoolManagementSystemServer.vo;

import java.util.Date;

public class CheckInOutTableItem {

    private String phone;
    private String vercode;
    private Date checkInTime;
    private Date checkOutTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
