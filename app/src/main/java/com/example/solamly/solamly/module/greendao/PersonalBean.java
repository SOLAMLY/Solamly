package com.example.solamly.solamly.module.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Author SOLAMLY
 * @Date 2018/9/5 13:40
 * @Description:
 */
@Entity
public class PersonalBean {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String sex;
    private long age;
    private String birthday;
    private int num;
    private String type;


  
    @Generated(hash = 1021030346)
    public PersonalBean(long id, String name, String sex, long age, String birthday,
            int num, String type) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.num = num;
        this.type = type;
    }
    @Generated(hash = 1035445605)
    public PersonalBean() {
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
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public long getAge() {
        return this.age;
    }
    public void setAge(long age) {
        this.age = age;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PersonalBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", num=" + num +
                ", type='" + type + '\'' +
                '}';
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
