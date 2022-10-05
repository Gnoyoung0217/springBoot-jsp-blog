package com.jerryLog.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan ("com.jerryLog.www.dao")
public class MySQLConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    	// SqlSessionFactory = Mysql과 MyBatis를 연결해주는 인스턴스
    	// SqlSessionFactoryBean = SqlSessionFactory를 생성해주는 클래스
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean(); 
        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:static/mybatis/mapper/*.xml"));

        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
        sessionFactory.setConfigLocation(myBatisConfig);

        return sessionFactory.getObject();
    }
}