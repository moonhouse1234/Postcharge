package com.yzcx.postcharge.until;


import com.yzcx.postcharge.constants.AppConstants;
import com.yzcx.postcharge.constants.SpConstants;

/**
 * @author 闫鹏飞
 *         create at 2017-11-15 0015
 *         description: 查询邮储网点信息
 */
public class ParameterUtil {


    //系统时间的10位的时间戳
    public static String TIMESTAMP = "";

    /**
     * @author 闫鹏飞
     * @time 2018-3-12 0012  15:58
     * @describe 获取通用参数Sign
     */
    public static String getSign() {

        //获取系统时间的10位的时间戳
        long time = System.currentTimeMillis() / 1000;
        TIMESTAMP = String.valueOf(time);

        String sign = AppConstants.PUBLIC_KEY + TIMESTAMP;
        String md51 = MD5Utils.getMd5Value(sign);
        String md52 = MD5Utils.getMd5Value(md51);
        return md52;
    }

    /**
     * @author 闫鹏飞
     * @time 2018-3-12 0012  15:59
     * @describe 获取通用参数timestamp
     */
    public static String getTimestamp() {
        return TIMESTAMP;
    }

    /**
     * @author 闫鹏飞
     * @time 2018-3-12 0012  15:59
     * @describe 获取通用参数token
     */
    public static String getToken() {
        String token = SPUtils.getInstance().getString(SpConstants.TOKEN);
        return token;
    }

}
