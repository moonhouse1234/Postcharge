package com.yzcx.postcharge;


import com.yzcx.postcharge.constants.AppConstants;

/**
 * @author 闫鹏飞
 *         create at 2017-11-15 0015
 *         description: 数据请求地址
 */
public class Urls {

    //获取地区json文件
    public static String ADDRESS_JSON = AppConstants.URL + "/regionFile";
    //邮件资费计算
    public static String CALUE = AppConstants.URL + "/ds/parcel/postage";
    //获取快递产品列表
    public static String PRODUCT_LIST = AppConstants.URL + "/ds/product/list";
}
