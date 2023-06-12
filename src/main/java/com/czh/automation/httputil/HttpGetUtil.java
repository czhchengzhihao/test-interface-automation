package com.czh.automation.httputil;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.testng.Reporter;

import java.net.URISyntaxException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @Author ChengZhiHao
 * @Description //TODO GET
 * @Date 15:09 2021/12/1
 * @Param
 * @return
 **/
@Slf4j
public class HttpGetUtil {

    /**
     * 加载application.properties
     */
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO get请求,参数拼接在请求地址中 String
     * @Date 15:04 2021/12/1
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpGet(String testUrl, String interfaceAddress, String data) {
        URIBuilder build;
        try {
            build = new URIBuilder(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
            JSONObject parameter = JSONObject.parseObject(data);
            if (parameter != null) {
                Set<String> keys = parameter.keySet();
                for (String key : keys) {
                    String value = String.valueOf(parameter.get(key));
                    //在请求地址上拼接参数
                    build.setParameter(key, value);
                }
            } else {
                build = new URIBuilder(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
            }
            log.info("接口地址：" + build.build());
            Reporter.log("接口地址：" + build.build(), true);
            HttpGet get = new HttpGet(build.build());
            return getGetUtil(get);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.info("URL语法异常");
            Reporter.log("URL语法异常", true);
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO get请求,参数拼接在请求地址中 JSONObject
     * @Date 18:44 2021/12/29
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpGet(String testUrl, String interfaceAddress, JSONObject data, String requestHeader) {
        URIBuilder build;
        try {
            build = new URIBuilder(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
            if (data != null) {
                Set<String> keys = data.keySet();
                for (String key : keys) {
                    String value = String.valueOf(data.get(key));
                    //在请求地址上拼接参数
                    build.setParameter(key, value);
                }
            } else {
                build = new URIBuilder(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
            }
            log.info("接口地址：" + build.build());
            Reporter.log("接口地址：" + build.build(), true);
            HttpGet get = new HttpGet(build.build());
            return getGetUtil(get);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.info("URL语法异常");
            Reporter.log("URL语法异常", true);
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO get请求 无需入参
     * @Date 15:08 2021/12/1
     * @Param [testUrl, interfaceAddress]
     **/
    public static JSONObject httpGet(String testUrl, String interfaceAddress) {
        HttpGet get = new HttpGet(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        return getGetUtil(get);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO get请求公共方法
     * @Date 17:15 2022/1/15
     * @Param [interfaceAddress, requestHeader, get]
     **/
    private static JSONObject getGetUtil(HttpGet get) {
        HttpSetHeader.getSetHeader(get);
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        return httpClientResponseUtil.requestExecution(get);
    }

}
