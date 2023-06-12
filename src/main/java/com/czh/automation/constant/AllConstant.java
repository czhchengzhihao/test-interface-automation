package com.czh.automation.constant;

/**
 * @PackageName: com.czh.constant
 * @ClassName: AllConstant
 * @Description: AllConstant/description:常量管理类
 * @Author: ChengZhiHao
 * @Date: 2021/4/12 15:02
 * @Version: v1.0
 */
public class AllConstant {

    /**
     * 状态码
     */
    public static final int HTTP_CODE_200 = 200;

    /**
     * http设置请求和传输超时时间  创建链接最长时间 获取链接最长时间 数据传输最长时间
     */
    public static final int HTTP_CONNECT_TIME_OUT = 10000;
    public static final int HTTP_CONNECTION_REQUEST_TIME_OUT = 500;
    public static final int HTTP_SOCKET_TIME_OUT = 10000;
    public static final int MAX_TOTAL = 100;
    public static final int DEFAULT_MAX_PER_ROUTE = 10;
    public static final int RETRY_COUNT = 3;

    /**
     * 格式
     */
    public static final String UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    /**
     * http,https 协议
     */
    public static final String HTTP = "http";
    public static final String HTTPS = "https";




}
