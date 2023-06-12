package com.czh.automation.connection;

import com.czh.automation.constant.AllConstant;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @PackageName: com.czh.util
 * @ClassName: HttpClientConnection
 * @Description: HttpClientConnection/description:连接池管理器
 * @Author: ChengZhiHao
 * @Date: 2021/4/27 14:23
 * @Version: v1.0
 */
public class HttpClientConnection {

    private static PoolingHttpClientConnectionManager manager;

    static {
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslSf;
            sslSf = new SSLConnectionSocketFactory(builder.build());
            // 配置同时支持 HTTP 和 HTTPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(AllConstant.HTTP, PlainConnectionSocketFactory.getSocketFactory())
                    .register(AllConstant.HTTPS, sslSf).build();
            manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            //设置最大连接数
            manager.setMaxTotal(AllConstant.MAX_TOTAL);
            //设置最大主机连接数
            manager.setDefaultMaxPerRoute(AllConstant.DEFAULT_MAX_PER_ROUTE);
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return org.apache.http.impl.client.CloseableHttpClient
     * @Author ChengZhiHao
     * @Description //TODO httpClient连接池
     * @Date 10:17 2021/5/7
     * @Param []
     **/
    public static CloseableHttpClient getHttpClientConnection() {
        //设置超时时间
        RequestConfig requestconfig = RequestConfig.custom()
                .setConnectTimeout(AllConstant.HTTP_CONNECT_TIME_OUT)
                .setConnectionRequestTimeout(AllConstant.HTTP_CONNECTION_REQUEST_TIME_OUT)
                .setSocketTimeout(AllConstant.HTTP_SOCKET_TIME_OUT).build();
        return HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(manager)
                .setDefaultRequestConfig(requestconfig)
                // 设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(AllConstant.RETRY_COUNT, false)).build();
    }

}
