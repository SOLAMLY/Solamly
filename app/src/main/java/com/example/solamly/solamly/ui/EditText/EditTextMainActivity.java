package com.example.solamly.solamly.ui.EditText;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.solamly.basemodule.base.ui.BaseActivity;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.ui.EditText.RichTextEditor.EditData;
import com.example.solamly.solamly.ui.activity.MediaActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * @Author SOLAMLY
 * @Date 2018/8/31 18:14
 * @Description: https://github.com/xmuSistone/AnimRichEditor
 */

public class EditTextMainActivity extends BaseActivity {
    private static final int REQUEST_IMAGE = 0;


    @Override
    protected int setLayout() {
        return R.layout.activity_edit_main;
    }

    @Override
    protected void initView() {
        editor = (RichTextEditor) findViewById(R.id.richEditor);
        btnListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editor.hideKeyBoard();
                if (v.getId() == btn2.getId()) {
                    // 打开相机
                    intiImageSelector();
                } else if (v.getId() == btn3.getId()) {
                    List<EditData> editList = editor.buildEditData();
                    // 下面的代码可以上传、或者保存，请自行实现
                    dealEditData(editList);
                }
            }
        };

        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        btn2.setOnClickListener(btnListener);
        btn3.setOnClickListener(btnListener);

    }

    @Override
    protected void initData() {

    }


    private static final int REQUEST_CODE_PICK_IMAGE = 1023;
    private static final int REQUEST_CODE_CAPTURE_CAMEIA = 1022;
    private RichTextEditor editor;
    private View btn2, btn3;
    private View.OnClickListener btnListener;

    private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    private File mCurrentPhotoFile;// 照相机拍照得到的图片


    /**
     * 负责处理编辑数据提交等事宜，请自行实现
     */
    protected void dealEditData(List<EditData> editList) {
        for (EditData itemData : editList) {
            if (itemData.inputStr != null) {
                Log.d("RichEditor", "commit inputStr=" + itemData.inputStr);
                stringBuilder.append(itemData.inputStr);
                stringBuilder.append("</br>");
            } else if (itemData.imagePath != null) {
                Log.d("RichEditor", "commit imgePath=" + itemData.imagePath);
                stringBuilder.append("<img src=\"");
                stringBuilder.append(itemData.imagePath);
                stringBuilder.append("\"/>");
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("URL", stringBuilder.toString());
        skip(MediaActivity.class, bundle);
    }

    private StringBuilder stringBuilder = new StringBuilder();

    protected void openCamera() {
        try {
            // Launch camera to take photo for selected contact
            PHOTO_DIR.mkdirs();// 创建照片的存储目录
            mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
            final Intent intent = getTakePickIntent(mCurrentPhotoFile);
            startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
        } catch (ActivityNotFoundException e) {
        }
    }

    public static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        return intent;
    }

    /**
     * 用当前时间给取得的图片命名
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     * 初始化图片选择器
     */
    public void intiImageSelector() {
        MultiImageSelector
                .create()
                .showCamera(true)
                .count(9)
                .start(this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
            insertBitmap(mCurrentPhotoFile.getAbsolutePath());
        } else if (requestCode == REQUEST_IMAGE) {
            List<String> imagePath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            for (int i = 0; i < imagePath.size(); i++) {
                insertBitmap(imagePath.get(i));
            }
        }


    }

    /**
     * 添加图片到富文本剪辑器
     *
     * @param imagePath
     */
    private void insertBitmap(String imagePath) {
        editor.insertImage(imagePath);
    }

    /**
     * 根据Uri获取图片文件的绝对路径
     */
    public String getRealFilePath(final Uri uri) {
        if (null == uri) {
            return null;
        }

        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
