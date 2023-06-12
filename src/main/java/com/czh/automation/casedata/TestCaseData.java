package com.czh.automation.casedata;


import com.alibaba.fastjson.JSON;

import com.czh.automation.entity.ResponseResult;

import com.czh.automation.service.responseresult.ResponseResultService;
import com.czh.automation.util.ApplicationContextProvider;
import com.czh.automation.util.BaseCase;
import com.czh.automation.util.ReadDataBaseUtil;
import org.testng.annotations.DataProvider;

import java.util.List;


/**
 * @PackageName: com.example.demo.casedata
 * @ClassName: TestCaseData
 * @Description: TestCaseData/description:
 * @Author: ChengZhiHao
 * @Date: 2022/5/17 16:21
 * @Version: v1.0
 */

public class TestCaseData extends BaseCase {
//使用ApplicationContextProvider是因为夸方法调用 springboot不能自动注入bean,
// 需要手动注入，但是如果不需要夸类调用可以自动注入就不需要此方法了

    ResponseResultService responseResultService = ApplicationContextProvider.getBean(ResponseResultService.class);

    @DataProvider(name = "rr")
    public Object[][] rr() {
        List<ResponseResult> data = responseResultService.queryResponseResult("aaa");
        String parameter = JSON.toJSONString(data);
        System.out.println(parameter);
        return ReadDataBaseUtil.readDataBase(parameter);
    }


/*    @Test
    public void test() {
        List<ResponseResult> data = responseResultService.queryResponseResult("测试");
        System.out.println(data);
        String s1 = data.toString();
        System.out.println(s1);

        String s = JSONObject.toJSONString(data);
        System.out.println(s);

        Object[][] dataS = ReadDataBaseUtil.readDataBase(s);
        System.out.println(dataS);
        for (Object[] objects : dataS) {
            for (Object object : objects) {
                System.out.print("{" + object + "}");
            }
            System.out.println();
        }
    }*/


}

