/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springsource.html5expense.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.map.HashedMap;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.cloudfoundry.runtime.env.RdbmsServiceInfo;
import org.cloudfoundry.runtime.service.relational.RdbmsServiceCreator;
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
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.springsource.html5expense.controller.ExpenseController;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.serviceImpl.JpaExpenseReportServiceImpl;


@Configuration
@EnableTransactionManagement
//@PropertySource("/config.properties")
@ImportResource({ "classpath:spring-security.xml"})
@ComponentScan(basePackageClasses = {JpaExpenseReportServiceImpl.class,ExpenseController.class,Expense.class})

public class ComponentConfig {
		
	/*@Bean
    public DataSource dataSource()  {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/test");
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }*/
	
	@Bean
    public CloudEnvironment environment() {
        return new CloudEnvironment();
    }

    @Bean
    public DataSource dataSource() throws Exception {
        CloudEnvironment environment = environment();
        Collection<RdbmsServiceInfo> mysqlSvc = environment.getServiceInfos(RdbmsServiceInfo.class);
        RdbmsServiceCreator dataSourceCreator = new RdbmsServiceCreator();
        return dataSourceCreator.createService(mysqlSvc.iterator().next());
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
	    LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
	    emfb.setJpaVendorAdapter( jpaAdapter());
	    emfb.setDataSource(dataSource());
	    emfb.setJpaPropertyMap(createPropertyMap());
	    emfb.setJpaDialect(new HibernateJpaDialect());
	    emfb.setPackagesToScan(new String[]{Expense.class.getPackage().getName()});
	    return emfb;
	}
	
	public Map<String,String> createPropertyMap()
	{
		Map<String,String> map= new HashedMap();
		map.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		map.put(org.hibernate.cfg.Environment.HBM2DDL_IMPORT_FILES, "import.sql");
		map.put("hibernate.c3p0.min_size", "5");
		map.put("hibernate.c3p0.max_size", "20");
		map.put("hibernate.c3p0.timeout", "360000");
		map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return map;
		
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
	public PlatformTransactionManager transactionManager() throws Exception{
	    final JpaTransactionManager transactionManager =
	        new JpaTransactionManager();

	    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	    Map<String,String> jpaProperties = new HashMap<String,String>();
	    jpaProperties.put("transactionTimeout","43200");
	    transactionManager.setJpaPropertyMap(jpaProperties);

	    return transactionManager;
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10000000);
		return multipartResolver;
	}
	
	@Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
	
}
