package com.springsource.html5expense.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.PropertyResolver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@Profile("local")
@PropertySource("/db.properties")
public class LocalDataSourceConfiguration implements DataSourceConfiguration {

    @Autowired
    private PropertyResolver propertyResolver;

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%s/%s", propertyResolver.getProperty("postgres.host"),
                 propertyResolver.getProperty("postgres.port"), propertyResolver.getProperty("postgres.db.name")));
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername(propertyResolver.getProperty("postgres.username"));
        dataSource.setPassword(propertyResolver.getProperty("postgres.password"));
        return dataSource;
    }

    public Map<String, String> jpaPropertyMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
        map.put(org.hibernate.cfg.Environment.HBM2DDL_IMPORT_FILES, "import.sql");
        map.put("hibernate.c3p0.min_size", "5");
        map.put("hibernate.c3p0.max_size", "20");
        map.put("hibernate.c3p0.timeout", "360000");
        map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return map;
    }
}
