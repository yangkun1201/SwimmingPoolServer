package com.tzc.wsc.SwimmingPoolManagementSystemServer.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.util.HashMap;
import java.util.Map;

public class AliPayUtil {

    private static Map<String, String> paramsMap = new HashMap<>();

    public static String pay(String commodityName,double amount,long tradeNo){
        String form = "";
        try {
            // 1. 创建AlipayClient实例
            AlipayClient alipayClient = new DefaultAlipayClient(
                    "https://openapi.alipaydev.com/gateway.do",
                    "2016101800718866",
                    "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCsrCecvOqboH8cf6WZb0x7a6bMsYQqWYEoXs+fPvnMFLHzx12IWR0rblk1dLBbbN0yjA/fWYCABFqeyEvsJmUDUTGtzNppVpu9KCqPVFxW3pK9m9iNvr2lnJtfuUCf+lhchwZ7oeOwj7zMFAiwle6W7wGUdn3Tc6siLMeYwm+m9gOHvY8IRDY5N8Z5PnUY07JV8tJi4dq99iQNdK3cNf8bDQLMZQYzIjOGIxkVyzNhZO7IwE3jSwyiGDsxjR2xWGS6+PPtbZk2h5wi5qvkdi2wHJa9GSvfFIB09SIyyKk2yBBpQxcTnh2S3Bm66tIUtXRJoav15aavbf+6u5Y1ChPJAgMBAAECggEAWXUDPLe/EAuRbxFOmPRJenOmQcwB/fLK8EBWblkxDyL1oqd59Ka+bv7HZuNibqsy2fooSv1SjoAy9qosc1bc5GAPTcygpwxAuTny+rio8jRzb5orEhxsoj1FXc1bBsarOpyVpj2T+aguFsiWS+4RYs7GUcUloQP++ECc066AK83UXBjb2RSyYfE6SHNvKNP9GlsSgwitFuZzSsx2Ow1Z2SKEv+iQBsGPJYxwVkZ6KgoTiLBJflFv/BI1G/THXCgtvyBiqkxYYIoqK6J9sYKumvCdY3m9Ol7eehIMnR6ijn3naiuhljAxIMoWcJw8qZ0QBXXwSVqePicH5GCIjrQTEQKBgQDhXB/blN/DBkrI581XJk44KD5avQKBSXhNCWbFUpPJjnxr1sLfJgwxg7i9QZpl1GuIBgBI4I+i0VNdzVXsodUA0Udck4VD0EMVbURInpOWdwowR4CZsGjqpnp4hwmUPz2RFzFTmoWKZMC3DhtxjJeIgDUNQjTdbthBJcW8uGsJHQKBgQDEJjF3keEeH0W3suu9gNt2SQ7i1VtfCafT6kMtU8PBMNE8smnWh+PvwTLzBv7VSFwjhbOOCufhiWU+RvBzVhMR8e0cu7bWR1uZrHfrxD/6bWloTlHszYDw5z5YswcsNhh1QwrKglGiTh6WkQNct7m4KkgEFJAmNIEKLFWbH3/hnQKBgBeH4f+whMi9UHO32HYAFuzALvqUf712KWyJzcROwgWqrg0oiIJ6W42T/mcsgW2eh3fVgJQbdoP+rWC+/vjNUQtbzsK42JJjHY4QMbAPdsOgPF0cKi5iACJ4LZOGLhbfn5MVw93B5a20pXa3r1/k2TG0iVWQEysH1ua+he4Vp391AoGALl0kxjGaN6eGsDQ/msyRk3UTarSp414B6IpwFI+/LrQeUS2O7OG0FCDjrSRTKhDvjon09jdHtjzmzICyJ2EKIuy/clJAlofJdBqbQgRiZwxjpP8WSFYPC2Jtj0PKBz5GK7mMHHAPYcD7Y/WlCM54vLppyWUe6Zjxg3kVRxR8AtUCgYBDNiqpbuC8uVD/s8PvSuTSjrl/1GVYWIUfkf6R+/Q996QIVqmpLQg06O/0NI9x/BbW1STkn0dDdCH6C8EBxvJFdbH5dtsumC3dQUW0nz8PAmZAYm53ozb8fYNB0T6rtWKdVeiIY6Ic+SgyoPCAqllboSzH5cOEwcplPUfM9ii0xQ==",
                    "json",
                    "utf-8",
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArKwnnLzqm6B/HH+lmW9Me2umzLGEKlmBKF7Pnz75zBSx88ddiFkdK25ZNXSwW2zdMowP31mAgARanshL7CZlA1ExrczaaVabvSgqj1RcVt6SvZvYjb69pZybX7lAn/pYXIcGe6HjsI+8zBQIsJXulu8BlHZ903OrIizHmMJvpvYDh72PCEQ2OTfGeT51GNOyVfLSYuHavfYkDXSt3DX/Gw0CzGUGMyIzhiMZFcszYWTuyMBN40sMohg7MY0dsVhkuvjz7W2ZNoecIuar5HYtsByWvRkr3xSAdPUiMsipNsgQaUMXE54dktwZuurSFLV0SaGr9eWmr23/uruWNQoTyQIDAQAB",
                    "RSA2");
            // 2. 创建使用的Open API对应的Request请求对象
            AlipayTradePagePayRequest  request = getRequest(commodityName,amount,tradeNo);
            // 3. 发起请求并处理响应
            form = alipayClient.pageExecute(request).getBody();
        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return form;
    }

    private static AlipayTradePagePayRequest getRequest(String commodityName, double amount,long tradeNo) {
        AlipayTradePagePayRequest alipayRequest =  new AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( "http://127.0.0.1/swim/" );
        AlipayTradePayModel model = new AlipayTradePayModel();
        alipayRequest.setBizModel(model);
        model.setOutTradeNo(tradeNo+"");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setSubject(commodityName);
        model.setTotalAmount(String.valueOf(amount));
        model.setAuthCode("xxxxx");//沙箱钱包中的付款码
        model.setScene("bar_code");
        return alipayRequest;
    }

    public static boolean checkPayStatus(long tradeNo){
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016101800718866",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCsrCecvOqboH8cf6WZb0x7a6bMsYQqWYEoXs+fPvnMFLHzx12IWR0rblk1dLBbbN0yjA/fWYCABFqeyEvsJmUDUTGtzNppVpu9KCqPVFxW3pK9m9iNvr2lnJtfuUCf+lhchwZ7oeOwj7zMFAiwle6W7wGUdn3Tc6siLMeYwm+m9gOHvY8IRDY5N8Z5PnUY07JV8tJi4dq99iQNdK3cNf8bDQLMZQYzIjOGIxkVyzNhZO7IwE3jSwyiGDsxjR2xWGS6+PPtbZk2h5wi5qvkdi2wHJa9GSvfFIB09SIyyKk2yBBpQxcTnh2S3Bm66tIUtXRJoav15aavbf+6u5Y1ChPJAgMBAAECggEAWXUDPLe/EAuRbxFOmPRJenOmQcwB/fLK8EBWblkxDyL1oqd59Ka+bv7HZuNibqsy2fooSv1SjoAy9qosc1bc5GAPTcygpwxAuTny+rio8jRzb5orEhxsoj1FXc1bBsarOpyVpj2T+aguFsiWS+4RYs7GUcUloQP++ECc066AK83UXBjb2RSyYfE6SHNvKNP9GlsSgwitFuZzSsx2Ow1Z2SKEv+iQBsGPJYxwVkZ6KgoTiLBJflFv/BI1G/THXCgtvyBiqkxYYIoqK6J9sYKumvCdY3m9Ol7eehIMnR6ijn3naiuhljAxIMoWcJw8qZ0QBXXwSVqePicH5GCIjrQTEQKBgQDhXB/blN/DBkrI581XJk44KD5avQKBSXhNCWbFUpPJjnxr1sLfJgwxg7i9QZpl1GuIBgBI4I+i0VNdzVXsodUA0Udck4VD0EMVbURInpOWdwowR4CZsGjqpnp4hwmUPz2RFzFTmoWKZMC3DhtxjJeIgDUNQjTdbthBJcW8uGsJHQKBgQDEJjF3keEeH0W3suu9gNt2SQ7i1VtfCafT6kMtU8PBMNE8smnWh+PvwTLzBv7VSFwjhbOOCufhiWU+RvBzVhMR8e0cu7bWR1uZrHfrxD/6bWloTlHszYDw5z5YswcsNhh1QwrKglGiTh6WkQNct7m4KkgEFJAmNIEKLFWbH3/hnQKBgBeH4f+whMi9UHO32HYAFuzALvqUf712KWyJzcROwgWqrg0oiIJ6W42T/mcsgW2eh3fVgJQbdoP+rWC+/vjNUQtbzsK42JJjHY4QMbAPdsOgPF0cKi5iACJ4LZOGLhbfn5MVw93B5a20pXa3r1/k2TG0iVWQEysH1ua+he4Vp391AoGALl0kxjGaN6eGsDQ/msyRk3UTarSp414B6IpwFI+/LrQeUS2O7OG0FCDjrSRTKhDvjon09jdHtjzmzICyJ2EKIuy/clJAlofJdBqbQgRiZwxjpP8WSFYPC2Jtj0PKBz5GK7mMHHAPYcD7Y/WlCM54vLppyWUe6Zjxg3kVRxR8AtUCgYBDNiqpbuC8uVD/s8PvSuTSjrl/1GVYWIUfkf6R+/Q996QIVqmpLQg06O/0NI9x/BbW1STkn0dDdCH6C8EBxvJFdbH5dtsumC3dQUW0nz8PAmZAYm53ozb8fYNB0T6rtWKdVeiIY6Ic+SgyoPCAqllboSzH5cOEwcplPUfM9ii0xQ==",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArKwnnLzqm6B/HH+lmW9Me2umzLGEKlmBKF7Pnz75zBSx88ddiFkdK25ZNXSwW2zdMowP31mAgARanshL7CZlA1ExrczaaVabvSgqj1RcVt6SvZvYjb69pZybX7lAn/pYXIcGe6HjsI+8zBQIsJXulu8BlHZ903OrIizHmMJvpvYDh72PCEQ2OTfGeT51GNOyVfLSYuHavfYkDXSt3DX/Gw0CzGUGMyIzhiMZFcszYWTuyMBN40sMohg7MY0dsVhkuvjz7W2ZNoecIuar5HYtsByWvRkr3xSAdPUiMsipNsgQaUMXE54dktwZuurSFLV0SaGr9eWmr23/uruWNQoTyQIDAQAB",
                "RSA2");
        AlipayTradeQueryRequest request =  new  AlipayTradeQueryRequest();
        request.setBizContent( "{"  +
                "    \"out_trade_no\":"+tradeNo+
                "  }" );
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return false;
        }
        if (response.isSuccess()){
            return true;
        }  else  {
            return false;
        }
    }

}
