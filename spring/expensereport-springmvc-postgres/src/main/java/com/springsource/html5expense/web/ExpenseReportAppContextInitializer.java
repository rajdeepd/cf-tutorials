package com.springsource.html5expense.web;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ExpenseReportAppContextInitializer implements ApplicationContextInitializer<AnnotationConfigWebApplicationContext> {

    private CloudEnvironment cloudEnvironment = new CloudEnvironment();

    private boolean isCloudFoundry() {
        return cloudEnvironment.isCloudFoundry();
    }

    @Override
    public void initialize(AnnotationConfigWebApplicationContext applicationContext) {
        String profile = "local";
        if (isCloudFoundry()) {
            profile = "cloud";
        }
        applicationContext.getEnvironment().setActiveProfiles(profile);
        applicationContext.refresh();
    }
}
