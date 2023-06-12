package com.czh.automation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.czh.automation.entity.CaseData;
import com.czh.automation.entity.ResponseResult;
import com.czh.automation.httputil.HttpGetUtil;
import com.czh.automation.mapper.casemanagement.CaseDataMapper;
import com.czh.automation.mapper.querybooks.QueryBooksMapper;
import com.czh.automation.mapper.responseresult.ResponseResultMapper;
import com.czh.automation.service.responseresult.ResponseResultService;
import com.czh.automation.util.BaseCase;
import com.czh.automation.util.ReadDataBaseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class TestDemo extends BaseCase {


    @Autowired
    private ResponseResultMapper responseResultMapper;
    @Autowired
    private QueryBooksMapper queryBooksMapper;
    @Autowired
    private CaseDataMapper caseDataMapper;
    @Resource
    private ResponseResultService responseResultService;

    @Test
    public void test1() {

        log.info(JSONObject.toJSONString(responseResultMapper.queryResponseResult("测试")));
        log.info(JSONObject.toJSONString(queryBooksMapper.queryBooks()));


    }

    @Test
    public void test2() {
        for (int i = 0; i < 1; i++) {
            log.debug("debug");
            log.info("info");
            log.warn("warn");
            log.error("error");
            log.info("-------------------------" + i);
        }
        log.info("------------------end---------------------");

    }

    @Test
    public void test3() {
        JSONObject jsonObject = HttpGetUtil.httpGet("testUrl", "aa");
        System.out.println(jsonObject);
    }

    @Test
    public void test4() {
        List<ResponseResult> data = responseResultMapper.queryResponseResult("测试");
        System.out.println(data + "aaaaaaa");
        String s = JSONObject.toJSONString(data);
        System.out.println(JSONArray.parseArray(s).size() + "aaaa");
        System.out.println(JSONArray.parseArray(s).getJSONObject(0).size() + "aaa");
        Object[][] dataS = new Object[JSONArray.parseArray(s).size()][JSONArray.parseArray(s).getJSONObject(0).size()];
        System.out.println(JSONArray.parseArray(s).size() + "行");
        System.out.println(JSONArray.parseArray(s).getJSONObject(0).size() + "列");
        for (int i = 0; i < JSONArray.parseArray(s).size(); i++) {
            for (int j = 0; j < JSONArray.parseArray(s).getJSONObject(i).size(); j++) {
                dataS[i][j] = JSONArray.parseArray(s).getJSONObject(i).keySet();
            }
        }
        for (Object[] objects : dataS) {
            for (Object object : objects) {
                System.out.print("{" + object + "}");
            }
            System.out.println();
        }




      /*  Object[][] dataS = new Object[JSONArray.parseArray(s).size()][JSONArray.parseArray(s).getJSONObject(1).size()];
        for (int i = 0; i < JSONArray.parseArray(s).size(); i++) {
            JSONObject jsonObject = JSONArray.parseArray(s).getJSONObject(1);
            for (int j = 0; j < JSONArray.parseArray(s).getJSONObject(i).size(); j++) {
                JSONObject jsonObject1 = JSONArray.parseArray(s).getJSONObject(1);
                dataS[i][j] = jsonObject1;
            }
            for (Object[] objects : dataS) {
                for (Object object : objects) {
                    System.out.print("{" + object + "}");
                   // System.out.println(object);
                }
                System.out.println();
            }
        }*/

    }

    @Test
    public void test44() {
        List<ResponseResult> data = responseResultMapper.queryResponseResult("测试");
        System.out.println(data + "aaaaaaa");
        String s = JSONObject.toJSONString(data);
        System.out.println(JSONArray.parseArray(s).size() + "aaaa");
        System.out.println(JSONArray.parseArray(s).getJSONObject(0).size() + "aaa");
        Object[][] dataS = new Object[JSONArray.parseArray(s).size()][JSONArray.parseArray(s).getJSONObject(0).size()];
        System.out.println(JSONArray.parseArray(s).size() + "行");
        System.out.println(JSONArray.parseArray(s).getJSONObject(0).size() + "列");
        for (int i = 0; i < JSONArray.parseArray(s).size(); i++) {
            int j = 0;
            Set<Map.Entry<String, Object>> entries = JSONArray.parseArray(s).getJSONObject(i).entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                dataS[i][j++] = entry.getValue();
            }
        }
        for (Object[] objects : dataS) {
            for (Object object : objects) {
                System.out.print("{" + object + "}");
            }
            System.out.println();
        }

    }

    @DataProvider(name = "ab")
    public Object[][] yy() {
        List<ResponseResult> data = responseResultMapper.queryResponseResult("测试");
        String parameter = JSONObject.toJSONString(data);
        return ReadDataBaseUtil.readDataBase(parameter);
    }

    @DataProvider(name = "cd")
    public Object[][] zz() {
        List<ResponseResult> data = responseResultService.queryResponseResult("测试");
        System.out.println("参数+" + data);
        String parameter = JSONObject.toJSONString(data);
        return ReadDataBaseUtil.readDataBase(parameter);
    }

    @DataProvider(name = "aa")
    public Object[][] test424() {
        List<ResponseResult> data = responseResultMapper.queryResponseResult("测试");
        System.out.println(data + "aaaaaaa");
        String s = JSONObject.toJSONString(data);
        System.out.println(JSONArray.parseArray(s).size() + "aaaa");
        System.out.println(JSONArray.parseArray(s).getJSONObject(0).size() + "aaa");
        Object[][] dataS = new Object[JSONArray.parseArray(s).size()][JSONArray.parseArray(s).getJSONObject(0).size()];
        System.out.println(JSONArray.parseArray(s).size() + "行");
        System.out.println(JSONArray.parseArray(s).getJSONObject(0).size() + "列");
        for (int i = 0; i < JSONArray.parseArray(s).size(); i++) {
            int j = 0;
            Set<Map.Entry<String, Object>> entries = JSONArray.parseArray(s).getJSONObject(i).entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                dataS[i][j++] = entry.getValue();
            }
        }
        for (Object[] objects : dataS) {
            for (Object object : objects) {
                System.out.print("{" + object + "}");
            }
            System.out.println();
        }
        return dataS;
    }

    @Test(dataProvider = "ab")
    public void test(String aa, String bb, String cc) {
        log.info(aa + bb + cc);
    }


    @Test(dataProvider = "cd")
    public void test123(String aa, String bb, String cc) {
        log.info(aa + bb + cc);
    }

    @Test
    public void test22() {
        List<CaseData> caseData = caseDataMapper.queryCaseData();
        System.out.println(caseData);
    }
}
