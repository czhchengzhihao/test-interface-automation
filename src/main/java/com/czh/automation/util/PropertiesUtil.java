package com.czh.automation.util;

import lombok.extern.slf4j.Slf4j;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @PackageName: com.anheng.datacenter.util
 * @ClassName: PropertiesUtil
 * @Description: PropertiesUtil/description:TODO 读写properties文件
 * @Author: ChengZhiHao
 * @Date: 2022/1/15 14:42
 * @Version: v1.0
 */

@Slf4j
public class PropertiesUtil {
    /**加载properties文件*/
    // private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("dasLinkInterfaceAddress", Locale.CHINA);


    /**
     * @return void
     * @Author ChengZhiHao
     * @Description //TODO 写入properties文件
     * @Date 14:43 2022/1/15
     * @Param [propertiesPath, key, value,describe]
     **/
    public static void writeProperties(String propertiesPath, String key, String value, String describe) {
        //创建Properties对象
        Properties properties = new Properties();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            //创建输入流文件对象
            inputStream = new FileInputStream(propertiesPath);
            //加载输入流
            properties.load(inputStream);
            //修改值
            properties.setProperty(key, value);
            //创建输出流文件对象
            fileOutputStream = new FileOutputStream(propertiesPath);
            //将Properties对象的属性保存到输出流指定文件
            properties.store(fileOutputStream, describe);
        } catch (Exception e) {
            log.info("写入properties文件异常");
            Reporter.log("写入properties文件异常");
        } finally {
            try {
                assert fileOutputStream != null;
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                log.info("关闭输入,输出流异常");
                Reporter.log("关闭输入,输出流异常");
            }
        }
    }

    /**
     * @return void
     * @Author ChengZhiHao
     * @Description //TODO 删除properties文件
     * @Date 15:03 2022/1/15
     * @Param [propertiesPath, key, describe]
     **/
    public static void deleteProperties(String propertiesPath, String key, String describe) {
        //创建Properties对象
        Properties properties = new Properties();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        //创建输入流文件对象
        try {
            inputStream = new FileInputStream(propertiesPath);
            //加载输入流
            properties.load(inputStream);
            //修改值
            properties.remove(key);
            //创建输出流文件对象
            fileOutputStream = new FileOutputStream(propertiesPath);
            //将Properties对象的属性保存到输出流指定文件
            properties.store(fileOutputStream, describe);
        } catch (Exception e) {
            log.info("删除properties文件异常");
            Reporter.log("删除properties文件异常");
        } finally {
            try {
                assert fileOutputStream != null;
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                log.info("关闭输入,输出流异常");
                Reporter.log("关闭输入,输出流异常");
            }

        }

    }
}
