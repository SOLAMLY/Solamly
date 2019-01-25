package com.example.solamly.solamly.module.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.solamly.basemodule.util.other.MyLog;
import com.greendao.gen.BehaviorBeanDao;
import com.greendao.gen.DaoMaster;
import com.greendao.gen.FileBeanDao;
import com.greendao.gen.IconBeanDao;
import com.greendao.gen.PersonalBeanDao;
import com.greendao.gen.UserBeanDao;

import org.greenrobot.greendao.database.Database;

import static com.example.solamly.solamly.module.greendao.IMigrationHelper.migrate;


/**
 * @Author SOLAMLY
 * @Date 2018/9/4 17:25
 * @Description: 数据库的创建 升级 降级
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    private static final String TAG = "GreenDao";

    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
        MyLog.e(TAG, "数据库创建" );
    }

    /**
     * 更新数据库
     *
     * 在onUpgrade()方法中可以不调用父类的onUpgrade()方法，
     * 因为做数据库升级的时候已经在MigrationHelper中调用了父类的onUpgrade()方法中的内容
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
//        MigrationHelper.getInstance().migrate(db,UserBeanDao.class);
        MyLog.e(TAG, "数据库升级" + oldVersion + " --> " + newVersion);
        switch (newVersion) {
            /**
             *  Version - 3、4、5
             *
             * 分别添加字段type、count、num
             */
            case 3:
            case 4:
            case 5:
                migrate(db, UserBeanDao.class);
                break;
            /**
             *  Version - 10
             *
             * 添加表PersonalBean
             */
            case 10:
                PersonalBeanDao.createTable(db, true);
                break;
            /**
             *  Version - 12
             *
             * 修改表PersonalBean - 添加字段birthday
             */
            case 12:
                migrate(db, PersonalBeanDao.class);
                break;
            /**
             *  Version - 13
             *
             * 修改表UserBean - 添加字段birthday
             * 修改表PersonalBean - 添加字段 num
             */
            case 13:
                migrate(db, UserBeanDao.class);
                migrate(db, PersonalBeanDao.class);
                break;
            /**
             * Version - 15
             *
             * 添加表 - BehaviorBean
             * 修改表PersonalBean - 添加字段 type
             */
            case 15:
                migrate(db, PersonalBeanDao.class);
                BehaviorBeanDao.createTable(db, true);
                break;
            /**
             * Version - 17
             *
             * 添加表 - IconBean
             * 修改表 BehaviorBeanDao 和  UserBeanDao ---  一对多设置
             */
            case 17:
                IconBeanDao.createTable(db,true);
                migrate(db, BehaviorBeanDao.class);
                migrate(db, UserBeanDao.class);
                break;
            /**
             * Version - 19
             *
             * 修改表 IconBeanDao 和  UserBeanDao ---  一对一设置
             */
            case 19:
                migrate(db, IconBeanDao.class);
                migrate(db, UserBeanDao.class);
                break;
            /**
             * Version - 20
             *
             * 创建表 FileBean
             */
            case 20:
                FileBeanDao.createTable(db, true);
                break;
            /**
             * FileBean表添加主键
             */
            case 21:
                migrate(db, FileBeanDao.class);
                break;
        }

//        IMigrationHelper.migrate(db,PersonalBeanDao.class);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        super.onDowngrade(db, oldVersion, newVersion);
        MyLog.e(TAG, "数据库降级" + oldVersion + " --> " + newVersion);
        migrate(db, UserBeanDao.class);
    }
}
