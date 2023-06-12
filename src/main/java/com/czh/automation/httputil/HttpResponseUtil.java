package com.czh.automation.httputil;

import com.alibaba.fastjson.JSONObject;
import com.czh.automation.connection.HttpClientConnection;
import com.czh.automation.constant.AllConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;


/**
 * @Author ChengZhiHao
 * @Description //TODO 请求执行，响应结果
 * @Date 15:10 2021/12/1
 * @Param
 * @return
 **/
@Slf4j
public class HttpResponseUtil {
    /**
     * 日志打印
     */


    private CloseableHttpResponse closeableHttpResponse;
    private CloseableHttpClient client;


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 请求执行公共类/POST
     * @Date 14:49 2021/12/1
     * @Param [post]
     **/
    public JSONObject requestExecution(HttpPost post) {
        //从线程池创建HttpClient对象
        client = HttpClientConnection.getHttpClientConnection();
        try {
            //执行请求
            closeableHttpResponse = client.execute(post);
            //获取接口响应状态吗
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            log.info("接口响应状态码：" + code);
            Reporter.log("接口响应状态码：" + code);
            //获取响应结果
            return responseResults(closeableHttpResponse, code);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("接口请求超时");
            Reporter.log("接口请求超时", true);
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("IO异常");
                    Reporter.log("IO异常", true);
                }
            }
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 请求执行公共类/GET
     * @Date 14:48 2021/12/1
     * @Param [get]
     **/
    public JSONObject requestExecution(HttpGet get) {
        client = HttpClientConnection.getHttpClientConnection();
        try {
            closeableHttpResponse = client.execute(get);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            log.info("接口响应状态码：" + code);
            Reporter.log("接口响应状态码：" + code);
            return responseResults(closeableHttpResponse, code);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("IO异常");
            Reporter.log("IO异常", true);
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("IO异常");
                    Reporter.log("IO异常", true);
                }
            }
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 请求执行公共类/PUT
     * @Date 14:48 2021/12/1
     * @Param [put]
     **/
    public JSONObject requestExecution(HttpPut put) {
        client = HttpClientConnection.getHttpClientConnection();
        try {
            closeableHttpResponse = client.execute(put);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            log.info("接口响应状态码：" + code);
            Reporter.log("接口响应状态码：" + code);
            return responseResults(closeableHttpResponse, code);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("IO异常");
            Reporter.log("IO异常", true);
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("IO异常");
                    Reporter.log("IO异常", true);
                }
            }
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 请求执行公共类/DELETE
     * @Date 14:48 2021/12/1
     * @Param [put]
     **/
    public JSONObject requestExecution(HttpDeleteWithBody delete) {
        client = HttpClientConnection.getHttpClientConnection();
        try {
            closeableHttpResponse = client.execute(delete);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();
            log.info("接口响应状态码：" + code);
            Reporter.log("接口响应状态码：" + code);
            return responseResults(closeableHttpResponse, code);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("IO异常");
            Reporter.log("IO异常", true);
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("IO异常");
                    Reporter.log("IO异常", true);
                }
            }
        }
        return null;
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Author ChengZhiHao
     * @Description //TODO 响应结果返回公共类
     * @Date 14:49 2021/12/1
     * @Param [closeableHttpResponse, code]
     **/
    public JSONObject responseResults(CloseableHttpResponse closeableHttpResponse, int code) {
        String result = null;
        try {
            //获取响应结果
            result = EntityUtils.toString(closeableHttpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //结果转Json格式
        JSONObject resultJsonObject = JSONObject.parseObject(result);
        log.info("响应结果：" + resultJsonObject);
        Reporter.log("响应结果：" + resultJsonObject, true);
        Assert.assertEquals(code, AllConstant.HTTP_CODE_200, "接口响应状态码：" + code + ",接口请求失败");
        return resultJsonObject;
    }
}
