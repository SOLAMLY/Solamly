package com.example.solamly.solamly.module.greendao;

import com.greendao.gen.BehaviorBeanDao;
import com.greendao.gen.DaoSession;
import com.greendao.gen.IconBeanDao;
import com.greendao.gen.UserBeanDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

/**
 * @Author SOLAMLY
 * @Date 2018/9/5 10:21
 * @Description:
 */

@Entity
public class UserBean {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String age;
    private String sex;
    private String type;
    private int num;
    private long count;
    private String birthday;

    private long iconId;
    @ToOne(joinProperty = "iconId")
    private IconBean iconBean;

    @ToMany(referencedJoinProperty = "behaviorId")
    private List<BehaviorBean> behaviorBeen;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 83707551)
    private transient UserBeanDao myDao;
    @Generated(hash = 1059248016)
    private transient Long iconBean__resolvedKey;

    @Generated(hash = 729722625)
    public UserBean(long id, String name, String age, String sex, String type, int num, long count,
            String birthday, long iconId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.type = type;
        this.num = num;
        this.count = count;
        this.birthday = birthday;
        this.iconId = iconId;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public long getCount() {
        return this.count;
    }
    public void setCount(long count) {
        this.count = count;
    }


    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", type='" + type + '\'' +
                ", num=" + num +
                ", count=" + count +
                ", birthday='" + birthday + '\'' +
                ", iconId=" + iconId +
                ", iconBean=" + iconBean +
                ", behaviorBeen=" + behaviorBeen +
                '}';
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1129293257)
    public List<BehaviorBean> getBehaviorBeen() {
        if (behaviorBeen == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BehaviorBeanDao targetDao = daoSession.getBehaviorBeanDao();
            List<BehaviorBean> behaviorBeenNew = targetDao
                    ._queryUserBean_BehaviorBeen(id);
            synchronized (this) {
                if (behaviorBeen == null) {
                    behaviorBeen = behaviorBeenNew;
                }
            }
        }
        return behaviorBeen;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 994806015)
    public synchronized void resetBehaviorBeen() {
        behaviorBeen = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1491512534)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserBeanDao() : null;
    }
    public long getIconId() {
        return this.iconId;
    }
    public void setIconId(long iconId) {
        this.iconId = iconId;
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1557183171)
    public IconBean getIconBean() {
        long __key = this.iconId;
        if (iconBean__resolvedKey == null || !iconBean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IconBeanDao targetDao = daoSession.getIconBeanDao();
            IconBean iconBeanNew = targetDao.load(__key);
            synchronized (this) {
                iconBean = iconBeanNew;
                iconBean__resolvedKey = __key;
            }
        }
        return iconBean;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 819156947)
    public void setIconBean(@NotNull IconBean iconBean) {
        if (iconBean == null) {
            throw new DaoException(
                    "To-one property 'iconId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.iconBean = iconBean;
            iconId = iconBean.getId();
            iconBean__resolvedKey = iconId;
        }
    }
}
