package com.example.solamly.solamly.module.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Author SOLAMLY
 * @Date 2018/9/5 16:57
 * @Description:
 */

@Entity
public class IconBean {
    @Id(autoincrement = true)
    private long id;
    private String icon;

    @Generated(hash = 1413954164)
    public IconBean(long id, String icon) {
        this.id = id;
        this.icon = icon;
    }

    @Generated(hash = 1307995362)
    public IconBean() {
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IconBean{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                '}';
    }
}
