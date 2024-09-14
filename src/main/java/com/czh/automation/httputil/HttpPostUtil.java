package com.czh.automation.httputil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.czh.automation.constant.AllConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Reporter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * @Author ChengZhiHao
 * @Description //TODO POST
 * @Date 15:10 2021/12/1
 * @Param
 * @return
 **/
@Slf4j
public class HttpPostUtil {

    /**
     * 加载application.properties
     */
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO post请求,入参String格式转为JSON
     * @Date 14:52 2021/12/1
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPost(String testUrl, String interfaceAddress, String data) {
        //创建请求方法的实例
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        //入参转json格式
        JSONObject parameter = JSONObject.parseObject(data);
        //添加请求参数
        post.setEntity(new StringEntity(parameter.toString(), AllConstant.UTF_8));
        return getPostUtil(post);

    }


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 入参JSON格式 无需转换
     * @Date 15:01 2021/12/1
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPost(String testUrl, String interfaceAddress, JSONObject data) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        post.setEntity(new StringEntity(data.toString(), AllConstant.UTF_8));
        return getPostUtil(post);
    }


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO post请求,入参集合格式
     * @Date 15:02 2021/12/1
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPostArray(String testUrl, String interfaceAddress, String data) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        JSONArray parameter = JSONObject.parseArray(data);
        post.setEntity(new StringEntity(parameter.toString(), AllConstant.UTF_8));
        return getPostUtil(post);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO post请求,参数在接口地址上
     * @Date 15:03 2021/12/1
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPostUrl(String testUrl, String interfaceAddress, String data) {
        JSONObject parameter = JSONObject.parseObject(data);
        URIBuilder build;
        try {
            build = new URIBuilder(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
            log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
            Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
            if (parameter != null) {
                Set<String> keys = parameter.keySet();
                for (String key : keys) {
                    String value = String.valueOf(parameter.get(key));
                    //在请求地址上拼接参数
                    build.setParameter(key, value);
                }
            }
            HttpPost post = new HttpPost(build.build());
            return getPostUtil(post);
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
     * @Description //TODO post请求,无需传参
     * @Date 15:01 2021/12/1
     * @Param [testUrl, interfaceAddress]
     **/
    public static JSONObject httpPost(String testUrl, String interfaceAddress) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        return getPostUtil(post);
    }


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO post请求,form-data格式
     * @Date 15:03 2021/12/1
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPostFormData(String testUrl, String interfaceAddress, String data) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        JSONObject parameter = JSONObject.parseObject(data);
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        //通过迭代器取出所有的key，再获取每一个键对应的值
        Set<String> keySet = parameter.keySet();
        for (String key : keySet) {
            String value = (String) parameter.get(key);
            params.add(new BasicNameValuePair(key, value));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            log.info("编码异常");
            Reporter.log("编码异常", true);
        }
        return getPostUtil(post);

    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO POST请求公共方法
     * @Date 17:57 2022/1/15
     * @Param [interfaceAddress, requestHeader, post]
     **/
    private static JSONObject getPostUtil(HttpPost post) {
        HttpSetHeader.postSetHeader(post);
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        return httpClientResponseUtil.requestExecution(post);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 附件上传
     * @Date 14:07 2022/1/10
     * @Param [testUrl, interfaceAddress, data]
     **/
    public static JSONObject httpPostFileUpload(String testUrl, String interfaceAddress, String fileKey, File filePath) {
        HttpPost post = new HttpPost(BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        log.info("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress));
        Reporter.log("接口地址：" + BUNDLE.getString(testUrl) + BUNDLE.getString(interfaceAddress), true);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        //设置参数
        builder.addPart(fileKey, new FileBody(filePath, ContentType.IMAGE_JPEG));
        post.setEntity(builder.build());
        HttpSetHeader.postUploadFileSetHeader(post);
        HttpResponseUtil httpClientResponseUtil = new HttpResponseUtil();
        return httpClientResponseUtil.requestExecution(post);
    }

}
