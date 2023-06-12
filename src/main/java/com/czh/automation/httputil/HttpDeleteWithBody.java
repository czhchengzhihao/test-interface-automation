package com.czh.automation.httputil;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * @PackageName: com.anheng.datacenter.httputil
 * @ClassName: HttpDeleteWithBody
 * @Description: HttpDeleteWithBody/description:
 * @Author: ChengZhiHao
 * @Date: 2021/12/16 12:53
 * @Version: v1.0
 * TODO HttpClient中DELETE请求，是没有办法带参数的。因为setEntity()方法是抽象类HttpEntityEnclosingRequestBase类里的方法，
 * TODO HttpPost继承了该类，而HttpDelete类继承的是HttpRequestBase类。下面是没有setEntity()方法的。
 * TODO https://my.oschina.net/u/4267017/blog/4320520
 */

public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

    public static final String METHOD_NAME = "DELETE";

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpDeleteWithBody() {
        super();
    }


}
