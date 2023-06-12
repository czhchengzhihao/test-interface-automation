package com.czh.automation.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.anheng.datacenter.util
 * @ClassName: TestNGSqlutil
 * @Description: TestNGSqlutil/description:TODO 读取数据库
 * @Author: ChengZhiHao
 * @Date: 2021/10/20 15:04
 * @Version: v1.0
 */
public class TestngSqlUtil {
    public static List<Object[]> getTestData(String tableName) {
        List<Object[]> list = new ArrayList<>();
        //设置mysql驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立数据库的连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/automation?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8", "root", "admin123");
            //判断数据连接是否成功
            if (!conn.isClosed()) {
                System.out.println(tableName + "连接成功");
            } else {
                System.out.println(tableName + "连接失败");
            }
            //创建Statement对象可以用对应的方法executeQuery(sql语句)获取测试数据
            Statement statement = conn.createStatement();
            //创建一个结果集存放数据库执行完sql的数据
            //ResultSet resultSet = statement.executeQuery("select * from " + tableName);
            ResultSet resultSet = statement.executeQuery(tableName);
            //声明存放泛型string数组的list对象
            int colNum = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                String[] strings = new String[colNum];
                for (int i = 0; i < strings.length; i++) {
                    strings[i] = resultSet.getString(i + 1);
                }
                list.add(strings);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<Object[]> cases_management = getTestData("select * from cases_management");
        System.out.println(cases_management);
        //将list对象转换成二位数组
        Object[][] results = new Object[cases_management.size()][];
        for (int i = 0; i < cases_management.size(); i++) {
            results[i] = cases_management.get(i);
        }
        System.out.println(results);
        for (Object[] objectss : results) {
            for (Object object : objectss) {
                System.out.print("{" + object + "}");
            }
            System.out.println();
        }
    }
}
