package com.czh.automation.httputil;

import com.alibaba.fastjson.JSONObject;
import com.czh.automation.constant.AllConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.testng.Reporter;

import java.net.URISyntaxException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @Author ChengZhiHao
 * @Description //TODO PUT
 * @Date 15:09 2021/12/1
 * @Param
 * @return
 **/
@Slf4j
public class HttpPutUtil {

    /**
     * 加载application.properties
     */
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO get请求,参数拼接在请求地址中 入参Sting
     * @Date 15:09 2021/12/1
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPut(String testUrl, String interfaceAddress, String data, String requestHeader) {
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
            HttpPut put = new HttpPut(build.build());
            return getPutUtil(put);
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
     * @Description //TODO  get请求,参数拼接在请求地址中 入参JSON
     * @Date 13:52 2021/12/3
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPut(String testUrl, String interfaceAddress, JSONObject parameter) {
        URIBuilder build;

        try {
            build = new URIBuilder(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
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
            HttpPut put = new HttpPut(build.build());
            return getPutUtil(put);
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
     * @Description //TODO JSON格式
     * @Date 14:18 2021/12/15
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPutJson(String testUrl, String interfaceAddress, JSONObject data) {
        //创建请求方法的实例
        HttpPut put = new HttpPut(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        //添加请求参数
        put.setEntity(new StringEntity(data.toString(), AllConstant.UTF_8));
        return getPutUtil(put);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO PUT请求公共方法
     * @Date 17:57 2022/1/15
     * @Param [interfaceAddress, requestHeader, put]
     **/
    private static JSONObject getPutUtil(HttpPut put) {
        HttpSetHeader.putSetHeader(put);
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        return httpClientResponseUtil.requestExecution(put);
    }
}
