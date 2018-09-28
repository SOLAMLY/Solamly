package com.example.solamly.solamly.module.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.greendao.gen.UserBeanDao;

import java.util.List;

/**
 * @Author SOLAMLY
 * @Date 2018/9/3 17:56
 * @Description:
 */
public class GreenDaoManager {

    private static final String DB_NAME = "data.db";

    private static DaoSession daoSession;
    //    private static DaoMaster.DevOpenHelper helper;
    private static MyOpenHelper myOpenHelper;
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;

    public static void setupDatabase(Context context) {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

//        helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        myOpenHelper = new MyOpenHelper(context, DB_NAME);
        db = myOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    /**
     * 增
     * <p>
     * insert： 会进行去重，保存第一次的数据，也就是不会进行更新。
     * insertOrReplace：  会去重，保存最新的数据，也就是会进行更新
     * save：  不会去重，保存所有数据
     */
    public static void insertOrReplace(Object object) {
        if (object instanceof UserBean) {
            daoSession.getUserBeanDao().insertOrReplace((UserBean) object);
        } else if (object instanceof PersonalBean) {
            daoSession.getPersonalBeanDao().insertOrReplace((PersonalBean) object);
        } else if (object instanceof BehaviorBean) {
            daoSession.getBehaviorBeanDao().insertOrReplace((BehaviorBean) object);
        }
    }

    /**
     * 改
     *
     * @param userBean
     */
    public static void update(UserBean userBean) {
        daoSession.getUserBeanDao().update(userBean);
    }

    /**
     * 查
     *
     * @return
     */
    public static void queryAll() {
        List<PersonalBean> personalBeen = daoSession.getPersonalBeanDao().loadAll();
        List<UserBean> userBeen = daoSession.getUserBeanDao().loadAll();
        List<BehaviorBean> behaviorBeen =  daoSession.getBehaviorBeanDao().loadAll();
        List<IconBean> iconBeen = daoSession.getIconBeanDao().loadAll();
        for (PersonalBean bean : personalBeen) {
            Log.e("TAG", "查询" + bean);
        }
        for (UserBean bean : userBeen) {
            Log.e("TAG", "查询" + bean);
        }
        for (BehaviorBean bean : behaviorBeen) {
            Log.e("TAG", "查询" + bean);
        }
        for (IconBean bean : iconBeen) {
            Log.e("TAG", "查询" + bean);
        }
    }

    /**
     * 查
     *
     * @return
     */
    public static Object queryAllByBean(Object object) {
        LogQueryAll(object);
        if (object instanceof PersonalBean) {
            return daoSession.getPersonalBeanDao().loadAll();
        } else if (object instanceof UserBean) {
            return daoSession.getUserBeanDao().loadAll();
        } else if (object instanceof BehaviorBean) {
            return daoSession.getBehaviorBeanDao().loadAll();
        } else if (object instanceof IconBean) {
            return daoSession.getIconBeanDao().loadAll();
        }
        return null;
    }

    public static void LogQueryAll(Object object) {

        if (object instanceof PersonalBean) {
            for (PersonalBean bean : (List<PersonalBean>) object) {
                Log.e("TAG", "查询" + bean);
            }
        } else if (object instanceof UserBean) {
            for (UserBean bean : (List<UserBean>) object) {
                Log.e("TAG", "查询" + bean);
            }
        } else if (object instanceof BehaviorBean) {
            for (BehaviorBean bean : (List<BehaviorBean>) object) {
                Log.e("TAG", "查询" + bean);
            }
        } else if (object instanceof IconBean) {
            for (IconBean bean : (List<IconBean>) object) {
                Log.e("TAG", "查询" + bean);
            }
        }
    }

    public static List<UserBean> query(String name) {
        return daoSession.getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Name.eq(name)).list();
    }

    /**
     * 删
     */
    public static void deleteAll() {
        daoSession.getUserBeanDao().deleteAll();
        daoSession.getBehaviorBeanDao().deleteAll();
        daoSession.getPersonalBeanDao().deleteAll();
        daoSession.getIconBeanDao().deleteAll();
    }

    public void deletedAllByBean(Object object) {
        if (object instanceof PersonalBean) {
            daoSession.getPersonalBeanDao().deleteAll();
        } else if (object instanceof UserBean) {
            daoSession.getUserBeanDao().deleteAll();
        } else if (object instanceof BehaviorBean) {
            daoSession.getBehaviorBeanDao().deleteAll();
        }
    }

    public static void deletedByName(String name) {
        daoSession.getUserBeanDao()
                .queryBuilder()
                .where(UserBeanDao.Properties.Name.eq(name))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
