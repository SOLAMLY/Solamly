package com.example.solamly.solamly.module.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author SOLAMLY
 * @Date 2018/9/5 14:46
 * @Description:
 */

@Entity
public class BehaviorBean {
    private String behavior;
    private String type;
    private int num;

    private long behaviorId;

    @Generated(hash = 268130804)
    public BehaviorBean(String behavior, String type, int num, long behaviorId) {
        this.behavior = behavior;
        this.type = type;
        this.num = num;
        this.behaviorId = behaviorId;
    }
    @Generated(hash = 1166184184)
    public BehaviorBean() {
    }
    public String getBehavior() {
        return this.behavior;
    }
    public void setBehavior(String behavior) {
        this.behavior = behavior;
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

    @Override
    public String toString() {
        return "BehaviorBean{" +
                "behavior='" + behavior + '\'' +
                ", type='" + type + '\'' +
                ", num=" + num +
                ", behaviorId=" + behaviorId +
                '}';
    }

    public long getBehaviorId() {
        return this.behaviorId;
    }
    public void setBehaviorId(long behaviorId) {
        this.behaviorId = behaviorId;
    }
}
