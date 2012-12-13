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

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.springsource.html5expense.controllers.ExpenseController;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.redis.services.ExpenseRepository;
import com.springsource.html5expense.redis.services.KeyUtils;

@Configuration
@ComponentScan(basePackageClasses = { ExpenseRepository.class, ExpenseController.class,
        Expense.class })
@EnableWebMvc
public class ComponentConfig {

    @Autowired
    private RedisConnectionFactoryConfiguration redisConfiguration;

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate() throws Exception {
        RedisTemplate<String, Serializable> redisTemplate = new
                RedisTemplate<String, Serializable>();
        redisTemplate.setConnectionFactory(redisConfiguration.redisConnectionFactory());
        return redisTemplate;
    }

    @Bean(name = KeyUtils.EXPENSE_COUNT)
    public RedisAtomicLong expenseCount() throws Exception {
        return new RedisAtomicLong(KeyUtils.EXPENSE_COUNT,
                redisConfiguration.redisConnectionFactory());
    }

    @Bean(name = KeyUtils.EXPENSE_TYPE_COUNT)
    public RedisAtomicLong expenseTypeCount() throws Exception {
        return new RedisAtomicLong(KeyUtils.EXPENSE_TYPE_COUNT,
                redisConfiguration.redisConnectionFactory());
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver =
                new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
}
