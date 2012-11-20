package com.springsource.html5expense.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springsource.html5expense.controllers.ExpenseController;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.services.JpaExpenseService;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {JpaExpenseService.class,
        ExpenseController.class, Expense.class })
@ImportResource("/WEB-INF/spring-security.xml")
public class ComponentConfig {

    @Autowired private DataSourceConfiguration dataSourceConfiguration;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setJpaVendorAdapter(jpaAdapter());
        emfb.setDataSource(dataSourceConfiguration.dataSource());
        emfb.setJpaPropertyMap(dataSourceConfiguration.jpaPropertyMap());
        emfb.setJpaDialect(new HibernateJpaDialect());
        emfb.setPackagesToScan(new String[]{Expense.class.getPackage().getName()});
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        Map<String, String> jpaProperties = new HashMap<String, String>();
        jpaProperties.put("transactionTimeout", "43200");
        transactionManager.setJpaPropertyMap(jpaProperties);
        return transactionManager;
    }
}
