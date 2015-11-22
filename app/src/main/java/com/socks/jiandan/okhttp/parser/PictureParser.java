package com.socks.jiandan.okhttp.parser;

import android.support.annotation.Nullable;

import com.google.gson.reflect.TypeToken;
import com.socks.jiandan.model.Picture;
import com.socks.jiandan.net.JSONParser;
import com.socks.jiandan.okhttp.OkHttpBaseParser;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhaokaiqiang on 15/11/22.
 */
public class PictureParser extends OkHttpBaseParser<ArrayList<Picture>> {

    @Nullable
    public ArrayList<Picture> parse(Response response) {

        code = wrapperCode(response.code());
        if (!response.isSuccessful())
            return null;

        try {
            String jsonStr = response.body().string();
            jsonStr = new JSONObject(jsonStr).getJSONArray("comments").toString();
            return (ArrayList<Picture>) JSONParser.toObject(jsonStr,
                    new TypeToken<ArrayList<Picture>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
