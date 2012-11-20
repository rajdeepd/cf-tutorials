package com.springsource.html5expense.config;

import java.util.Map;
import javax.sql.DataSource;

public interface DataSourceConfiguration {
    DataSource dataSource();

    Map<String, String> jpaPropertyMap();
}
