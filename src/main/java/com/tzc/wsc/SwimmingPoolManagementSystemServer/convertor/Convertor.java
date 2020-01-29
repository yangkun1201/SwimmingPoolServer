package com.tzc.wsc.SwimmingPoolManagementSystemServer.convertor;

public interface Convertor<T> {
    T convert(Object[] obj);
}
