package cn.zhui.core.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Random;

/**
 * 生成随机数，年月日时分秒加四位随机数
 * Created by LIN on 2018/06/07.
 */
public class UUIDUtils {

    public static String generateUUID(){

        StringBuffer uuid = new StringBuffer();
        uuid.append(DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
        uuid.append(new Random().nextInt(9999));
        return uuid.toString();
    }
    public static void main(String[] args) {
		System.out.println(UUIDUtils.generateUUID());
	}
}
