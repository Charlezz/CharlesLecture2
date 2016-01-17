package com.tistory.charlezz.charleslecture2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity";
    private android.widget.EditText editname;
    private android.widget.EditText editage;
    private android.widget.Button btnsave;
    private android.widget.Button retrieve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnsave = (Button) findViewById(R.id.btn_save);
        this.editage = (EditText) findViewById(R.id.edit_age);
        this.editname = (EditText) findViewById(R.id.edit_name);
        this.retrieve = (Button) findViewById(R.id.retrieve);

        btnsave.setOnClickListener(this);
        retrieve.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnsave)) {
            Person mPerson = new Person();
            mPerson.setName(editname.getText().toString());
            mPerson.setAge(Integer.parseInt(editage.getText().toString()));

            try {
                NetworkManager.getInstance().addPerson(mPerson, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e(TAG, "onSuccess");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e(TAG, "onFailure");
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return;
        }

        if (v.equals(retrieve)) {
            NetworkManager.getInstance().getPersons(new BaseJsonHttpResponseHandler<JSONObject>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                    try {
                        JSONArray array = new JSONArray(response.get("results").toString());
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObj = array.getJSONObject(i);
                            Log.e(TAG, "name:" + jsonObj.getString("name") + " age:" + jsonObj.getInt("age") + " id:" + jsonObj.getString("objectId"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                    Log.e(TAG, "onFailure");
                }

                @Override
                protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    if (!isFailure) {
                        Log.e(TAG, rawJsonData);
                        return new JSONObject(rawJsonData);
                    }
                    return null;
                }
            });
            return;
        }
    }
}
