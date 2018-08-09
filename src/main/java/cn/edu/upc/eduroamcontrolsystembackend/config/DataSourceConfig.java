package cn.edu.upc.eduroamcontrolsystembackend.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by jay on 2018/08/08
 */

@Configuration
public class DataSourceConfig {
    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.primary.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "radiusDataSource")
    @ConfigurationProperties(prefix = "spring.radius.datasource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }
}
