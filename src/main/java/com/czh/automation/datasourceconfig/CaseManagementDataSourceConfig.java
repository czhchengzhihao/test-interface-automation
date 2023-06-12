package com.czh.automation.datasourceconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @PackageName: com.anheng.daslink.datasourceconfig
 * @ClassName: CaseManagementDataSourceConfig
 * @Description: CaseManagementDataSourceConfig/description:TODO 测试用例数据库
 * @Author: ChengZhiHao
 * @Date: 2022/6/21 17:07
 * @Version: v1.0
 */
@Configuration
@MapperScan(basePackages = {"com.czh.automation.mapper.casemanagement"}, sqlSessionFactoryRef = "caseManagementSqlSessionFactory")
public class CaseManagementDataSourceConfig {
    @Bean(name = "caseManagementDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.casemanagement")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "caseManagementSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("caseManagementDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "caseManagementTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("caseManagementDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "caseManagementSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("caseManagementSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
