package cn.tamilin.springcloud.order.utils;

import java.util.Random;

/**
 * @ClassName KeyUtil
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/8 00:11
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间 + 随机数
     * @return
     */
    public static synchronized String genUniquekey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
