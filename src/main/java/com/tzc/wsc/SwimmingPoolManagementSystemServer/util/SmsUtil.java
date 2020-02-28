package com.tzc.wsc.SwimmingPoolManagementSystemServer.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class SmsUtil {

    private Logger logger = LoggerFactory.getLogger(SmsUtil.class);
    private volatile static SmsUtil instances;
    @Value("")
    private String accessKeyId;
    @Value("")
    private String secret;

    public static SmsUtil getInstances() {
        if(instances == null){
            synchronized (SmsUtil.class){
                if(instances == null){
                    instances = new SmsUtil();
                }
            }
        }
        return instances;
    }

    public String sendSms(String phone,String message){

        String sendResult = "";

        Gson gson = new Gson();
        Map<String,Object> data = new HashMap<>();
        data.put("code",message);
        String templateParam = gson.toJson(data);

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "杨坤");
        request.putQueryParameter("TemplateCode", "SMS_163438131");
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            //System.out.println(response.getData());
            sendResult = response.getData();
            logger.info(sendResult);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return sendResult;
    }

}
