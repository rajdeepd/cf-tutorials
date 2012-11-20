package com.springsource.html5expense.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.cloudfoundry.runtime.env.RdbmsServiceInfo;
import org.cloudfoundry.runtime.service.relational.RdbmsServiceCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudDataSourceConfiguration implements DataSourceConfiguration {

    private CloudEnvironment cloudEnvironment = new CloudEnvironment();

    @Bean
    public DataSource dataSource() {
        Collection<RdbmsServiceInfo> psqlServiceInfo = cloudEnvironment.getServiceInfos(RdbmsServiceInfo.class);
        RdbmsServiceCreator dataSourceCreator = new RdbmsServiceCreator();
        return dataSourceCreator.createService(psqlServiceInfo.iterator().next());
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
