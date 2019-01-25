package com.example.solamly.solamly.module.network.network;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.module.network.network.OKHttp.OKHttp3Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static com.example.solamly.basemodule.util.other.ConvertUtil.getPath;
import static com.example.solamly.solamly.module.network.network.HttpUtil.METHOD_GET;
import static com.example.solamly.solamly.module.network.network.HttpUtil.METHOD_POST;
import static com.example.solamly.solamly.module.network.network.HttpUtil.setUrlConnection;
import static com.example.solamly.solamly.module.network.network.Retrofit.RetrofitUtil.postRetrofitM;
import static com.example.solamly.solamly.module.network.network.Retrofit.RetrofitUtil.postRetrofitUpload;
import static com.example.solamly.solamly.module.network.network.Retrofit.RetrofitUtil.postRetrofitUploadLlist;

/**
 * @Author SOLAMLY
 * @Date 2018/9/6 10:15
 * @Description:
 */

public class HttpActivity extends BaseActivity {
    private static final String TAG = "HTTP_URL_CONNECTION";
    @BindView(R.id.et_url)
    EditText editText;


    private static final String url1 = "http://app.meibbc.com/BeautifyBreast/user!findUser.action";
    private static final String url2 = "http://fz.meibbc.com/information-core/controller/PersonalController.html?behavior=PersonalPage";
    private static final String url3 = "http://fz.meibbc.com/information-core/controller/VideoCollectionController/list.html";
    private static final String url4 = "http://fz.meibbc.com/information-core/controller/InformationCommentController/CommonList.html";
    private static final String url_bf = "https://www.bai";

    private static final String url = url4;

    @Override
    protected int setLayout() {
        return R.layout.activity_http_url_connection;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({
            R.id.btn_free,
            R.id.btn_post,
            R.id.btn_get,
            R.id.btn_get_okhttp,
            R.id.btn_post_okhttp,
            R.id.btn_post_file,
            R.id.btn_post_image,
            R.id.btn_post_retrofit_pramars,
            R.id.btn_post_retrofit,
            R.id.btn_post_retrofit_upload

    })
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_free:
                setUrlConnection(editText.getText().toString(), METHOD_GET, null);
                break;
            case R.id.btn_post:
                setUrlConnection(url, METHOD_POST, getMap());
                break;
            case R.id.btn_get:
                setUrlConnection(url, METHOD_GET, getMap());
                break;

            /**
             * OK-HTTP请求
             */
            case R.id.btn_get_okhttp:
                OKHttp3Util.getOkHttp(url_bf);
                break;
            case R.id.btn_post_okhttp:
                OKHttp3Util.postAsynOkHttp(url3);
                break;
            case R.id.btn_post_image:
                intiImageSelector(TYPE_OKHTTP_MORE);
                break;
            case R.id.btn_post_file:
                intiSystem(TYPE_OKHTTP_MORE);
                break;

            /**
             * Retrofit
             */
            case R.id.btn_post_retrofit:
                postRetrofitM();
                break;
            case R.id.btn_post_retrofit_pramars:
                intiSystem(TYPE_RETROFIT_MORE);
                break;
            case R.id.btn_post_retrofit_upload:
                intiImageSelector(TYPE_RETROFIT_ONE);
                break;
        }
    }


    /**
     * 参数设置
     *
     * @return
     */
    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        switch (url) {
            case url2:
                map.put("userid", "2caae2956abb4634ba51527770801846");
                map.put("lookuserid", "7aaa8c8864924421b4cba5a26043bf94");
                break;
            case url3:
                map.put("userid", "7aaa8c8864924421b4cba5a26043bf94");
                map.put("pgindex", "1");
                map.put("pagesize", "10");
                break;
            case url4:
                map.put("informationid", "c1_2d4c5a16a2844bbd89b9b5b1fa638e7c");
                map.put("pageSize", "10");
                map.put("page", "1");
                break;
        }
        return map;
    }

    /**
     * 打开相册
     * @param type
     */
    public void intiImageSelector(int type) {
        OPEN_TYPE = type;
        MultiImageSelector
                .create()
                .showCamera(true)
                .count(9)
                .start(this, REQUEST_IMAGE);
    }

    private static final int TYPE_RETROFIT_ONE = 999;
    private static final int TYPE_RETROFIT_MORE = 998;
    private static final int TYPE_OKHTTP_MORE = 997;
    private static int OPEN_TYPE;


    private static final int REQUEST_IMAGE = 0;
    private List<String> imagePath = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE:
                    imagePath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    for (int i = 0; i < imagePath.size(); i++) {
                        Log.e("选择的图片", imagePath.get(i));
                        if (OPEN_TYPE == TYPE_RETROFIT_ONE) {
                            postRetrofitUpload(imagePath.get(i));
                        }
                    }
                    if (OPEN_TYPE == TYPE_RETROFIT_MORE) {
                        postRetrofitUploadLlist(imagePath, "oFileName");
                    } else if (OPEN_TYPE == TYPE_OKHTTP_MORE) {
                        OKHttp3Util.postAsyncFile(imagePath);
                    }
                    break;
                case 1:
                    Uri uri = data.getData();
                    List<String> list = new ArrayList<>();
                    list.add(getPath(this, uri));
                    Log.e("选择的文件", getPath(this, uri));
                    if (OPEN_TYPE == TYPE_RETROFIT_MORE) {
                        postRetrofitUploadLlist(list, "oFileName");
                    } else if (OPEN_TYPE == TYPE_OKHTTP_MORE) {
                        OKHttp3Util.postAsyncFile(list);
                    }
                    break;
            }
        }
    }

    /**
     * 打开系统文件管理器
     */
    public void intiSystem(int type) {
        OPEN_TYPE = type;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);

    }


}
