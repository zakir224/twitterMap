package com.test.twittermap.data.twitter;

import com.test.twittermap.crypto.signature.Signature;
import com.test.twittermap.util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Config {

    private String url;
    private JSONObject params;
    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public JSONObject getParams() {
        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }

    public HashMap<String, String> getEncodedConfigParams() {
        HashMap<String, String> encodedKeyValuePairs = new HashMap<>();
        JSONArray keys = this.params.names ();
        for (int i = 0; i < keys.length (); ++i) {
            try {
                String key = keys.getString (i);
                String value = params.getString (key);
                encodedKeyValuePairs.put(StringUtil.INSTANCE.getEncodedString(key)
                        , StringUtil.INSTANCE.getEncodedString(value));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return encodedKeyValuePairs;
    }


    public String getEncodedConfig() throws UnsupportedEncodingException {
        return StringUtil.INSTANCE.getEncodedString(method.toUpperCase()) +
                "&" + StringUtil.INSTANCE.getEncodedString(url);
    }
}
