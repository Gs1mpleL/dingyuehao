package com.wanfeng.dingyuehao.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wanfeng.dingyuehao.domain.VO.MsgReq;
import com.wanfeng.dingyuehao.domain.VO.MsgResp;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MsgUtils {

    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 返回消息类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_Image = "image";

    /**
     * 返回消息类型：语音
     */
    public static final String RESP_MESSAGE_TYPE_Voice = "voice";

    /**
     * 返回消息类型：视频
     */
    public static final String RESP_MESSAGE_TYPE_Video = "video";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 事件类型：VIEW(自定义菜单URl视图)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";

    /**
     * 事件类型：LOCATION(上报地理位置事件)
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**
     * 事件类型：LOCATION(上报地理位置事件)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";

    /**
     *  解析微信发来的请求（XML）
     */
    public static Map<String, String> parseXml(HttpServletRequest request) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = null;
        InputStream inputStream = null;
        try {
            // 从request中取得输入流
            inputStream = request.getInputStream();
            document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            elementList.stream().forEach(element -> {
                map.put(element.getName(), element.getStringValue());
            });
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 释放资源
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    /**
     * 文本消息对象转换成xml
     */
    public static String textMessageToXml(MsgResp textMessage) {
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        //XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }


    /**
     * 组装返回语句
     */
    public static String buildReply(MsgReq msgReq,String text){
        MsgResp msgResp = new MsgResp();
        msgResp.setContent(text);
        msgResp.setMsgType(MsgUtils.RESP_MESSAGE_TYPE_TEXT);
        msgResp.setFromUserName(msgReq.getToUserName());
        msgResp.setToUserName(msgReq.getFromUserName());
        msgResp.setCreateTime(msgReq.getCreateTime());
        return MsgUtils.textMessageToXml(msgResp);
    }
}