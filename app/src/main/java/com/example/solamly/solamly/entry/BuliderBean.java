package com.example.solamly.solamly.entry;

/**
 * @Author SOLAMLY
 * @Date 2018/10/10 16:19
 * @Description:
 */

public class BuliderBean {
    private String name;
    private int sex;
    private boolean isOk;
    private String value;

    private BuliderBean(Builder builder) {
        name = builder.name;
        sex = builder.sex;
        isOk = builder.isOk;
        value = builder.value;
    }


    public static final class Builder {
        private String name;
        private int sex;
        private boolean isOk;
        private String value;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder sex(int val) {
            sex = val;
            return this;
        }

        public Builder isOk(boolean val) {
            isOk = val;
            return this;
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public BuliderBean build() {
            return new BuliderBean(this);
        }
    }
}
