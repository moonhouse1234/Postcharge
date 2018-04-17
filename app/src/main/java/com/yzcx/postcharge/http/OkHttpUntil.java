package com.yzcx.postcharge.http;

import com.yzcx.postcharge.constants.SpConstants;
import com.yzcx.postcharge.until.SPUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author liuqingjie
 * @create 2018/3/15
 * @description 简单封装okhttp
 */

public class OkHttpUntil {
    private static OkHttpClient okHttpClient = null;

    private OkHttpUntil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(30, TimeUnit.SECONDS);//读取超时
        builder.connectTimeout(10, TimeUnit.SECONDS);//连接超时
        builder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        okHttpClient = builder.build();
    }

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttpUntil.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * get异步请求
     *
     * @param url
     * @param callback
     */
    public static void get(String url, Map<String, String> mapParams, Callback callback) {
        String token = SPUtils.getInstance().getString(SpConstants.TOKEN);
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        for (String key : mapParams.keySet()) {
            urlBuilder.addQueryParameter(key, mapParams.get(key));
        }
        Request request = reqBuild
                .url(urlBuilder.build())
                .addHeader("Authorization","Bearer"+token)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * get异步请求
     *
     * @param url
     * @param callback
     */
    public static void getNoToken(String url, Map<String, String> mapParams, Callback callback) {
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        for (String key : mapParams.keySet()) {
            urlBuilder.addQueryParameter(key, mapParams.get(key));
        }
        Request request = reqBuild
                .url(urlBuilder.build())
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }
    /**
     * post异步请求
     *
     * @param url
     * @param mapParams
     * @param callback
     */
    public static void post(String url, Map<String, String> mapParams, Callback callback) {
        String token = SPUtils.getInstance().getString(SpConstants.TOKEN);
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : mapParams.keySet()) {
            builder.add(key, mapParams.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .addHeader("Authorization","Bearer"+token)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * Post请求发送JSON数据
     *
     * @param url
     * @param jsonParams
     * @param callback
     */
    public static void doPost(String url, String jsonParams, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * 上传文件
     *
     * @param url
     * @param pathName
     * @param fileName
     * @param callback
     */
    public static void doFile(String url, String pathName, String fileName, Callback callback) {
        //判断文件类型
        MediaType MEDIA_TYPE = MediaType.parse(judgeType(pathName));
        //创建文件参数
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(MEDIA_TYPE.type(), fileName,
                        RequestBody.create(MEDIA_TYPE, new File(pathName)));
        //发出请求参数
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "9199fdef135c122")
                .url(url)
                .post(builder.build())
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * 根据文件路径判断MediaType
     *
     * @param path
     * @return
     */
    private static String judgeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * 下载文件
     *
     * @param url
     * @param fileDir
     * @param fileName
     */
    public static void downFile(String url, final String fileDir, final String fileName) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = new File(fileDir, fileName);
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) is.close();
                    if (fos != null) fos.close();
                }
            }
        });
    }


}
