package com.socks.jiandan.okhttp.parser;

import android.support.annotation.Nullable;

import com.socks.jiandan.model.FreshNews;
import com.socks.jiandan.okhttp.OkHttpBaseParser;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhaokaiqiang on 15/11/22.
 */
public class FreshNewsParser extends OkHttpBaseParser<ArrayList<FreshNews>> {

    @Nullable
    public ArrayList<FreshNews> parse(Response response) {

        code = wrapperCode(response.code());
        if (!response.isSuccessful())
            return null;

        try {
            String body = response.body().string();
            JSONObject resultObj = new JSONObject(body);
            JSONArray postsArray = resultObj.optJSONArray("posts");
            return FreshNews.parse(postsArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
