package com.czh.automation.httputil;

import com.alibaba.fastjson.JSONObject;
import com.czh.automation.constant.AllConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.StringEntity;
import org.testng.Reporter;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * @Author ChengZhiHao
 * @Description //TODO DELETE
 * @Date 15:09 2021/12/1
 * @Param
 * @return
 **/
@Slf4j
public class HttpDeleteUtil {
    /**
     * 加载application.properties
     */
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 入参String格式
     * @Date 14:18 2021/12/15
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpDelete(String testUrl, String interfaceAddress, String data) {
        //创建请求方法的实例
        HttpDeleteWithBody delete = new HttpDeleteWithBody(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        //入参转json格式
        JSONObject parameter = JSONObject.parseObject(data);
        //添加请求参数
        delete.setEntity(new StringEntity(parameter.toString(), AllConstant.UTF_8));
        //设置请求头
        HttpSetHeader.deleteSetHeader(delete);
        return getDeleteUtil(delete);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 入参JSON格式
     * @Date 14:18 2021/12/15
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpDelete(String testUrl, String interfaceAddress, JSONObject data) {
        //创建请求方法的实例
        HttpDeleteWithBody delete = new HttpDeleteWithBody(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        //添加请求参数
        delete.setEntity(new StringEntity(data.toString(), AllConstant.UTF_8));
        return getDeleteUtil(delete);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO DELETE请求公共方法
     * @Date 18:00 2022/1/15
     * @Param [interfaceAddress, requestHeader, delete]
     **/
    private static JSONObject getDeleteUtil(HttpDeleteWithBody delete) {
        HttpSetHeader.deleteSetHeader(delete);
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        return httpClientResponseUtil.requestExecution(delete);
    }
}
