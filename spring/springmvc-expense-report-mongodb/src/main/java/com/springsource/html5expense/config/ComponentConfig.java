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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mongodb.Mongo;
import com.springsource.html5expense.controller.ExpenseController;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.mongodb.services.AttachmentRepository;

/**
 * Configuration for application @Components such as @Services, @Repositories, and @Controllers.
 * Loads externalized property values required to configure the various application properties.
 * Not much else here, as we rely on @Component scanning in conjunction with @Inject by-type autowiring.
 *
 */
@Configuration
@EnableTransactionManagement
@ImportResource("/WEB-INF/spring-security.xml")
@ComponentScan(basePackageClasses = {AttachmentRepository.class,
		 ExpenseController.class,Expense.class})

public class ComponentConfig {
	
	@Bean
	public 	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new Mongo("localhost",
				 27017), "rrrr");
	}
	
	@Bean
	public 	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		/*MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory(), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);*/
		return mongoTemplate;
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
