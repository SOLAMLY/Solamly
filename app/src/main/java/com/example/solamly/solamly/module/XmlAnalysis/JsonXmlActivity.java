package com.example.solamly.solamly.module.XmlAnalysis;

import android.util.Log;
import android.view.View;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.OnClick;

/**
 * @Author SOLAMLY
 * @Date 2018/9/17 10:18
 * @Description:
 */

public class JsonXmlActivity extends BaseActivity {
    private String array;
    private String object;
    private String jsonArray_Object;

    @Override
    protected int setLayout() {
        return R.layout.activity_json_xml;
    }

    @Override
    protected void initView() {
        array = "[{\"devid\":\"1234567800\",\"latitude\":\"29.4963\",\"longitude\":\"116.189\"}," +
                "{\"devid\":\"1234567832\",\"latitude\":\"29.4943\",\"longitude\":\"1161.129\"}]";
        object = "{\"name\":\"名\",\"sex\":\"女\",\"age\":\"10\"}";
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({
            R.id.json_array,
            R.id.json_object_array,
            R.id.json_object,
            R.id.xml_dom,
            R.id.xml_sax
    })
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.json_array:
                try {
                    JSONArray jsonArray = new JSONArray(array);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Log.e("devid", jsonObject.getString("devid"));
                        Log.e("latitude", jsonObject.getString("latitude"));
                        Log.e("longitude", jsonObject.getString("longitude"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.json_object_array:
                break;
            case R.id.json_object:
                try {
                    JSONObject js = new JSONObject(object);
                    Log.e("name", js.getString("name"));
                    Log.e("age", js.getString("age"));
                    Log.e("sex", js.getString("sex"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.xml_dom:
                try {
                    XmlAnalysisUtil.xmlToDom(getResources().getAssets().open("students.xml")
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.xml_sax:
                try {
                    XmlAnalysisUtil.xmlOfPull(getResources().getAssets().open("students.xml")
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
