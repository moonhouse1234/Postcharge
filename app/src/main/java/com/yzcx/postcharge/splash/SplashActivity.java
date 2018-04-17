package com.yzcx.postcharge.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yzcx.postcharge.R;
import com.yzcx.postcharge.Urls;
import com.yzcx.postcharge.charge.PostageQueryActivity;
import com.yzcx.postcharge.constants.SpConstants;
import com.yzcx.postcharge.http.OkHttpUntil;
import com.yzcx.postcharge.until.ParameterUtil;
import com.yzcx.postcharge.until.SPUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuqingjie
 * @create 2018/4/16
 * @description 启动页
 */

public class SplashActivity extends AppCompatActivity{
    private static String TAG = "SplashActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        //下载json文件
        downloadAddress();
        //获取快递产品列表
        getProductList();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterMainActivity();
            }
        }, 2000);
    }

    private void getProductList() {
        Map<String, String> mapParams =  new HashMap<String, String>();
        mapParams.put("sign", ParameterUtil.getSign());
        mapParams.put("timestamp",ParameterUtil.getTimestamp());
        OkHttpUntil.getNoToken(Urls.PRODUCT_LIST,mapParams,new ProductCall());
    }

    private void downloadAddress() {
    }

    /**
     * 进入资费计算页面
     */
    private void enterMainActivity() {
        Intent intent = new Intent(this, PostageQueryActivity.class);
        startActivity(intent);
        finish();
    }
    class ProductCall implements Callback{

        @Override
        public void onFailure(Call call, IOException e) {
            Log.i(TAG,"失败");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
                String productJson = response.body().string();
            //将数据保存到偏好设置里面
            SPUtils.getInstance().put(SpConstants.PRODUCT_LIST, productJson);
        }
    }
    
}
