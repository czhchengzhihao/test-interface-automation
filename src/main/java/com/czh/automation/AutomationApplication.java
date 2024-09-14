package com.czh.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author admin
 */
@SpringBootApplication
//(exclude= {DataSourceAutoConfiguration.class})
//@ComponentScan("com.czh.automation.mapper")

public class AutomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomationApplication.class, args);
    }

}
