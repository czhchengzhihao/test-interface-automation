package com.czh.automation.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @PackageName: com.czh.automation.api
 * @ClassName: XBBCase
 * @Description: XBBCase/description:
 * @Author: ChengZhiHao
 * @Date: 2023/9/22 10:02
 * @Version: v1.0
 */
public class XbbCase {
    String token = "b15b90abd6d209b04da17e6ac21e95ae";


 /*   String conditions = "{\n" +
            "    \"formId\":2,\n" +
                "    \"corpid\":\"ding0434d73b09f8fd4e\",\n" +
            "    \"dataId\": \"91082\",\n" +
            "    \"userId\":\"04446648111231604\"\n" +
            "}";*/
 String conditions = "{\n" +
         "  \"dataId\": 91082,\n" +
         "  \"corpid\": \"ding0434d73b09f8fd4e\",\n" +
         "  \"userId\": \"181401120230821054\"\n" +
         "}";

    @Test
    public void test() throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://api.xcrm.xxh.dbappsecurity.com.cn/pro/v2/api/customer/detail");
        post.setHeader("sign", getDataSign(JSONObject.parseObject(conditions), token));
        System.out.println("入参111："+getDataSign(JSONObject.parseObject(conditions), token));
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");
        JSONObject parameter = JSONObject.parseObject(conditions);
        System.out.println("入参" + parameter);
        StringEntity entity = new StringEntity(parameter.toString());
        post.setEntity(entity);
        CloseableHttpResponse response = client.execute(post);
        int code = response.getStatusLine().getStatusCode();
        System.out.println("接口响应" + code);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println("2222222"+result);
        System.out.println("111111"+JSONObject.parseObject(result));

    }

    public static String getDataSign(JSONObject data, String token) {
        return XbbDigestUtil.Encrypt(data + token, "SHA-256");
    }

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application", Locale.CHINA);

    @Test
    public void test1() {
        String testToken = BUNDLE.getString("testUrlPrefix");
        System.out.println(testToken);

    }
}
