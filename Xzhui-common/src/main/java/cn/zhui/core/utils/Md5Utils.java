package cn.zhui.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LIN on 2018/06/07.
 */
public class Md5Utils {
   private final static String[] hexDigits = {
      "0", "1", "2", "3", "4", "5", "6", "7","8", "9", "a", "b", "c", "d", "e", "f"
   };

   public static String md5Object(Object object) throws NoSuchAlgorithmException, UnsupportedEncodingException, JsonProcessingException {
       if (object == null) {
           return "md5_null";
       }
       return md5Encode(JsonUtils.writeValueAsString(object));
   }

    /**
     * MD5编码
     * origin 原始字符串
     * 经过MD5加密之后的结果
     */
    public static String md5Encode(String origin) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String resultString = null;
        resultString = origin;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(resultString.getBytes("UTF-8"));
        resultString = byteArrayToHexString(messageDigest.digest());
        return resultString;
    }

    /**
     * 转换字节数组为16进制字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        for(byte bt : b) {
            stringBuilder.append(byteToHexString(bt));
        }
        return stringBuilder.toString();
    }

    /**
     * 转换byte到16进制
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if(n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
