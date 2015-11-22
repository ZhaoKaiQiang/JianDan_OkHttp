package com.socks.jiandan.okhttp;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaokaiqiang on 15/11/22.
 */
public class OkHttpProxy {

    private static OkHttpClient mHttpClient;

    public static void init() {
        synchronized (OkHttpProxy.class) {
            if (mHttpClient == null) {
                mHttpClient = new OkHttpClient();
                mHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
                mHttpClient.setReadTimeout(15, TimeUnit.SECONDS);
                mHttpClient.setWriteTimeout(15, TimeUnit.SECONDS);
            }
        }
    }

    public static OkHttpClient getInstance() {
        if (mHttpClient == null)
            init();
        return mHttpClient;
    }

    public static Call get(String url, OkHttpCallback responseCallback) {
        return get(url, null, responseCallback);
    }

    public static Call get(String url, Object tag, OkHttpCallback responseCallback) {
        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        Request request = builder.build();
        Call call = getInstance().newCall(request);
        call.enqueue(responseCallback);
        return call;
    }


    public static Call post(String url, Map<String, String> params, OkHttpCallback responseCallback) {
        return post(url, params, null, responseCallback);
    }

    public static Call post(String url, Map<String, String> params, Object tag, OkHttpCallback responseCallback) {

        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }

        FormEncodingBuilder encodingBuilder = new FormEncodingBuilder();

        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                encodingBuilder.add(key, params.get(key));
            }
        }

        RequestBody formBody = encodingBuilder.build();
        builder.post(formBody);

        Request request = builder.build();
        Call call = getInstance().newCall(request);
        call.enqueue(responseCallback);
        return call;
    }

    public static void cancel(Object tag) {
        getInstance().cancel(tag);
    }

}
