package example.nativenews;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：国富小哥
 * 日期：2017/6/25
 * Created by Administrator
 */

public class ServiceUtil {
    /**
     * 解析服务器返回的xml信息，获取所有新闻数据实体
     * */
    public static List<NewsInfo> getNewsInfo(InputStream in){
        try {
            List<NewsInfo> newsInfoList=null;
            NewsInfo newsInfo=null;
            //获取xmlPullParser对象
            XmlPullParser xmlPullParser= Xml.newPullParser();
            xmlPullParser.setInput(in,"utf-8");
            //获取指针
            int type=xmlPullParser.getEventType();
            //type不是文档结束
            while (type!=XmlPullParser.END_DOCUMENT){
                switch (type){
                    //开始标签
                    case XmlPullParser.START_TAG:
                        //拿到标签名并判断
                        if ("news".equals(xmlPullParser.getName())){
                            newsInfoList=new ArrayList<>();
                        }else if ("newsInfo".equals(xmlPullParser.getName())){
                            newsInfo=new NewsInfo();
                        }else if ("icon".equals(xmlPullParser.getName())){
                            //获取解析器当前指向元素的下一个文本节点的值
                            String iconPath=xmlPullParser.nextText();
                            newsInfo.setIconPath(iconPath);
                        }else if ("title".equals(xmlPullParser.getName())){
                            String title=xmlPullParser.nextText();
                            newsInfo.setTitle(title);
                        }else if ("content".equals(xmlPullParser.getName())){
                            String description=xmlPullParser.nextText();
                            newsInfo.setDescription(description);
                        }else if ("type".equals(xmlPullParser.getName())){
                            String newsType=xmlPullParser.getName();
                            newsInfo.setType(Integer.parseInt(newsType));
                        }else if ("comment".equals(xmlPullParser.getName())){
                            String comment=xmlPullParser.nextText();
                            newsInfo.setComment(Long.parseLong(comment));
                        }
                        break;
                    //标签结束
                    case XmlPullParser.END_TAG:
                        if ("newsInfo".equals(xmlPullParser.getName())){
                            newsInfoList.add(newsInfo);
                            newsInfo=null;
                        }
                        break;
                }
                type=xmlPullParser.next();
            }
            return newsInfoList;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
