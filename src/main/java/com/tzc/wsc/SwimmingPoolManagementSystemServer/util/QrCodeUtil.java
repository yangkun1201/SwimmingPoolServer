package com.tzc.wsc.SwimmingPoolManagementSystemServer.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtil {

    private static volatile QrCodeUtil instances;

    private static final int width = 300;// 默认二维码宽度
    private static final int height = 300;// 默认二维码高度
    private static final String format = "png";// 默认二维码文件格式
    private static final Map<EncodeHintType, Object> hints = new HashMap();// 二维码参数

    static {
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 字符编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.MARGIN, 2);// 二维码与图片边距
    }

    private QrCodeUtil(){}

    public static QrCodeUtil getInstance() {
        if(instances == null){
            synchronized (QrCodeUtil.class){
                if(instances == null){
                    instances = new QrCodeUtil();
                }
            }
        }
        return instances;
    }

    public byte[] createQrCode(String content) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    public String getUuid(){
        Long temp0 = System.currentTimeMillis();
        String temp1 = temp0.toString();
        int length = temp1.length();
        String uuid = temp1.substring(length-6,length);
        return uuid;
    }

}
