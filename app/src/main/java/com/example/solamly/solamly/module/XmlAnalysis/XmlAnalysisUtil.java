package com.example.solamly.solamly.module.XmlAnalysis;

import android.util.Log;

import com.example.solamly.solamly.Util.ToastUtil;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @Author SOLAMLY
 * @Date 2018/9/17 11:28
 * @Description:
 */

public class XmlAnalysisUtil {
    private static final String TAG = "XmlAnalysis";

    /**
     * DOM 解析XML
     *
     * @param is
     */
    public static void xmlToDom(InputStream is) {
        Log.e(TAG,"DOM解析");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);                            //获得Document对象
            NodeList nodeList = document.getElementsByTagName("student");       //获得xml的List
            //遍历xml的student标签
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);                //获得student标签
                NodeList child = node.getChildNodes();       //获得student标签里面的子标签
                //遍历student标签里面的子标签
                for (int j = 0; j < child.getLength(); j++) {
                    Node childNode = child.item(j);       //获得student标签里子标签
                    if ("name".equals(childNode.getNodeName())) {
                        Log.i(TAG, "name : " + childNode.getTextContent());     //输出name字段
                        //获取sex字段，由于只有一个属性，所以取0
                        NamedNodeMap nodeMap = childNode.getAttributes();
                        Node n = nodeMap.item(0);
                        Log.i(TAG, "sex  : " + n.getTextContent());             //输出sex字段
                    } else if ("nickName".equals(childNode.getNodeName())) {
                        Log.i(TAG, "nickName : " + childNode.getTextContent());  //输出nickName字段
                    }
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * PULL 解析 XML
     *
     * @param is
     */
    public static void xmlOfPull(InputStream is) {
        Log.e(TAG,"SAX解析");
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();     //创建Pull解析器
            pullParser.setInput(is, "utf-8");                   ///设置编码
            int type = pullParser.getEventType();               //获取文件类型
            StringBuilder stringBuilder = new StringBuilder();
            //文件读取  -- 到结束节点时停止读取
            while (type != XmlPullParser.END_DOCUMENT){
                switch (type){
                    //文档开始事件，可以进行数据初始化处理
                    case XmlPullParser.START_DOCUMENT:
                        Log.e(TAG,"开始读取文档");
                        break;
                    //开始元素事件
                    case XmlPullParser.START_TAG:
                        String name = pullParser.getName();
                        if (name.equals("students")){
                            Log.e(TAG,"读取进度--" + "读取Students大集合" );
                        }else if (name.equals("student")){
                            Log.e(TAG,"读取进度--" + "读取Student集合"  );
                        }else if (name.equals("name")){
                            //若是标签里还有字段，必须先调用这一句，如果先调用nextText，则会返回null
                            String sex = pullParser.getAttributeValue(null,"sex");
                            String age = pullParser.getAttributeValue(null,"age");
                            Log.e(TAG,"读取进度:" + "读取sex : "  + sex);
                            Log.e(TAG,"读取进度:" + "读取age : "  + age);
                            Log.e(TAG,"读取进度--" + "读取name : " + pullParser.nextText());
                        }else if (name.equals("nickName")){
                            Log.e(TAG,"读取进度--" + "读取nickName : " + pullParser.nextText());
                        }

                        break;
                    //结束元素事件
                    case XmlPullParser.END_TAG:
                        String end =  pullParser.getName();
                        if (end.equals("students")){
                            Log.e(TAG,"读取结束--" + "students大集合" );
                        }else if (end.equals("student")){
                            Log.e(TAG,"读取结束--" + "Student集合" );
                        }
                        break;
                }
                type = pullParser.next();       //读取下一个标签
            }
            Log.e(TAG,"文档读取完毕");


        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
