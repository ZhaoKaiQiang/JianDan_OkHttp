package com.socks.jiandan.callback;

/**
 * Created by zhaokaiqiang on 15/11/7.
 */
public interface LoadResultCallBack<T> {

    int SUCCESS_OK = 1001;
    int SUCCESS_NONE = 1002;
    int ERROR_NET = 1003;

    void onSuccess(int result, T t);

    void onError(int code, String msg);
}
