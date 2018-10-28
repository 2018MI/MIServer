package org.chengpx.mi.cfg;

import org.chengpx.mi.filter.ReqMethodFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ReqMethodFilter> registFilter() {
        FilterRegistrationBean<ReqMethodFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new ReqMethodFilter());
        registration.addUrlPatterns("*.do");
        registration.setName("ReqMethodFilter");
        registration.setOrder(1);
        return registration;
    }

}