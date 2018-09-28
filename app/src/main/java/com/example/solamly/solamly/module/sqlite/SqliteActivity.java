package com.example.solamly.solamly.module.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.solamly.solamly.module.sqlite.MySqliteHelper.DB_NAME;
import static com.example.solamly.solamly.module.sqlite.MySqliteHelper.TABLE_NAME;

/**
 * @Author SOLAMLY
 * @Date 2018/9/3 14:36
 * @Description:
 */

public class SqliteActivity extends BaseActivity {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_sex)
    EditText etSex;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;

    SQLiteDatabase db;

    private MySqliteHelper dbHelper;

    @Override
    protected int setLayout() {
        return R.layout.activity_sqlite_main;
    }

    @Override
    protected void initView() {

        dbHelper = new MySqliteHelper(this,DB_NAME,null,1);
        db = dbHelper.getReadableDatabase();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    private void insert(SQLiteDatabase db) {
        //第一种
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", etName.getText().toString());
        contentValues.put("sex", etSex.getText().toString());
        /**
         * 参数1  表名称，
         * 参数2  空列的默认值
         * 参数3  ContentValues类型的一个封装了列名称和列值的Map；
         */
        db.insert(TABLE_NAME, null, contentValues);

        //第二种
        String insert = "insert into " + TABLE_NAME
                + "(name,sex)"
                + " values(" + etName.getText().toString() + ","+ etSex.getText().toString() +")";
        db.execSQL(insert);
        Log.e("TAG", "数据插入成功");
    }

    private void deleted(SQLiteDatabase db) {
        String claus =  "name=?";           //条件
        String[] where = new String[]{etName.getText().toString()};           //参数
        db.delete(TABLE_NAME, claus, where);
        Log.e("TAG", "数据删除成功");
    }

    public void update(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", etName.getText().toString());
        String clause = "name=?";
        String[] whereArgs = new String[]{"aaa"};
        db.update(TABLE_NAME, contentValues, clause, whereArgs);
        Log.e("TAG","修改成功");
    }

    /**
     * public  Cursor query(String table,String[] columns,String selection,String[]  selectionArgs,String groupBy,String having,String orderBy,String limit);
     * 参数table:表名称
     * 参数columns:列名称数组
     * 参数selection:条件字句，相当于where
     * 参数selectionArgs:条件字句，参数数组
     * 参数groupBy:分组列
     * 参数having:分组条件
     * 参数orderBy:排序列
     * 参数limit:分页查询限制
     *
     * @param db
     */
    private void query(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                cursor.move(i);
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                Log.e("TAG","查询数据:"+id +"  name:"+ name + "  sex:"+sex);
            }
        }
    }

    @OnClick({
            R.id.add,
            R.id.del,
            R.id.edit,
            R.id.search
    })
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.add:
                insert(db);
                break;
            case R.id.del:
                deleted(db);
                break;
            case R.id.edit:
                update(db);
                break;
            case R.id.search:
                query(db);
                break;
        }
    }
}
