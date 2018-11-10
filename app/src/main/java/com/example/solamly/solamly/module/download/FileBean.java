package com.example.solamly.solamly.module.download;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Author SOLAMLY
 * @Date 2018/10/31 15:24
 * @Description:
 */

@Entity
public class FileBean {
    public static final int DOWN_THREAD_FINISH = 999;            //下载线程结束/中断
    public static final int UPDATE_PROGRESS = 997;              //更新进度条
    public static final int INIT_THREAD_FINISH = 998;           //初始化线程结束

    @Id
    private long id;

    private String name;        //文件名
    private String url;         //文件Url
    private int length;         //文件大小
    private String dirPath;     //文件目录  文件被手动删除时需要重新下载
    private int startNode;      // 下载过的节点

    @Generated(hash = 962482179)
    public FileBean(long id, String name, String url, int length, String dirPath,
                    int startNode) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.length = length;
        this.dirPath = dirPath;
        this.startNode = startNode;
    }

    @Generated(hash = 1910776192)
    public FileBean() {
    }

    private FileBean(Builder builder) {
        setName(builder.name);
        setUrl(builder.url);
        setLength(builder.length);
        setDirPath(builder.dirPath);
        setStartNode(builder.startNode);
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDirPath() {
        return this.dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public int getStartNode() {
        return this.startNode;
    }

    public void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    public static final class Builder {
        private String name;
        private String url;
        private int length;
        private String dirPath;
        private int startNode;

        public Builder() {
        }


        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder length(int val) {
            length = val;
            return this;
        }

        public Builder dirPath(String val) {
            dirPath = val;
            return this;
        }

        public Builder startNode(int val) {
            startNode = val;
            return this;
        }

        public FileBean build() {
            return new FileBean(this);
        }
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", length=" + length +
                ", dirPath='" + dirPath + '\'' +
                ", startNode=" + startNode +
                '}';
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
