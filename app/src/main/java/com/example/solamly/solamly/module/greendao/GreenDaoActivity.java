package com.example.solamly.solamly.module.greendao;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;
import com.example.solamly.solamly.Util.MyLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author SOLAMLY
 * @Date 2018/9/4 9:23
 * @Description:
 */

public class GreenDaoActivity extends BaseActivity {
    @BindView(R.id.edit_text)
    EditText editText;

    @Override
    protected int setLayout() {
        return R.layout.activity_greendao_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({
            R.id.add,
            R.id.del,
            R.id.query,
            R.id.query_all,
            R.id.update,
            R.id.delete,
            R.id.one_more,
            R.id.one_one
    })
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                GreenDaoManager.insertOrReplace(getUserBean());
                GreenDaoManager.insertOrReplace(getUserBean());
                GreenDaoManager.insertOrReplace(getBehaviorBean());
                Log.e("TAG", "插入成功");
                break;
            case R.id.del:
                count--;
                GreenDaoManager.deleteAll();
                Log.e("TAG", "删除成功");
                break;
            case R.id.delete:
                count--;
                GreenDaoManager.deletedByName(editText.getText().toString());
                Log.e("TAG", "删除成功");
                break;
            case R.id.query:
//                GreenDaoManager.query(editText.getText().toString());
//                Log.e("TAG", "查询" + GreenDaoManager.query(editText.getText().toString()));
                GreenDaoManager.queryAllByBean(new UserBean());
                GreenDaoManager.queryAllByBean(new PersonalBean());
                GreenDaoManager.queryAllByBean(new BehaviorBean());
                GreenDaoManager.queryAllByBean(new BehaviorBean());
                break;
            case R.id.query_all:
                GreenDaoManager.queryAll();
                break;
            case R.id.update:
                count--;
                GreenDaoManager.update(getUserBean());
                Log.e("TAG", "更新成功");
                break;
            case R.id.one_more:
                setOnToMore();
                break;
            case R.id.one_one:
                setOneToOne();
                break;
        }
    }

    private int count = 0;

    public UserBean getUserBean() {
        UserBean userBean = new UserBean();
        userBean.setId(0);
        userBean.setName(editText.getText().toString());
        userBean.setAge("Age" + 1101010);
        userBean.setSex("Sex" + count);
        count++;
        return userBean;
    }

    public PersonalBean getProfessionBean() {
        PersonalBean personalBean = new PersonalBean();
        personalBean.setId(count);
        personalBean.setName(editText.getText().toString());
        personalBean.setAge(count);
        count++;
        return personalBean;
    }

    public BehaviorBean getBehaviorBean() {
        BehaviorBean behaviorBean = new BehaviorBean();
        behaviorBean.setBehavior(editText.getText().toString());
        behaviorBean.setType("哈哈哈");
        behaviorBean.setNum(count + 5);
        count++;
        return behaviorBean;
    }

    /**
     * 一对多
     */
    private void setOnToMore() {
        UserBean userBean = new UserBean();
        userBean.setId(++count);
        userBean.setName("七戒律");

        long flag = GreenDaoManager.getDaoSession().getUserBeanDao().insert(userBean);
        List<BehaviorBean> behaviorBeens = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BehaviorBean behaviorBean = new BehaviorBean();
            behaviorBean.setNum(i);
            behaviorBean.setType("我是" + i);
            behaviorBean.setBehaviorId(flag);
            behaviorBeens.add(behaviorBean);
        }
        GreenDaoManager.getDaoSession().getBehaviorBeanDao().insertInTx(behaviorBeens);
        MyLog.e("TAG", "多对多添加成功");

    }

    private     IconBean iconBean;
    private   UserBean userBean;
    private void setOneToOne() {
         userBean = new UserBean();
        userBean.setId(count);
        userBean.setName("骷髅");

         iconBean = new IconBean();
        iconBean.setId(count);
        iconBean.setIcon("ICON" + count);

        long flag = GreenDaoManager.getDaoSession().getIconBeanDao().insert(iconBean);
        userBean.setIconId(flag);
        GreenDaoManager.getDaoSession().getUserBeanDao().insert(userBean);
        count++;
        MyLog.e("TAG", "一对多添加成功");
    }
}
