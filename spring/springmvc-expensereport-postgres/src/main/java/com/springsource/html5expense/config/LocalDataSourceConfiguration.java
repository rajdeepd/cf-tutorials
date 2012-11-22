package com.springsource.html5expense.config;

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
}
