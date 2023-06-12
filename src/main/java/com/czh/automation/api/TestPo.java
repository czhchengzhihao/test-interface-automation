package com.czh.automation.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.czh.automation.entity.ResponseResult;
import com.czh.automation.service.responseresult.ResponseResultService;
import com.czh.automation.util.BaseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @PackageName: com.anheng.daslink.api
 * @ClassName: TestPo
 * @Description: TestPo/description:
 * @Author: ChengZhiHao
 * @Date: 2022/8/23 13:21
 * @Version: v1.0
 */
@Component
public class TestPo extends BaseCase {

    //ResponseResultService responseResultService = ApplicationContextProvider.getBean(ResponseResultService.class);


    /* 使用ApplicationContextProvider是因为夸方法调用 springboot不能自动注入bean,
 需要手动注入，但是如果不需要夸类调用可以自动注入就不需要此方法了*/
    @Autowired
    public ResponseResultService responseResultService;

    @Test
    public void test1() {
        List<ResponseResult> data = responseResultService.queryResponseResult("测试");
        System.out.println(data);
        JSONArray parameter = JSONArray.parseArray(JSON.toJSONString(data));
        System.out.println(parameter);
        String success = parameter.getJSONObject(0).getString("success");
        System.out.println(success);
    }


/*    @DataProvider(name = "rr")
    public Object[][] rr() {
        List<ResponseResult> data = responseResultService.queryResponseResult("aaa");
        String parameter = JSON.toJSONString(data);
        System.out.println(parameter);
        return ReadDataBaseUtil.readDataBase(parameter);*/
}

/*    @DataProvider(name = "rr")
    public Object[][] rr() {
        List<ResponseResult> data = responseResultService.queryResponseResult("测试");
        String parameter = JSONObject.toJSONString(data);
        return ReadDataBaseUtil.readDataBase(parameter);
    }




}*/
