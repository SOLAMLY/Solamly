package com.example.solamly.solamly.module.network.rxjava_retrofit;

import com.example.solamly.solamly.module.network.network.Retrofit.CommentBean;

import java.util.List;

/**
 * @Author SOLAMLY
 * @Date 2018/9/11 16:12
 * @Description:
 */

public class ComBean {
    /**
     * total : 42
     * array : [{"articlecommenttime":"2018-09-08 11:39:50","address":"","childtotal":0,"timepoor":162,"icon":"","userid":"39e9b4a178b34db98cd6e691300241e6","arraychild":[],"informationcommentid":"c2_974642b1e078446c84ef74eae04cf22c","content":"专业BBT手法交流群，加我微信15937756766速度每天学习一个穴位和BBT仪器养生操作方法！","childaddress":"","username":"匿名用户"},{"articlecommenttime":"2018-09-08 11:37:49","address":"","childtotal":0,"timepoor":164,"icon":"","userid":"39e9b4a178b34db98cd6e691300241e6","arraychild":[],"informationcommentid":"c1_33e8fb5e6b6247c1b3f715ffed89013a","content":"专业BBT手法交流群，加我微信15937756766速度每天学习一个穴位和BBT仪器养生操作方法！","childaddress":"","username":"匿名用户"},{"articlecommenttime":"2018-09-08 11:04:07","address":"广州","childtotal":0,"timepoor":198,"icon":"/BeautifyBreast/file/images/9d21dd81f57c4e0eb262101819b6f8a0.jpg","userid":"da179d4313584f7aac4195ec4864d54b","arraychild":[],"informationcommentid":"c2_c54d8fa98a7647d5b21404b03d20f8c3","content":"预防大于治疗","childaddress":"广州","username":"刘银娣"},{"articlecommenttime":"2018-09-08 09:27:08","address":"湖南省，湘潭县花石排头乡","childtotal":0,"timepoor":295,"icon":"","userid":"274c0601d48c416192c7ba33b14b8835","arraychild":[],"informationcommentid":"c2_ce03c15cf22c46b58a17fa5bf874089b","content":"每天坚持做胸部有氧运动","childaddress":"湖南省，湘潭县花石排头乡","username":"匿名用户陈必浪"},{"articlecommenttime":"2018-09-08 09:19:14","address":"重庆合川区较长路安康养生","childtotal":0,"timepoor":303,"icon":"/BeautifyBreast/file/images/17b6e2ff5bad4ef581e59eb8e5448220.jpg","userid":"357a0ed24d4344dc8cf1c927fea58db2","arraychild":[],"informationcommentid":"c2_aa34ea0c331748b69255e02cbad9128a","content":"呕气伤肝划不来，开心快乐过好每一天！安康养生与您分享，健康咨询热线：15310394949","childaddress":"重庆合川区较长路安康养生","username":"安康养生善行天下"},{"articlecommenttime":"2018-09-08 09:14:57","address":"广东省彿山市","childtotal":0,"timepoor":307,"icon":"/BeautifyBreast/file/images/90ee0ef87b994479a6bf88761279fab6.jpg","userid":"f3081d2e5a4e077346f9a0e737cbc7c6","arraychild":[],"informationcommentid":"c2_a3cb053404014a1891af1a5c08f19959","content":"面对不易，哭了，擦干眼泪，微笑着面对生活！","childaddress":"广东省彿山市","username":"冼琳琳"},{"articlecommenttime":"2018-09-08 09:10:47","address":"浙江湖州吴兴区","childtotal":0,"timepoor":311,"icon":"/BeautifyBreast/file/images/30630a90a4694c9098d1a41fd812be7b.jpg","userid":"b5785d8828f142ea8e329929c328312f","arraychild":[],"informationcommentid":"c2_ba9d791e209a4569b73123aa979485c4","content":"乳盲带来的乳癌，我们的任务很艰巨\n对于大学生来讲，如果家里没有一个懂健康的人，真的很可惜。除非自己知识面广","childaddress":"浙江湖州吴兴区","username":"蔡爱馨"},{"articlecommenttime":"2018-09-08 08:59:27","address":"广东茂名","childtotal":0,"timepoor":323,"icon":"/BeautifyBreast/file/images/E2F929595B2F47CCB4ACE249FEA39BE7.png","userid":"be170770c75de978da48759673c2883f","arraychild":[],"informationcommentid":"c2_3017960ed4b84071b216999998d3f9d2","content":"女性要控制情绪和心情","childaddress":"广东茂名","username":"潘光杰"},{"articlecommenttime":"2018-09-08 08:37:59","address":"","childtotal":0,"timepoor":344,"icon":"/BeautifyBreast/file/images/934cb2f1c4b24817b218812389e42784.jpg","userid":"89f8a54c1e44428297c1530171084048","arraychild":[],"informationcommentid":"c2_6d5fd600c73c453eb5787c40dc90fcff","content":"预防大过治疗，好好爱自己","childaddress":"","username":"任桂香"},{"articlecommenttime":"2018-09-08 07:44:32","address":"东升","childtotal":0,"timepoor":398,"icon":"/BeautifyBreast/file/images/f3d0443c1c464cc6b696270e273db9c0.JEPG","userid":"f226ca681ecf21eed28ad01a8b6a4b3c","arraychild":[],"informationcommentid":"c2_9ac1f0dc60804057982f921785609b6a","content":"关爱女性，扫除乳盲","childaddress":"东升","username":"梁焕卿"}]
     */

    private int total;
    private List<CommentBean.DataBean.ArrayBean> array;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CommentBean.DataBean.ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<CommentBean.DataBean.ArrayBean> array) {
        this.array = array;
    }

    public static class ArrayBean {
        /**
         * articlecommenttime : 2018-09-08 11:39:50
         * address :
         * childtotal : 0
         * timepoor : 162
         * icon :
         * userid : 39e9b4a178b34db98cd6e691300241e6
         * arraychild : []
         * informationcommentid : c2_974642b1e078446c84ef74eae04cf22c
         * content : 专业BBT手法交流群，加我微信15937756766速度每天学习一个穴位和BBT仪器养生操作方法！
         * childaddress :
         * username : 匿名用户
         */

        private String articlecommenttime;
        private String address;
        private int childtotal;
        private int timepoor;
        private String icon;
        private String userid;
        private String informationcommentid;
        private String content;
        private String childaddress;
        private String username;
        private List<?> arraychild;

        public String getArticlecommenttime() {
            return articlecommenttime;
        }

        public void setArticlecommenttime(String articlecommenttime) {
            this.articlecommenttime = articlecommenttime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getChildtotal() {
            return childtotal;
        }

        public void setChildtotal(int childtotal) {
            this.childtotal = childtotal;
        }

        public int getTimepoor() {
            return timepoor;
        }

        public void setTimepoor(int timepoor) {
            this.timepoor = timepoor;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getInformationcommentid() {
            return informationcommentid;
        }

        public void setInformationcommentid(String informationcommentid) {
            this.informationcommentid = informationcommentid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getChildaddress() {
            return childaddress;
        }

        public void setChildaddress(String childaddress) {
            this.childaddress = childaddress;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getArraychild() {
            return arraychild;
        }

        public void setArraychild(List<?> arraychild) {
            this.arraychild = arraychild;
        }

        @Override
        public String toString() {
            return "ArrayBean{" +
                    "articlecommenttime='" + articlecommenttime + '\'' +
                    ", address='" + address + '\'' +
                    ", childtotal=" + childtotal +
                    ", timepoor=" + timepoor +
                    ", icon='" + icon + '\'' +
                    ", userid='" + userid + '\'' +
                    ", informationcommentid='" + informationcommentid + '\'' +
                    ", content='" + content + '\'' +
                    ", childaddress='" + childaddress + '\'' +
                    ", username='" + username + '\'' +
                    ", arraychild=" + arraychild +
                    '}';
        }
    }
}
