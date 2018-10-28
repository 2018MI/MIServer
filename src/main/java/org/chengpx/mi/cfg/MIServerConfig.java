package org.chengpx.mi.cfg;

import org.chengpx.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chengpx
 * @date 2018/10/27 17:03
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootConfiguration
@ComponentScan(basePackages = "org.chengpx")
@PropertySource(value = {"classpath:project.properties"})
public class MIServerConfig {

    //    @Value(value = "${jdbc.driver}")
    //    private String driver;
    //    @Value(value = "${jdbc.url}")
    //    private String url;
    //    @Value(value = "${jdbc.username}")
    //    private String username;
    //    @Value(value = "${jdbc.password}")
    //    private String password;
    //    @Value(value = "${jdbc.readOnly}")
    //    private Boolean readOnly;
    //    @Value(value = "${jdbc.connectionTimeout}")
    //    private Long connectionTimeout;
    //    @Value(value = "${jdbc.idleTimeout}")
    //    private Long idleTimeout;
    //    @Value(value = "${jdbc.maxLifetime}")
    //    private Long maxLifetime;
    //    @Value(value = "${jdbc.maximumPoolSize}")
    //    private Integer maximumPoolSize;

    @Value(value = "${taskExecutor.corePoolSize}")
    private Integer corePoolSize;
    @Value(value = "${taskExecutor.keepAliveSeconds}")
    private Integer keepAliveSeconds;
    @Value(value = "${taskExecutor.maxPoolSize}")
    private Integer maxPoolSize;
    @Value(value = "${taskExecutor.queueCapacity}")
    private Integer queueCapacity;

    public static void main(String[] args) {
        SpringApplication.run(MIServerConfig.class, args);
    }

    @Bean(value = "threadPoolTaskExecutor")
    @ConditionalOnMissingBean
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        // # 对拒绝task的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

    //    @Bean(value = "dataSource")
    //    public DataSource getDataSource() {
    //        HikariDataSource hikariDataSource = new HikariDataSource();
    //        hikariDataSource.setDriverClassName(driver);
    //        hikariDataSource.setJdbcUrl(url);
    //        hikariDataSource.setUsername(username);
    //        hikariDataSource.setPassword(password);
    //        hikariDataSource.setReadOnly(readOnly);
    //        hikariDataSource.setConnectionTimeout(connectionTimeout);
    //        hikariDataSource.setIdleTimeout(idleTimeout);
    //        hikariDataSource.setMaxLifetime(maxLifetime);
    //        hikariDataSource.setMaximumPoolSize(maximumPoolSize);
    //        return hikariDataSource;
    //    }

    @Bean
    public GsonHttpMessageConverter getGsonHttpMessageConverter() {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(JsonUtils.getGson());
        gsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        return gsonHttpMessageConverter;
    }

}
