package com.tistory.charlezz.charleslecture2;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Charles on 16. 1. 17..
 */
public class NetworkManager {
    private static NetworkManager instance = new NetworkManager();

    public static NetworkManager getInstance() {
        return instance;
    }

    private NetworkManager() {
        client = new AsyncHttpClient();
        client.addHeader("X-Parse-Application-Id", "T65yngZbqdFZEd9pbczlqs8H0oWv7ibI30ZG4wG1");
        client.addHeader("X-Parse-REST-API-Key", "6beYHyz6IDFw9iW2yNNPWH3s6pqpjt2UmbkVYFhh");
//        client.addHeader("Content-Type", "application/json");
    }

    public static final String BASE_URL = "https://api.parse.com/1/classes/";
    public static final String TABLE_PERSON = "Person";

    private AsyncHttpClient client;

    public void addPerson(Person data, AsyncHttpResponseHandler handler) throws JSONException, UnsupportedEncodingException {

        JSONObject json = new JSONObject();
        json.put("name", data.getName());
        json.put("age", data.getAge());

        StringEntity entity = new StringEntity(json.toString());

        client.post(MyApp.getContext(), getAbsoluteUrl(TABLE_PERSON), entity, "application/json", handler);
    }

    public void getPersons(BaseJsonHttpResponseHandler<JSONObject> handler) {
        client.get(MyApp.getContext(), getAbsoluteUrl(TABLE_PERSON), handler);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
