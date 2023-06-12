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
 * @author admin
 * @Author ChengZhiHao
 * @Description //TODO 配置多数据源的方法 1.basePackages对应的mapper文件夹;2.prefix对应的yml文件数据库配置;3.对应的mapper.xml文件位置;
 * @Date 15:00 2022/5/17
 * @Param
 * @return
 **/
@Configuration
@MapperScan(basePackages = {"com.czh.automation.mapper.responseresult"}, sqlSessionFactoryRef = "automationSqlSessionFactory")
public class ResponseResultDataSourceConfig {
    @Bean(name = "automationDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.automation")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "automationSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("automationDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "automationTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("automationDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "automationSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("automationSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
