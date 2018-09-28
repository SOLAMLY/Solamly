package com.example.solamly.solamly.module.http_url_client.Retrofit;

import java.util.List;

/**
 * @Author SOLAMLY
 * @Date 2018/9/8 14:42
 * @Description:
 */

public class TestBean {
    /**
     * data : [{"informationtypeid":"82","name":"胸部健康","sort":"2"},{"informationtypeid":"2","name":"美胸丰胸","sort":"3"},{"informationtypeid":"12","name":"妇科健康","sort":"5"},{"informationtypeid":"69","name":"热辣情感","sort":"8"},{"informationtypeid":"9","name":"母婴健康","sort":"9"},{"informationtypeid":"10","name":"热点","sort":"10"},{"informationtypeid":"83","name":"内衣好物","sort":"12"},{"informationtypeid":"14","name":"小视频","sort":"14"},{"informationtypeid":"15","name":"公益","sort":"60"},{"informationtypeid":"0","name":"其他","sort":""}]
     * msg : SUCCESS
     * status : 1
     */

    private String msg;
    private int status;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * informationtypeid : 82
         * name : 胸部健康
         * sort : 2
         */

        private String informationtypeid;
        private String name;
        private String sort;

        public String getInformationtypeid() {
            return informationtypeid;
        }

        public void setInformationtypeid(String informationtypeid) {
            this.informationtypeid = informationtypeid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "informationtypeid='" + informationtypeid + '\'' +
                    ", name='" + name + '\'' +
                    ", sort='" + sort + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
