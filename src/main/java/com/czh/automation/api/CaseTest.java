package com.czh.automation.api;

import com.czh.automation.casedata.TestCaseData;
import com.czh.automation.util.BaseCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @PackageName: com.anheng.daslink.api
 * @ClassName: CaseTest
 * @Description: CaseTest/description:
 * @Author: ChengZhiHao
 * @Date: 2022/6/21 17:22
 * @Version: v1.0
 */
@Slf4j
public class CaseTest extends BaseCase {

    @Test(dataProvider = "rr", dataProviderClass = TestCaseData.class)
    public void test(String aa, String bb, String cc) {
        log.info(aa + bb + cc);
    }

    @Test
    public void test3() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://login.taobao.com/newlogin/login.do?appName=taobao&fromSite=0&_bx-v=2.2.3");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loginId", "ewqeq"));
        params.add(new BasicNameValuePair("password2", "9df691c99cf5220292d034ed3e13046b5f003c58f7f3785c9823e3b4a7ae441bcfd55f6d49d6989d4eb1963941e887cdc04795522b4eb122a4df6515eb963a04c37d07b663cb1d17af2a87fdcf45723f9471ab266d79af01a0903e308a9ba0f0cb24134155b952f2627ce0ca2db59fd07c8caff7a7a681017c288c35a0f10b00"));
        params.add(new BasicNameValuePair("keepLogin", "false"));
        post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("Cookie", "miid=2581225962078703927; cna=dqNEGWgIqAoCAT2kL8Thmck+; _m_h5_tk=90615c7a20bf2a523c0802a0839bb90a_1684988053477; _m_h5_tk_enc=a0f5f815961ce0eb3140a5e73ecdcd5c; xlly_s=1; XSRF-TOKEN=2365bf16-a6e4-4fa8-b33d-619cb25229fb; _samesite_flag_=true; cookie2=17c216241b592a519caebedb1f36a781; t=412872c7d6de6d7269fdb8555b55881e; _tb_token_=fbe7e57eba9a6; arms_uid=d17805dc-9b91-4b55-9537-269f9e67e8c1; x5sec=7b22617365727665723b32223a22303361656232363131616336623239333835353636313366346163326237643143496d4975364d47454e58692b5a654170622f7875414577395a54717a2f722f2f2f2f2f41554144227d; l=fBNC-VM4gixjy9sNBO5aourza77tqLAbGsPzaNbMiIEGa6s1BF_pbNC_YGGJvdtjgT5mZttrtWFbsdh6y0aU-x1Hrt7APlUOrM96ReM3N7AN.; isg=BGtrK8idi9wuTdcuHk-zADZJ-o9VgH8Ci9dtnt3iAKoBfJfeCVT5U8qa0rwS3Nf6; tfstk=dZRW0_iehuqWlNttTag216D3Ea1QF3Gw2y_pSeFzJ_C-RHt9V9WdYYxCdH-cpQ5yznOBbngHU95-RHt9V9WpLB7pOnfRKLSF4M1B-hgqbflwrUfhpcoZ_INz2_PHN7b5ONTlt6nauWHZ1UxM0nYfUJxiPOS_JEOyMFmbikT5cWF_Sd68DneUH-FGyTjOHi6Zf5S6sxeQlOVClGujlJ2H4EBhg");
        post.setHeader("Connection", "keep-alive");
        System.out.println(post);
        HttpResponse response = httpclient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);

    }

}
